package radio.persistence;

import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import radio.model.api.Competitions;
import radio.model.api.RadioException;
import radio.model.api.UnitOfWork;

public class JpaUnitOfWork implements UnitOfWork {

 private EntityManagerFactory emf;

 public JpaUnitOfWork(String persistenceUnit) {
  this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
 }

 @Override
 public <R> R tx(Function<Competitions, R> codeInTx) {
  EntityManager em = emf.createEntityManager();
  EntityTransaction tx = em.getTransaction();
  try {
   tx.begin();

   R result = codeInTx.apply(new JpaCompetitions(em));

   tx.commit();
   return result;
  } catch (RadioException e) {
   tx.rollback();
   throw e;
  } catch (Exception e) {
   tx.rollback();
   throw new RuntimeException(e);
  } finally {
   em.close();
  }
 }
}
