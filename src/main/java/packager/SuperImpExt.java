package packager;


import java.sql.PreparedStatement;            
import java.sql.ResultSet;                     
import java.sql.SQLException;

import java.sql.Statement;            


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;      
import java.sql.DriverManager;                     
import java.util.Properties;
import java.util.Scanner;       

import org.apache.logging.log4j.LogManager;       
import org.apache.logging.log4j.Logger;              



public class SuperImpExt {
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	private static Connection connectionInstance = null;
	private static final Logger log = LogManager.getLogger(CustImpExt.class); 

	
	
	public static Connection getConnection() {             
		if (SuperImpExt.connectionInstance != null) {
			return SuperImpExt.connectionInstance;        
		}
	
		InputStream in = null;
	
		try {
			// load information from properties file
			Properties props = new Properties();           
			in = new FileInputStream("/var/www/Revature/Spring/projzero/src/main/resources/connection.properties"); 
			//in = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
			props.load(in);                            //I know Quinn gave us the automatic file finder
			
			// get the connection object
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = null;
	
			String endpoint = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");                   
			String password = props.getProperty("jdbc.password");

			
			con = DriverManager.getConnection(endpoint, username, password);        
			connectionInstance = con;
			return connectionInstance;
			} catch (Exception e) {
				log.error("Unable to get connection to database");
			} finally {
				try {
					in.close();                   //try to close if can't error
			} catch (IOException e) {
	
			}
		}
		return null;
	}
	
	
	
	
	
	
		 //seeAll account informations works.
	public void seeAll() {     //This is a superuser ability  done
		try {
			System.out.println("------------------To see all users on the plateform-----------------------"); 
			String queryString = "SELECT * FROM customer";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();               //result set is the query
					
			while (resultSet.next()) {
				System.out.println("Personsid: " + resultSet.getInt("personsid"));
				System.out.println("Name: " + resultSet.getString("personsname"));
				System.out.println("Age: "+ resultSet.getInt("personsage"));
				System.out.println("Phone_number: "+ resultSet.getString("personsphonenumber"));
				System.out.println("Address: "+ resultSet.getString("personsaddress"));
				System.out.println("Personsmoney: "+ resultSet.getFloat("personsmoney"));
				System.out.println("Password: "+ resultSet.getString("password"));
				System.out.println("-------------------------------");
				
			}
				    
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if (resultSet != null)
					resultSet.close();
				if (ptmt != null)
					ptmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}
	
	
	
	
	
	

	public void remove() {                                
		try {                                        
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Which account would you like to close (use account number) to select?");  
			int personsid = scan.nextInt();
			
			String queryString = "DELETE FROM customer where personsid = ? ";
			connection = getConnection();            
			 
			ptmt = connection.prepareStatement(queryString);   
			ptmt.setInt(1, personsid); 
			ptmt.executeUpdate();
			
			scan.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Data Removed Successfully");
			seeAll();                          
			
		}
		
	}
}
