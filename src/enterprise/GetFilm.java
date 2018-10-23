package enterprise;
import java.util.LinkedHashMap;

import com.factory.FactoryMethod;
import com.factory.ReturnData;




public class GetFilm {

	public String searchFilmMethod(String search , String format) {
		 
		
		// it will create a String which we will return at the end
			String outputData = "";
			// it will pass the search parameter to String and Upper Case it
			 String searched = search.toUpperCase();
			// it will get the request format and assign it to the string
			 
			 // it will create an object of FilmDAO class
			 FilmInfo filmDAO = new FilmDAO();
			// OutputGenerator g = new OutputGenerator();
			 // it will create an object of our FactoryMethod class
			 FactoryMethod factory = new FactoryMethod();
			 // it will create a hashMap and call the method of FilmDAO which will return the hashMap of all the films
			 LinkedHashMap<String,Film> filmCollectionMap = filmDAO.listFilm();
			 // an object of printWtiter class
			 
				// the for loop will check if the request format is xml
		 if(searched.isEmpty()) {
			 outputData += "Please make sure you add something in the search input.";
		 }
		 
		 else if(!(filmCollectionMap.containsKey(searched))) {
			
			 outputData += "Unfortunately, we do not have "+ searched +" in the system yet.";
		 }
		 else {
			 LinkedHashMap<String,Film> searchedHashMap = filmDAO.searchFilm(searched);
			 if (format.equalsIgnoreCase("xml")) {
				  try {
					  /* it will call the method of factory class which returns the ReturnData object
						  * this is here we will use FACTORY DESIGN PATTERN
						  * and it will return an object of XMLData class and it will be ReturnData returnData = new XmlData();
						  */
						  ReturnData returnData = factory.checkedData(format);
						  // and it will assign the output to String method
						  outputData += "<textarea>";
						  outputData += returnData.generateData(searchedHashMap);
						  outputData += "</textarea>";
						  // we will not use MVC design pattern but we will instead use factory design pattern
						//outputData = g.generateXml(filmCollectionMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				  // sends the response back and add the response in text area so user can analyze it 
				  
				  
				  
			  }
			  else if(format.equalsIgnoreCase("json")) {
				  try {
					// this will return JsonData object so it will be ReturnData returnData = new JsonData();
					  ReturnData returnData = factory.checkedData(format);
					  outputData += "<textarea>";
					  outputData += returnData.generateData(searchedHashMap);
					  outputData += "</textarea>";
					//outputData = g.generateJson(filmCollectionMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				  // sends the response back and add the response in text area so user can analyze it 
			  }
			  else if(format.equalsIgnoreCase("structured")) {
				  try {
					// this will return JsonData object so it will be ReturnData returnData = new StructuredData();
					  ReturnData returnData = factory.checkedData(format);
					  outputData += returnData.generateData(searchedHashMap);
						//outputData = g.generateStructured(filmCollectionMap);
					} catch (Exception e) {
						e.printStackTrace();
					}
					  
					 
			  }
			  } 
		 return outputData;
		 }	 
	}
