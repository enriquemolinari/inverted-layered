package radio.model;

import java.util.function.Function;

public interface UnitOfWork {
 <R> R tx(Function<Competitions, R> codeInTx);
}
