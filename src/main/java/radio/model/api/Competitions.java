package radio.model.api;

import java.util.List;
import java.util.Optional;

import radio.model.Competition;

public interface Competitions {

 Optional<Competition> competitionBy(int id);

 List<Competition> competitionsForInscription();
}
