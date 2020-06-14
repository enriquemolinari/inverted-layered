package ar.cpfw.book.radio.model;

import java.time.LocalDateTime;

public class Inscription {

 private int id;
 private DefaultCompetitor competitor;
 private LocalDateTime date;

 public Inscription(DefaultCompetitor competitor, LocalDateTime inscriptionDate) {
  this.competitor = competitor;
  this.date = inscriptionDate;
 }

 protected Inscription() {
  
 }
 
 public String inscriptionDate() {
  return date.toString();
 }

 boolean isEnrolled(String personId) {
  return this.competitor.equals(DefaultCompetitor.of(personId));
 }
 
 private DefaultCompetitor getCompetitor() {
  return competitor;
 }

 private void setCompetitor(DefaultCompetitor competitor) {
  this.competitor = competitor;
 }

 private LocalDateTime getDate() {
  return date;
 }

 private void setDate(LocalDateTime date) {
  this.date = date;
 }

 private int getId() {
  return id;
 }

 private void setId(int id) {
  this.id = id;
 }
}
