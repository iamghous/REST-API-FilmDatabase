package enterprise;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// this class will make the connection to the database
public class ConnectDatabase {

	// this method will be used from other classes to get the connection
	public Connection connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException {	
		// declaring the connection variable to null
		Connection connection = null;
		
		// Now since we are using database pooling we won't be needing this bit of code 
		// however i have commented it out
		/*
		String user = "ghousn";
		String password = "rerRykil2";
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/"+user;
		try {  Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(url, user, password);  }
		 **/
		
		
		// using try catch to get all the errors nicely
		try {
		// creating a variable of Context class	
		Context context = new InitialContext();
		// creating a variable of Datasource Class which will get the values from context.xml
		DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/enterprise");
		// the connection will be passed to the connection variable
		connection = dataSource.getConnection();
	
		System.out.println("///////////////////Connection Opened/////////////////////");
		// this will print out the connection to console so you can see what is going on
		System.out.println(connection.toString());
	}
		catch(SQLException | NamingException se) {
			// this will return message if there is any error
		    se.getMessage();
		}
	// this will return the connection
	return connection;
	
}
}
