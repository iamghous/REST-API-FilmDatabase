package com.democo.jersey.first;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enterprise.Film;
import enterprise.InsertFilm;
// this class will let us add film through rest Web Service and it will be called using Ajax
@Path("/addFilm")
public class addFilm {	
	// It will have the POST Method
	@POST
	// it can take either xml or json data
	@Consumes({MediaType.APPLICATION_XML , MediaType.APPLICATION_JSON})
	// and prints out Data in textHTML 
	@Produces(MediaType.TEXT_HTML)
	public String addFilmWithXmlAndJson(Film film) {
		// creating an instance of InsertFilm Class
		InsertFilm insert = new InsertFilm();
		// calling the Method of Insert Film and passing the return  value to String 
		String value = insert.insertFilmMethod(film);
		// returning the value
		return value;
	}
	
// it will be called using POST method
	@POST
	// it will have 6 parameters
	@Path("{filmYear}/{filmGross}/{filmName}/{filmCredits}/{filmGenre}/{filmCountry}")
	// it consumes TextHTML or textPlain
	@Consumes(MediaType.TEXT_HTML)
	// and produces Text_HTML
	@Produces(MediaType.TEXT_HTML)
	public String addFilmWithParamters(@PathParam("filmYear")String filmYear,@PathParam("filmGross")String filmGross,@PathParam("filmName")String filmName,
			@PathParam("filmCredits")String filmCredits, @PathParam("filmGenre")String filmGenre,@PathParam("filmCountry") String filmCountry) {
		// creating an instance of InsertFilm Class
		InsertFilm insert = new InsertFilm();
		// calling the Method of Insert Film and passing the return  value to String 
		String value = insert.insertFilmMethod(filmYear,filmGross,filmName,filmCredits,filmGenre,filmCountry);
		// returning the value
		return value;
	}
	
	
	
}
