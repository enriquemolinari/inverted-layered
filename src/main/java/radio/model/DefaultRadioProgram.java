package radio.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import radio.model.api.Competitions;
import radio.model.api.Competitor;
import radio.model.api.RadioCompetition;
import radio.model.api.RadioException;
import radio.model.api.RadioProgram;
import radio.model.api.UnitOfWork;

public class DefaultRadioProgram implements RadioProgram {

 private UnitOfWork unit;

 public DefaultRadioProgram(UnitOfWork unit) {
  this.unit = unit;
 }

 @Override
 public Iterable<RadioCompetition> availableCompetitions() {
  Function<Competitions, List<Competition>> codeBlock = (competitions) -> {
   return competitions.competitionsForInscription();
  };

  var competitions = unit.tx(codeBlock);

  return competitions.stream().map(d -> new RadioCompetition() {
   @Override
   public int id() {
    return d.id();
   }

   @Override
   public String description() {
    return d.description();
   }

   @Override
   public String rules() {
    return d.rules();
   }

   @Override
   public LocalDateTime startDate() {
    return d.startDate();
   }

   @Override
   public LocalDateTime inscriptionStartDate() {
    return d.inscriptionStartDate();
   }

   @Override
   public LocalDateTime inscriptionEndDate() {
    return d.inscriptionEndDate();
   }
  }).collect(Collectors.toList());
 }

 @Override
 public void addInscription(int idCompetition, Competitor competitor) {
  unit.tx((competitions) -> {
   var competition = competitions.competitionBy(idCompetition).orElseThrow(
     () -> new RadioException("Selected competition does not exists..."));

   Competitor c = new DefaultCompetitor(competitor.personId(),
     competitor.name(), competitor.lastName(), competitor.email(),
     competitor.phone());

   competition.enroll(c);

   // just to make the compiler happy...
   return null;
  });
 }
}
