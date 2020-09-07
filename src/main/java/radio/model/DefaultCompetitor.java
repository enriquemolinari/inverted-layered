package radio.model;

public class DefaultCompetitor implements Competitor {

 private int id;
 private String personId;
 private String name;
 private String lastName;
 private Email email;
 private Phone phone;
 private Integer points;
 
 public DefaultCompetitor(String personaId, String name, String lastName,
   String email, String phone) {
  this.personId = new NotNullNorEmpty<String>(personaId, "Id").value();
  this.name = new NotNullNorEmpty<String>(name, "Name").value();
  this.lastName = new NotNullNorEmpty<String>(lastName, "Last Name")
    .value();
  this.email = new Email(email);
  this.phone = new Phone(phone);
  this.points = 0;
 }
 
 protected DefaultCompetitor() {
  
 }
 
 private DefaultCompetitor(String personId) {
  this.personId = personId;
 }
 
 public void morePoints(int points) {
  if (points <= 0) {
   throw new RadioException("points must be positive number...");
  }
  this.points += points;
 }
 
 public int actualPoints() {
  return points;
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

 static DefaultCompetitor of(String personId) {
  return new DefaultCompetitor(personId);
 }
 
 static DefaultCompetitor of(Competitor competitor) {
  return new DefaultCompetitor(competitor.personId(), competitor.name(),
    competitor.lastName(), competitor.email(), competitor.phone());
 }

 private Integer getPoints() {
  return points;
 }

 private void setPoints(Integer points) {
  this.points = points;
 }

 @Override
 public int hashCode() {
  final int prime = 31;
  int result = 1;
  result = prime * result + ((personId == null) ? 0 : personId.hashCode());
  return result;
 }

 @Override
 public boolean equals(Object obj) {
  if (this == obj)
   return true;
  if (obj == null)
   return false;
  if (getClass() != obj.getClass())
   return false;
  DefaultCompetitor other = (DefaultCompetitor) obj;
  if (personId == null) {
   if (other.personId != null)
    return false;
  } else if (!personId.equals(other.personId))
   return false;
  return true;
 }
}
