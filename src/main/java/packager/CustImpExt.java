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
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;           

import org.apache.logging.log4j.LogManager;       
import org.apache.logging.log4j.Logger;                






public class CustImpExt extends Cust{              //remove the extends Cust
	private static final long serialVersionUID = 1L;       
	Connection connection = null;
	PreparedStatement ptmt = null;
	ResultSet resultSet = null;
	private static Connection connectionInstance = null;
	private static final Logger log = LogManager.getLogger(CustImpExt.class); 
	public CustImpExt() {
	}
	
	

public static Connection getConnection() {                        
		if (CustImpExt.connectionInstance != null) {
			return CustImpExt.connectionInstance;        
		}
	
		InputStream in = null;
	
		try {
			// load information from properties file
			Properties props = new Properties();          
			in = new FileInputStream("/var/www/Revature/Spring/projzero/src/main/resources/connection.properties");
			
			//in = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
			props.load(in);                            //copy and paste Quinns auto path but direct path is more preferable
																
			
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
				in.close();                  
			} catch (IOException e) {
	
			}
		}
		return null;
	}
	
	
	
	
	
	

	public void add(){
		try {                                            
			                                                  
			 Scanner scan = new Scanner(System.in);     
			 System.out.println("ENTER PRIMARY_ID?");         
			 int personsid = scan.nextInt();       
			 setpersonsId(personsid);
			 
			 scan.nextLine();                      
			 System.out.println("What is your name?");
			 String personsname = scan.nextLine();
			 setpersonsName(personsname);
			 
			                     
			 
			 System.out.println("What is your age?");    
			 int personsage = scan.nextInt();            
			 setpersonsAge(personsage);
			 scan.nextLine(); 
			 
			            
			 System.out.println("What is your phone number?"); 
			 String personsphonenumber = scan.nextLine();
			 setpersonsphonenumber(personsphonenumber);
			 scan.nextLine(); 
			 
			 
			
			System.out.println("What is your address?"); 
			String personsaddress = scan.nextLine();  
			setpersonsaddress(personsaddress);
			scan.nextLine(); 
			 
			
			
			System.out.println("How much money do you want to put in?");
			int personsmoney = scan.nextInt();
			setpersonsmoney(personsmoney);
			scan.nextLine(); 
		
			
			
			System.out.println("Password");     
			String password = scan.nextLine();                
			setpassword(password);
			scan.nextLine();
			 	
			String queryString = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
			connection = getConnection();   
			                
         
		
			/*For example stmt.setString(1, user); will convert the user parameter to a String.
			Suppose that the parameter contains a SQL string containing an executable command: 
			using a prepared statement will not allow that.
			It adds metacharacter (a.k.a. auto conversion) to that.   */
			
			ptmt = connection.prepareStatement(queryString);    
			ptmt.setInt(1,personsid);
			ptmt.setString(2,personsname);
			ptmt.setInt(3,personsage);                                
			ptmt.setString(4,personsphonenumber);               
			ptmt.setString(5,personsaddress);
			ptmt.setInt(6,personsmoney); 
			ptmt.setString(7,password);  
			ptmt.executeUpdate();                         
			
			scan.close();	
			System.out.println("Data Added Successfully");	
			connection.close();
			
		} 
		
		                                    
		catch (SQLException e) { if(e.getMessage().contains("unique constraint"))      
			{ System.out.println("Start over with a different Unique id and password or with a person of an age beyond 18"); 
			}}             
		
		
		
		catch (Exception e) {     
		 System.out.println("This means there are errors"); 
		}  
		
	
		
		finally {
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
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	         //This is also withdrawal as well
	public void deposit() {           
		try {
			Scanner scan = new Scanner(System.in); 
			
			System.out.println("How much money would you like to withdraw or deposit");
			int personsmoney = scan.nextInt();
			
			
			
			scan.nextLine();
			System.out.println("what is your password");                 
			                                                                 
			String password = scan.nextLine();	
			scan.nextLine();                                   //usually need one of these for strings to absorb /n char
			
			
			
			connection = getConnection();
			String queryString = "update customer set personsmoney = ? where password = ?";     
			                                                                                        
		
			ptmt = connection.prepareStatement(queryString);          
			
			ptmt.setInt(1, personsmoney);                                                  
			ptmt.setString(2, password);                       
			
			resultSet = ptmt.executeQuery();               
			
			ptmt.executeUpdate();
			connection.setAutoCommit(true);

			
			System.out.println("Data Added Successfully");	
			
			scan.close();	
			
			connection.close();
			
				    
		
		} catch (SQLException e) {
			System.out.println("Withdrew more money than the bank had stored for you");
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
	
	

	
	
	
	
	
   
	public void Seeone() {                                      
		try {
			Scanner scan = new Scanner(System.in); 
			
			System.out.println("Password?");     
			String password = scan.nextLine();                
			scan.nextLine();
			
			String queryString = "SELECT * FROM customer WHERE password= ?";   
		
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			  
			ptmt.setString(1,password);       //each password is unique
		
			resultSet = ptmt.executeQuery();              
					
			if(resultSet.next()) {
				System.out.println("Personsid: " + resultSet.getInt("personsid"));
				System.out.println("Name: " + resultSet.getString("personsname"));
				System.out.println("Age: "+ resultSet.getInt("personsage"));
				System.out.println("Phone_number: "+ resultSet.getString("personsphonenumber"));
				System.out.println("Address: "+ resultSet.getString("personsaddress"));
				System.out.println("Personsmoney: "+ resultSet.getFloat("personsmoney"));
				System.out.println("Password: "+ resultSet.getString("password"));
				System.out.println("-------------------------------");
			}
			
			else {
				System.out.println("there was nothing like that in our database try again");
			}
			scan.close();
		} 
		
		
		
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
			if (resultSet != null){
				resultSet.close();
					if (ptmt != null) {
						ptmt.close();
					}
					if (connection != null) {
						connection.close();
					}
			}
			     }
			
			catch (SQLException e) {
				e.printStackTrace();
			     } 		
			catch (Exception e) {        
				System.out.println("Not the right action");
				e.printStackTrace();
			}
		}	
	}
	
	
	
	
	
	
	

	
	
	
	    
	private Cust extractUserFromResultSet(ResultSet rs) throws SQLException {          //this is like for get results back from private local
		//database
		Cust user = new Cust();
		user.setpersonsId(rs.getInt("personsid"));                   
		user.setpersonsName(rs.getString("personsname"));            
		user.setpersonsAge(rs.getInt("personsage"));
		user.setpersonsphonenumber(rs.getString("personsphonenumber"));
		user.setpersonsaddress(rs.getString("personsaddress"));
		user.setpassword(rs.getString("password"));
		return user;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
