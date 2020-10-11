package io.szelejewski.tpo.restapi;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/get-people")
public class apiService {
  @GET
  @Path("/by-surname")
  public Response getPeopleBySurname(@QueryParam("surname") String surname) {
    String jsonString = PersonDao.INSTANCE.getBySurname(surname);
    return Response.ok(jsonString).type(MediaType.APPLICATION_JSON).build();
  };

  @GET
  @Path("/by-birthdate")
  public Response getPeopleByBirthdate(@QueryParam("birthdate") String birthdate) {
    String jsonString = PersonDao.INSTANCE.getByBirthdate(birthdate);
    return Response.ok(jsonString).type(MediaType.APPLICATION_JSON).build();
  };
}
