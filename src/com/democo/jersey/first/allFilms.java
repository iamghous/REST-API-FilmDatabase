package com.democo.jersey.first;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import enterprise.GetAllFilms;

@Path("/allFilms")
public class allFilms {

	// This method is called if TEXT_PLAIN is request
	// it will be called using GET Method
	@GET
	// it produces HTML
	@Produces(MediaType.TEXT_HTML)
	public String getAllHTML() throws Exception {
		// creating an instance of GetAllFilms class
		GetAllFilms getAllFilms = new GetAllFilms();
		/* Calling the method of InsertFilm class which returns the data in string format 
		 * and it will pass a parameter and the method will determine what data needs to be sent back
		 */
		String value = getAllFilms.allFilmsMethod("structured");
		// return the String
		return value;
	}
	
	/*
	 * Same structure the First Method but it produces JSON
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllJson() throws Exception {
		GetAllFilms getAllFilms = new GetAllFilms();
		String value = getAllFilms.allFilmsMethod("json");
		return value;
	}
	/*
	 * Same structure the First Method but it produces XML
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getAllXml() throws Exception {
		GetAllFilms getAllFilms = new GetAllFilms();
		String value = getAllFilms.allFilmsMethod("xml");
		return value;
	}
	
	
	

}
