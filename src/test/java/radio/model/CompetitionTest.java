package radio.model;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import radio.model.Competition;
import radio.model.DefaultCompetitor;
import radio.model.RadioException;

public class CompetitionTest {

 @Test
 public void enroll_competitor() {
  var competition = new Competition(1, "competiton 1", "rules...",
    LocalDateTime.now().plusDays(20), LocalDateTime.now().minusDays(1),
    LocalDateTime.now().plusDays(5));

  var competitor = competition.enroll(new DefaultCompetitor("1234",
    "Enrique", "Molinari", "enrique.molinari@gmail.com", "1234-987654"));

  Assert.assertTrue(competition.competitor("1234"));
  Assert.assertEquals(0, competitor.actualPoints());
 }

 @Test
 public void enroll_competitor_same_day_inscription_start() {
  var competition = new Competition(1, "competiton 1", "rules...",
    LocalDateTime.now().plusDays(20), LocalDateTime.now(),
    LocalDateTime.now().plusDays(5));

  var competitor = competition.enroll(new DefaultCompetitor("1234",
    "Enrique", "Molinari", "enrique.molinari@gmail.com", "1234-987654"));

  Assert.assertEquals(10, competitor.actualPoints());
 }

 @Test(expected = RadioException.class)
 public void cant_enroll() {
  var competition = new Competition(1, "competiton 1", "rules...",
    LocalDateTime.now().plusDays(20), LocalDateTime.now().plusDays(1),
    LocalDateTime.now().plusDays(5));

  competition.enroll(new DefaultCompetitor("1234", "Enrique", "Molinari",
    "enrique.molinari@gmail.com", "1234-987654"));

  Assert.assertTrue(false);
 }
}
