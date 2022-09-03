import jdk.jfr.internal.PrivateAccess;

/**
 * 
 */

/**
 * @author Santiago
 *
 */
public class Customer {
	
	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	private String FirstName;
	private String lastName;
	private String userName;
	private String email;
	private double balance;
	private String password;
	

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * 
	 */
	public Customer() {
	
	}
	
	public Customer(String firstName, String lastName, String userName, String email, double balance, String password) {
		
		this.FirstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.balance = balance;
		this.password = password;
	}

}
