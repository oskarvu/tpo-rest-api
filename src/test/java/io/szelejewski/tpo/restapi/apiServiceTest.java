package io.szelejewski.tpo.restapi;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class apiServiceTest {

  // Currently in map
  // Oskar Szelejewski 1989-03-19
  // Jan Kowalski 2000-01-01
  // Jan Kowalski 2001-01-01
  // Jan Nowak 2000-01-01
  // Marcin Nowak 2000-01-01

  private static String surnamePath = "/by-surname";
  private static String birthdatePath = "/by-birthdate";

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://localhost:8080";
    RestAssured.basePath = "/restapi/get-people";
  }

  @Test
  public void correctQuery_correctStatusCodeAndType() {
    RestAssured.expect().statusCode(200).contentType(ContentType.JSON).when()
        .get(surnamePath + "?surname=kowalski");
  }

  // by-surname
  @Test
  public void surname_wrongSurname_responseIsEmptyArray() {
    RestAssured.get(surnamePath + "?surname=wrong").then().body("people", hasSize(0));
  }

  @Test
  public void surname_singleMatchInMap_responseHasSingleElement() {
    RestAssured.get(surnamePath + "?surname=szelejewski").then().body("people.name",
        hasItems("Oskar"));
  }

  @Test
  public void surname_manyPeopleWithSameSurname_responseHasCorrectNumOfPeople() {
    RestAssured.get(surnamePath + "?surname=Nowak").then().body("people.name",
        hasItems("Jan", "Marcin"));
    RestAssured.get(surnamePath + "?surname=Kowalski").then().body("people", hasSize(2));
  }

  // by-birthdate
  @Test
  public void birthdate_wrongBirtdate_responseIsEmptyArray() {
    RestAssured.get(birthdatePath + "?birthdate=wrong").then().body("people", hasSize(0));
  }

  @Test
  public void birthdate_singleMatchInMap_responseHasSingleElement() {
    RestAssured.get(birthdatePath + "?birthdate=1989-03-19").then().body("people.name",
        hasItems("Oskar"));
  }

  @Test
  public void birthdate_manyPeopleWithSameBirthdate_responseHasCorrectNumOfPeople() {
    RestAssured.get(birthdatePath + "?birthdate=2000-01-01").then().body("people", hasSize(3));
  }

}
