package radio.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import radio.model.api.Competitor;
import radio.model.api.RadioCompetition;
import radio.model.api.RadioException;

public class Competition implements RadioCompetition {

 private int id;
 private String descripcion;
 private String rules;
 private LocalDateTime startDate;
 private LocalDateTime inscriptionStartDate;
 private LocalDateTime inscriptionEndDate;
 private List<Inscription> inscriptions;

 // TODO: validar...
 public Competition(int id, String description, String rules,
   LocalDateTime startDate, LocalDateTime inscriptionStartDate,
   LocalDateTime inscriptionEndDate) {
  this.id = new NotNullNorEmpty<Integer>(id, "Must be a valid ").value();
  this.descripcion = new NotNullNorEmpty<String>(description,
    "Must be a valid description...").value();
  this.rules = rules;
  this.startDate = startDate;
  this.inscriptionStartDate = inscriptionStartDate;
  this.inscriptionEndDate = inscriptionEndDate;
  this.inscriptions = new ArrayList<>();
 }

 protected Competition() {

 }

 boolean competitor(String personId) {
  return this.inscriptions.stream()
    .anyMatch((i) -> i.isEnrolled(personId));
 }

 public Competitor enroll(Competitor competitor) {
  var inscriptionDate = LocalDateTime.now();
  if (!inTime(inscriptionDate))
   throw new RadioException("Out of inscription date...");

  var dCompetitor = DefaultCompetitor.of(competitor);

  if (sameDayAsInscriptionStart(inscriptionDate))
   dCompetitor.morePoints(10);

  this.inscriptions.add(new Inscription(dCompetitor, inscriptionDate));

  return dCompetitor;
 }

 private boolean sameDayAsInscriptionStart(LocalDateTime inscriptionDate) {
  return inscriptionStartDate.toLocalDate()
    .equals(inscriptionDate.toLocalDate());
 }

 public int totalCompetitors() {
  return inscriptions.size();
 }

 @Override
 public int id() {
  return this.id;
 }

 @Override
 public String description() {
  return this.descripcion;
 }

 @Override
 public String rules() {
  return this.rules;
 }

 @Override
 public LocalDateTime startDate() {
  return this.startDate;
 }

 @Override
 public LocalDateTime inscriptionStartDate() {
  return this.inscriptionStartDate;
 }

 @Override
 public LocalDateTime inscriptionEndDate() {
  return this.inscriptionEndDate;
 }

 private boolean inTime(LocalDateTime inscriptionDate) {
  return inscriptionStartDate.isBefore(inscriptionDate)
    && inscriptionEndDate.isAfter(inscriptionDate);
 }

 private int getId() {
  return id;
 }

 private void setId(int id) {
  this.id = id;
 }

 private String getDescripcion() {
  return descripcion;
 }

 private void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
 }

 private String getRules() {
  return rules;
 }

 private void setRules(String rules) {
  this.rules = rules;
 }

 private LocalDateTime getStartDate() {
  return startDate;
 }

 private void setStartDate(LocalDateTime startDate) {
  this.startDate = startDate;
 }

 private LocalDateTime getInscriptionStartDate() {
  return inscriptionStartDate;
 }

 private void setInscriptionStartDate(LocalDateTime inscriptionStartDate) {
  this.inscriptionStartDate = inscriptionStartDate;
 }

 private LocalDateTime getInscriptionEndDate() {
  return inscriptionEndDate;
 }

 private void setInscriptionEndDate(LocalDateTime inscriptionEndDate) {
  this.inscriptionEndDate = inscriptionEndDate;
 }

 private List<Inscription> getInscriptions() {
  return inscriptions;
 }

 private void setInscriptions(List<Inscription> inscriptions) {
  this.inscriptions = inscriptions;
 }
}
