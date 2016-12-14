package ObjectClasses;


public class UserObject {

	private String firstName, lastName, address, billingAddress, creditNum, email;
	
	public UserObject( String firstName, String lastName, String address, String billingAddress, String creditNum, String email)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.address=address;
		this.billingAddress=billingAddress;
		this.creditNum=creditNum;
		this.email=email;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public String displayUserInfo()
	{
		String UserInfo= firstName+" "+lastName+" "+email+" "+address+" "+billingAddress+" "+creditNum;
		return UserInfo;
	}
	
}
