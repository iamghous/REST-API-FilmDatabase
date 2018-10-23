package enterprise;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;

import com.factory.FactoryMethod;
import com.factory.ReturnData;




public class GetAllFilms {
	// doGet method of servlet
public String allFilmsMethod(String format) throws ServletException, IOException {
		// it will create a String which we will return at the end
		String outputData = "";
		
		
		 // it will create an object of FilmDAO class
		 FilmInfo filmDAO = new FilmDAO();
		// OutputGenerator g = new OutputGenerator();
		 // it will create an object of our FactoryMethod class
		 FactoryMethod factory = new FactoryMethod();
		 // it will create a hashMap and call the method of FilmDAO which will return the hashMap of all the films
		 LinkedHashMap<String,Film> filmCollectionMap = filmDAO.listFilm();
		 
		 
			// the for loop will check if the request format is xml
		  if (format.equalsIgnoreCase("xml")) {
			  try {
				 /* it will call the method of factory class which returns the ReturnData object
				  * this is here we will use FACTORY DESIGN PATTERN
				  * and it will return an object of XMLData class and it will be ReturnData returnData = new XmlData();
				  */
				  ReturnData returnData = factory.checkedData(format);
				  // and it will assign the output to String 
				  outputData += "<textarea>";
				  outputData += returnData.generateData(filmCollectionMap);
				  outputData += "</textarea>";
				  // we will not use MVC design pattern but we will instead use factory design pattern
				//outputData = g.generateXml(filmCollectionMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			  // it will send the output in texarea blocks so, the user can easily read it 
			  
		  }
		  // this part is almost same as the previous for loop but it is for json instead of xml
		  else if(format.equalsIgnoreCase("json")) {
			  try {
				  // this will return JsonData object so it will be ReturnData returnData = new JsonData();
				  ReturnData returnData = factory.checkedData(format);
				  outputData += "<textarea>";
				  outputData += returnData.generateData(filmCollectionMap);
				  outputData += "</textarea>";
				//outputData = g.generateJson(filmCollectionMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			  
			  
		  }
		  // and else loop will be for the structured type
		  else if(format.equalsIgnoreCase("structured")) {
			  try {
				// this will return JsonData object so it will be ReturnData returnData = new StructuredData();
				  ReturnData returnData = factory.checkedData(format);
				  outputData += returnData.generateData(filmCollectionMap);
					//outputData = g.generateStructured(filmCollectionMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
				  
				 
			  }
		return outputData;
		 
		
		  }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


