package io.szelejewski.tpo.restapi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
  private String name;
  private String surname;
  private LocalDate birthdate;
  private String formatedBirthdate;

  public Person() {}

  public Person(String name, String surname, LocalDate birthdate) {
    this.name = name;
    this.surname = surname;
    this.birthdate = birthdate;
    formatedBirthdate = birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public String getFormatedBirtdate() {
    return formatedBirthdate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthdate = birthDate;
  }
}
