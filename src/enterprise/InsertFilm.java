package enterprise;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import com.builder.FilmBuilder;



public class InsertFilm {
	// this method will return String and take an object of film class as paramter 
	public String insertFilmMethod(Film film) {
		
		 // creating an empty string which will be returned at the end
		String outputData = "";
		 // create an object of FilmDAO class
		 FilmInfo filmDAO = new FilmDAO();
		 // create a linked hashMap and pass it the method of filmDAO class which returns all the movies in Hash
		 LinkedHashMap<String,Film> filmCollectionMap = filmDAO.listFilm(); 
		/*
		 * this will get all the parameters and put them in the string
		 */
		 int filmYear = film.getFilmYear();
		 int filmGross = film.getFilmGross();
		 String filmName = film.getFilmName().toUpperCase();
		 String filmCredits = film.getFilmCredits();
		 String filmGenre = film.getFilmGenre();
		 String filmCountry = film.getFilmCountry();
		 // just printing out the film object using its toString method for testing purposes
		 System.out.println(film);
		 // this for loop will check if the user have left any field empty
		 if( filmYear == 0 || filmGross == 0 || filmName.isEmpty() || filmCredits.isEmpty() || filmGenre.isEmpty() || 
				 filmCountry.isEmpty())
		 {
			outputData += "Please make sure you enter all the details";
		 }
		 // this for loop will check if the movie already exist in the system and if it does, user cannot enter it again
		 else if(filmCollectionMap.containsKey(filmName)) {
			 outputData += "This movie already exists in the system, please enter a different one!";
		 }
	    else {
	    	
			// this little method from Calender class will give us 13 digit unique id whenever the user insertFilm
			 Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			 // so it basically gets the time in miliseconds and however it is always unique
			 // it will be assigned to long because int data type cannot handle 13 digit number
				long filmId = calendar.getTimeInMillis();
				/* here we are using BUILDER DESIGN PATTERN technique
				 * we will call our FilmBuilder class which is in com.builder package 
				 *  rather than using film class constructor and passing all the values which are required but instead we
				 *  will  use FilmBuilder class which allows us	not pass all the values which is a bit of relief in most cases			 
				 */ 		
	Film film1 = new FilmBuilder().setFilmId(filmId).setFilmYear(filmYear).setFilmGross(filmGross).setFilmName(filmName).setFilmCredits(filmCredits).setFilmGenre(filmGenre).setFilmCountry(filmCountry).getFilmValues();
	
	/* we can also use our old style technique but for this part of the assignment i will just
	 * use the builder design pattern
	 */				
		// Film film = new Film(filmId, filmYear1,filmGross1,filmName,filmCredits,filmGenre,filmCountry);
	// and it will simply call the method of filmDAO class which will take film object paramter
		 filmDAO.addFilm(film1);
		 
		 // once it is done the user will get the success message
		 outputData += "Successfully added the "+filmName +" into the System!";

		 // closes the printWeiter
		
		 }
		return outputData; 
	}
	// this method will take 6 parameters and it will be called form addFilms class
	public String insertFilmMethod(String filmYear,String filmGross,String filmName1,
			String filmCredits,String filmGenre, String filmCountry )  {
		// upper case the filmName
		String filmName = filmName1.toUpperCase();
		// create an empty string which will be returned at the end
		String outputData = "";
		 // create an object of FilmDAO class
		 FilmInfo filmDAO = new FilmDAO();
		 // create a linked hashMap and pass it the method of filmDAO class which returns all the movies in Hash
		 LinkedHashMap<String,Film> filmCollectionMap = filmDAO.listFilm(); 
		/*
		 * this will get all the parameters and put them in the string
		 */
		System.out.println(filmName);
		System.out.println(filmCollectionMap.keySet());
		 
		 // this for loop will check if the user have left any field empty
		if( filmYear.isEmpty() || filmGross.isEmpty() || filmName.isEmpty() || filmCredits.isEmpty() || filmGenre.isEmpty() || 
				 filmCountry.isEmpty())
		 {
			outputData += "Please make sure you enter all the details";
		 }
		 // this for loop will check if the movie already exist in the system and if it does, user cannot enter it again
		 else if(filmCollectionMap.containsKey(filmName)) {
			 outputData += "This movie already exists in the system, please enter a different one!";
		 }
	    else {
	    	int filmYear1 = Integer.valueOf(filmYear);
	    	int filmGross1 = Integer.valueOf(filmGross);
	    	
			// this little method from Calender class will give us 13 digit unique id whenever the user insertFilm
			 Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			 // so it basically gets the time in miliseconds and however it is always unique
			 // it will be assigned to long because int data type cannot handle 13 digit number
				long filmId = calendar.getTimeInMillis();
				/* here we are using BUILDER DESIGN PATTERN technique
				 * we will call our FilmBuilder class which is in com.builder package 
				 *  rather than using film class constructor and passing all the values which are required but instead we
				 *  will  use FilmBuilder class which allows us	not pass all the values which is a bit of relief in most cases			 
				 */ 		
	Film film = new FilmBuilder().setFilmId(filmId).setFilmYear(filmYear1).setFilmGross(filmGross1).setFilmName(filmName).setFilmCredits(filmCredits).setFilmGenre(filmGenre).setFilmCountry(filmCountry).getFilmValues();
	
	/* we can also use our old style technique but for this part of the assignment i will just
	 * use the builder design pattern
	 */				
		// Film film = new Film(filmId, filmYear1,filmGross1,filmName,filmCredits,filmGenre,filmCountry);
	// and it will simply call the method of filmDAO class which will take film object parameter
		 filmDAO.addFilm(film);
		 
		 // once it is done the user will get the success message
		 outputData += "Successfully added the "+filmName +" into the System!";

		 // closes the printWeiter
		
		 }
		return outputData; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
