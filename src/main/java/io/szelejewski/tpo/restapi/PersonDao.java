package io.szelejewski.tpo.restapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PersonDao {
  INSTANCE;

  private Map<String, List<Person>> surnamePersonMap = new HashMap<>();
  private Map<String, List<Person>> birthdatePersonMap = new HashMap<>();

  private PersonDao() {
    Person p1 = new Person("Oskar", "Szelejewski", LocalDate.parse("1989-03-19"));
    Person p2 = new Person("Jan", "Kowalski", LocalDate.parse("2000-01-01"));
    Person p3 = new Person("Jan", "Kowalski", LocalDate.parse("2001-01-01"));
    Person p4 = new Person("Jan", "Nowak", LocalDate.parse("2000-01-01"));
    Person p5 = new Person("Marcin", "Nowak", LocalDate.parse("2000-01-01"));
    addToMapList(surnamePersonMap, p1.getSurname(), p1);
    addToMapList(surnamePersonMap, p2.getSurname(), p2);
    addToMapList(surnamePersonMap, p3.getSurname(), p3);
    addToMapList(surnamePersonMap, p4.getSurname(), p4);
    addToMapList(surnamePersonMap, p5.getSurname(), p5);
    addToMapList(birthdatePersonMap, p1.getFormatedBirtdate(), p1);
    addToMapList(birthdatePersonMap, p2.getFormatedBirtdate(), p2);
    addToMapList(birthdatePersonMap, p3.getFormatedBirtdate(), p3);
    addToMapList(birthdatePersonMap, p4.getFormatedBirtdate(), p4);
    addToMapList(birthdatePersonMap, p5.getFormatedBirtdate(), p5);
  }

  public String getBySurname(String surname) {
    if (surname != null) {
      List<Person> l = surnamePersonMap.get(surname.toLowerCase().trim());
      if (l != null) {
        return createJsonString(l);
      }
    }
    return createJsonString(Collections.emptyList());
  }

  public String getByBirthdate(String birthdate) {
    if (birthdate != null) {
      List<Person> l = birthdatePersonMap.get(birthdate.trim());
      if (l != null) {
        return createJsonString(l);
      }
    }
    return createJsonString(Collections.emptyList());
  }

  private void addToMapList(Map<String, List<Person>> map, String key, Person p) {
    String lowerKey = key.toLowerCase().trim();
    if (map.containsKey(lowerKey)) {
      ((ArrayList<Person>) map.get(lowerKey)).add(p);
    } else {
      map.put(lowerKey, new ArrayList<>(Arrays.asList(p)));
    }
  }

  private String createJsonString(Collection<Person> c) {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"people\":[");
    for (Person p : c) {
      sb.append("{\"name\":\"" + p.getName() + "\", \"surname\":\"" + p.getSurname()
          + "\", \"birthdate\":\"" + p.getBirthdate().toString() + "\"},");
    }
    if (sb.length() > 11) {
      sb.setLength(sb.length() - 1);
    }
    sb.append("]}");
    return sb.toString();
  }
}
