package enterprise;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import com.builder.FilmBuilder;

// FilmDAO implements FilmInfo which means it gets all of his methods implemented in this class
public class FilmDAO implements FilmInfo {

	// creates a global linkedHashMap variable which will hold all the film objects and use film name
	// to find the film object
	private LinkedHashMap<String,Film> filmCollectionMap =new LinkedHashMap<String,Film>();
	
	
	// this method will query the database and get all the films and insert them nicely into the 
	// the linkedHashMap and return them
	@Override
	public LinkedHashMap<String, Film> listFilm() {
		// Creating a variable of our QueryDatabase class which manages all the database queries
		QueryDatabase queryDatabase = new QueryDatabase(); 
		// Creating a variable of ResultSet class and assign it to null
		ResultSet rs = null;
		try {
			// we will select all the data from NomanFilms table
			String query = "select * from NomanFilms";
			// it will call the method of QueryDatabase class with the given query and it will return
			// ResultSet and we will assign it to local ResultSet variable
			rs = queryDatabase.getDataFromDatabase(query);
		    // We will use while loop to go through all the rows
			while (rs.next()) {
				// we will assign all the values to local variable just to keep things simple and understandable
				 long filmId = rs.getLong("filmId");
				 int  filmYear = rs.getInt("filmYear");int  filmGross = rs.getInt("filmGross");
				 String filmName = rs.getString("filmName");  String filmCredits = rs.getString("filmCredits");
				 String filmGenre = rs.getString("filmGenre");  String filmCountry = rs.getString("filmCountry");
			    
	/* here we are using BUILDER DESIGN PATTERN technique
	 * we will call our FilmBuilder class which is in com.builder package 
	 *  rather than using film class constructor and passing all the values which are required but instead we
	 *  will  use FilmBuilder class which allows us	not pass all the values which is a bit of relief in most cases			 
	 */
       Film film = new FilmBuilder().setFilmId(filmId).setFilmYear(filmYear).setFilmGross(filmGross).setFilmName(filmName).setFilmCredits(filmCredits).setFilmGenre(filmGenre).setFilmCountry(filmCountry).getFilmValues();
			/* we can also use our old style technique but for this part of the assignment i will just
			 * use the builder design pattern
			 */
 			//Film film = new Film(rs.getLong("filmId"), rs.getInt("filmYear"), rs.getInt("filmGross")	, rs.getString("filmName"),rs.getString("filmCredits"),rs.getString("filmGenre"),rs.getString("filmCountry"));
			// now we will add the film object in the value of  LinkedHashMap and we will use film name as the key     
           filmCollectionMap.put(filmName,film);
		}
			/* now once all the data retrieval is done and in place we can close all the open Connection,
			 * ResultSet and Statements, this will call the close connection of our QueryDatabase class
			 */
			queryDatabase.closeConnection();
		}
		// catching all kind of exceptions here
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		// and this will return LinkedHashMap 
		return filmCollectionMap;
	}

	// this method will insert film in the database 
	@Override
	public void addFilm(Film fInfo) {
		// we will create and object of our QueryDatabase class
		QueryDatabase queryDatabase = new QueryDatabase();
		// we will create the query and assign it to the string and get the values from given Film object parameter
		String query = 
		     	"insert into NomanFilms(filmId, filmYear ,filmGross, filmName, filmCredits,filmGenre,filmCountry) "+
		      	"values("+fInfo.getFilmId()+ "," + fInfo.getFilmYear()+","+fInfo.getFilmGross()+",'"+fInfo.getFilmName()+"','"+fInfo.getFilmCredits()+"','"+fInfo.getFilmGenre()+"','"+ fInfo.getFilmCountry() +"');";
		  try {
			  		// calling the insert method of queryDatabase class with the String query parameter
					queryDatabase.insertDataToDatabase(query);
					/* now once method is called we can close the open Connection,
					 * ResultSet and Statements, this will call the close connection of our QueryDatabase class
					 */
					queryDatabase.closeConnection();
				} 
		  // catching all the errors here nicely
		  catch (SQLException e) {
				
					e.printStackTrace();
				}
	}

	//  This method will return LinkedHashMap and take a string parameter it will be used to search the film
	// from the collection
	@Override
	public LinkedHashMap<String, Film> searchFilm(String searchStr) {
		// it will create a new LinkedHashMap
		LinkedHashMap<String, Film> searchedFilmMap = new LinkedHashMap<String, Film>();
		// this if statement will find the given film name from teh global linkedHasMap just to check if key exist
		if(filmCollectionMap.containsKey(searchStr)) {
			// if it does then will assign the film Object using linkedHaspmap.get method
			Film film = filmCollectionMap.get(searchStr);
			// and it will put the searched film into the new hashMap which we created earlier
			searchedFilmMap.put(searchStr, film);
		}
		// if the Map is empty it will print no data found
		if (searchedFilmMap.isEmpty()) { 
			System.out.println("No data Found"); }
		// if not then it will pass the the map value to the Film Object, just to show the output to console
		else {
			Film film =searchedFilmMap.get(searchStr);
			// it will call the method of Film class and assign the value to String
			String output = film.toString();
			// it will print out the searched film
			System.out.println("Searched "+ output );
		}
		// and finally it will return the searchedFilmMap
		return searchedFilmMap;
		
		
	}	
}
