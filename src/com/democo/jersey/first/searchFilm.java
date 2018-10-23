package com.democo.jersey.first;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enterprise.GetFilm;
@Path("/searchFilm")
public class searchFilm {

	// it will be called using GET method
	@GET
	// it takes one parameter which is filmName
	@Path("{filmName}")
	// it will produce HTML
	@Produces(MediaType.TEXT_HTML)
	// parameter will be passed in the method
	public String getFilmText(@PathParam("filmName") String filmName) throws Exception {
		// creating an instance of GetFilm class
		GetFilm getFilm = new GetFilm();
		// calling the method with desired format and filmName to search from the Map and it will return data in string
		String value = getFilm.searchFilmMethod(filmName, "structured");
		// Returns the String
		return value;
	}
	// Same structure as the First Method but produces XML
	@GET
	@Path("{filmName}")
	@Produces(MediaType.APPLICATION_XML)
	public String getFilmXml(@PathParam("filmName") String filmName) throws Exception {
		GetFilm getFilm = new GetFilm();
		String value = getFilm.searchFilmMethod(filmName, "xml");
		return value;
	}
	// Same structure the First Method but produces JSON
	@GET
	@Path("{filmName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFilmJson(@PathParam("filmName") String filmName) throws Exception {
		GetFilm getFilm = new GetFilm();
		String value = getFilm.searchFilmMethod(filmName, "json");
		return value;
	}
	
	
	
}
