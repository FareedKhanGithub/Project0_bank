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




//This is the face of the app, right before it sends you off to two different locations

                        //make sure to change the credentials for connection.util



















public class BankFaceApp {
	private static Connection connectionInstance = null;
	private static final Logger log = LogManager.getLogger(BankFaceApp.class);      
    

	
	
	

	public static void main(String[] args)throws SQLException{
	
	
	
		Scanner scan = new Scanner(System.in); 
		CustImpExt custobj = new CustImpExt();
		SuperImpExt superobj = new SuperImpExt(); 
		
		
		System.out.println("Select 1 to start new account");                            
		System.out.println("Select 2 to see your balance");        
		System.out.println("Select 3 to deposit or withdraw money from bank account");
		System.out.println("Select 0 to opt out to Superuser section");
		int choice;
		choice = scan.nextInt();
		 
		
		switch(choice){
			    case 1:
			        custobj.add();        //works securely uniques in place
			        break;
			    case 2:
			        custobj.Seeone();      //works securely with uniques in place singling out one account is do able
			        break;	        
			    case 3:
			        custobj.deposit();                 //maybe both withdraw or deposit          //works  withdrawal   
			        break;                                                //negative will go through chkconstriant                      
			    default:                                                     
			        System.out.println("Opting out of user application and going to SuperUser");
			        break;
		}   
		
		
		
		
		
		
		
		
		System.out.println("What is the passcode for the superuser?");
		int option;
		option = scan.nextInt();      	
		
		if(option == 3534) {
			System.out.println("Welcome to the Superuser section");
			System.out.println("Select 1 to delete accounts");
			System.out.println("Select 2 to see all accounts in the system");
			System.out.println("Select 0 to opt out");
			
			int superuserchoice;
			superuserchoice = scan.nextInt();
			
			switch(superuserchoice) {
				case 1:
					superobj.remove();              //superuser ability works      //using account number //this worked uncomment seeAll
					break;
				case 2:
					superobj.seeAll();                    //this worked straight                     
					break;
				default:
					System.out.println("leaving the superuser section now");
			}
		}   
		scan.close();
		}	
}
	
	
	
	
	
