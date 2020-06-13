package ar.cpfw.book.radio.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import ar.cpfw.book.radio.model.Competition;
import ar.cpfw.book.radio.model.Competitions;
import ar.cpfw.book.radio.model.RadioException;

public class JpaCompetitions implements Competitions {
 private EntityManager em;

 public JpaCompetitions(EntityManager em) {
  this.em = em;
 }

 @Override
 public Optional<Competition> competitionBy(int id) {
  var c = em.find(Competition.class, id);
  if (c != null)
   return Optional.of(em.find(Competition.class, id));
  return Optional.empty();
 }

 @Override
 public List<Competition> competitionsForInscription()
   throws RadioException {

  return em.createQuery(
    "from Competition c where :today "
    + "between c.inscriptionStartDate and c.inscriptionEndDate",
    Competition.class)
    .setParameter("today", LocalDateTime.now()).getResultList();
 }
}
