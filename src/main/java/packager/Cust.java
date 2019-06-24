package packager;                 


//This is the data transfer object
    /*    
    Model Object or Value Object -
    This object is simple POJO containing get/set methods to store data retrieved using DAO class.
  */        


import java.io.Serializable;




public class Cust implements Serializable {    
	private static final long serialVersionUID = 1L;
	
    private int personsid;     
    private String personsname;                    
    private int personsage;              
    private String personsphonenumber;                   
    private String personsaddress;
    private int personsmoney;
    private String password;
						
    public Cust() {	
    }
    
    public Cust(Integer personsid, String personsname, Integer personsage, String personsphonenumber, String personaddress, Integer personsmoney, String password) {
        this.personsid = personsid;
        this.personsname = personsname;
        this.personsage = personsage;
        this.personsphonenumber = personsphonenumber;
        this.personsaddress = personaddress;
        this.personsmoney = personsmoney; 
        this.password = password;
    }
    
       
    public Integer getpersonsId() {             //P caps  naming convention    
        return personsid;
    }
    
    public void setpersonsId(Integer personsid) {
        this.personsid = personsid;
    }
    
    
    
    
    public String getpersonsName() {
        return personsname;
    }
    public void setpersonsName(String personsname) {
        this.personsname = personsname;
    }
    
    
    
    
    
    
    public Integer getpersonsAge() {
        return personsage;
    }
    
    public void setpersonsAge(Integer personsage) {
        this.personsage = personsage;
    }
    
    
    
    
    
    
    
    
    public String getpersonsphonenumber() {
        return personsphonenumber ;
    }
    
    public void setpersonsphonenumber(String personsphonenumber) {
        this.personsphonenumber = personsphonenumber;
    }
    
    
    
    
    
    public String getpersonsaddress() {
        return personsaddress;
    }
   
    public void setpersonsaddress(String personsaddress) {
        this.personsaddress = personsaddress;
    }
    
    
    
    public Integer getpersonsmoney() {
        return personsmoney;
    }
   
    public void setpersonsmoney(Integer personsmoney) {
        this.personsmoney = personsmoney;
    }
    
    
    
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    
   
    
}	
	
	
	
	
	
	
