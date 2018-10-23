package enterprise;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class QueryDatabase {
// Creates an object of Connection, Statement and ResultSet and assign null value 
private Connection connection= null;
private Statement stmt = null;
private ResultSet result = null;
	// this method will return ResultSet and take a String parameter
	public ResultSet getDataFromDatabase(String query) {
		try {
		// create an object of our ConnectDataBase Class
		ConnectDatabase connectDatabase = new ConnectDatabase();
		// call the method of ConnectDatabase class and assign the connection to connection variable
		connection = connectDatabase.connect();
		// creates the statement and pass it to the statement variable
		stmt = connection.createStatement();
		// prints out the success message
		System.out.println("DEBUG: Select: " + query);
		// gets the data and gives it to the result object
		result = stmt.executeQuery(query);
		} // catching all sort of errors here
		catch(InstantiationException| IllegalAccessException| ClassNotFoundException| SQLException e) {
			System.out.println(e);
		}
		// return ResultSet
		return result;
		
	}
public void insertDataToDatabase(String query)  {
	  try {
		// create an object of our ConnectDataBase Class
			ConnectDatabase connectDatabase = new ConnectDatabase();
			// call the method of ConnectDatabase class and assign the connection to connection variable
			connection = connectDatabase.connect();
			// creates the statement and pass it to the statement variable
			stmt = connection.createStatement();
		// exectues the update and checks and gets the int value of how many rows it has updated
		int check = stmt.executeUpdate(query);	
		// if check is 0 than all good and it will print the success message out
		if (check > 0) {
			System.out.println("DEBUG: Inserted: " + query);
		}
		// otherwise it will print out the sorry message
		else {
			System.out.println("Sorry, could not insert the data into database, please make sure the Film Does not exist already");
		}
	  }// catching all sort of errors here
		catch(InstantiationException| IllegalAccessException| ClassNotFoundException| SQLException e) {
			System.out.println(e);
		}
	}
/*
 * What this method does is that it check if Statement, ResultSet or Connection are open and if they are 
 * it will close them one by one, which is good programming technique
 */
public void closeConnection() throws SQLException {
	// will create a string which will be used to show the user what has been closed
	String data = "";
	// checks if statement is not null
	if(stmt != null) {
		// closes the statement
	stmt.close();
	data += "Statement - ";
	}
	// checks if resultSet is not null
	if(result != null) {
		// closes the ResultSet
	result.close();
	data += "Result - ";
	}
	// checks if connection is not null
	if(connection != null)
	{
		// closes the Connection
	connection.close();
	data += "Connection";
	}
	// prints out the message of what has been closed 
	System.out.println("/////////////////// " +data +" closed////////////////// \n \n");
	
	
}
	
}
