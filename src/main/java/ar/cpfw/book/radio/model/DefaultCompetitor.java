package ar.cpfw.book.radio.model;

public class DefaultCompetitor implements Competitor {

 private int id;
 private String personId;
 private String name;
 private String lastName;
 private Email email;
 private Phone phone;

 public DefaultCompetitor(String personaId, String name, String lastName,
   String email, String phone) {
  this.personId = new NotNullNorEmpty<String>(personaId, "Id").value();
  this.name = new NotNullNorEmpty<String>(name, "Name").value();
  this.lastName = new NotNullNorEmpty<String>(lastName, "Last Name")
    .value();
  this.email = new Email(email);
  this.phone = new Phone(phone);
 }
 
 protected DefaultCompetitor() {
  
 }
 
 public String personId() {
  return personId;
 }

 public String name() {
  return name;
 }

 public String lastName() {
  return lastName;
 }

 public String email() {
  return email.toString();
 }

 public String phone() {
  return phone.toString();
 }

 private int getId() {
  return id;
 }

 private void setId(int id) {
  this.id = id;
 }

 private String getPersonId() {
  return personId;
 }

 private void setPersonId(String personId) {
  this.personId = personId;
 }

 private String getName() {
  return name;
 }

 private void setName(String name) {
  this.name = name;
 }

 private String getLastName() {
  return lastName;
 }

 private void setLastName(String lastName) {
  this.lastName = lastName;
 }

 private Email getEmail() {
  return email;
 }

 private void setEmail(Email email) {
  this.email = email;
 }

 private Phone getPhone() {
  return phone;
 }

 private void setPhone(Phone phone) {
  this.phone = phone;
 }

 static DefaultCompetitor of(Competitor competitor) {
  return new DefaultCompetitor(competitor.personId(), competitor.name(),
    competitor.lastName(), competitor.email(), competitor.phone());
 }
}
