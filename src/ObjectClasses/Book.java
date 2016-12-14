package ObjectClasses;
public class Book {
	
private String BookTitle;
private String AuthorLastname;//maybe to separate first and last name for sake of prepared statement
private String AuthorFirstname;
private int ISBN;
private double Price;
private String Condition;
private int EntryNumber;//added for the sake of finding books by entry number
//private String edition;//might not be used or added in later
private int SellerAccNum;//added for the sake of finding posts by seller account number
private String SellerName;//added for the seeing the seller's name when book info is displayed

public Book(int EntryNumber, int SellerAccNum, String SellerName, String BookTitle, String AuthorFirstname, String AuthorLastname, int ISBN, double Price, String Condition)
{
	this.EntryNumber=EntryNumber;//added
	this.SellerAccNum=SellerAccNum;//added
	this.SellerName=SellerName;//added
	this.BookTitle=BookTitle;
	this.AuthorFirstname=AuthorFirstname;//added
	this.AuthorLastname=AuthorLastname;//added
	this.ISBN=ISBN;
	this.Price=Price;
	this.Condition=Condition;
}

public String getSellerName() {//added
	return SellerName;
}

public void setSellerName(String sellerName) {//added
	SellerName = sellerName;
}

public void setEntryNumber(int EntryNumber){//added
	this.EntryNumber=EntryNumber;
}

public String getAuthorLastname() {//added
	return AuthorLastname;
}

public void setAuthorLastname(String authorLastname) {//added
	AuthorLastname = authorLastname;
}

public String getAuthorFirstname() {//added
	return AuthorFirstname;
}

public void setAuthorFirstname(String authorFirstname) {//added
	AuthorFirstname = authorFirstname;
}

public int getEntryNumber(){//added
	return EntryNumber;
}

public void setSellerAccNum(int SellerAccNum){//added
	this.SellerAccNum=SellerAccNum;
}

public int getSellerAccNum(){//added
	return SellerAccNum;
}

public void setBookTitle(String BookTitle)
{
	this.BookTitle=BookTitle;
}
public String getBookTitle()
{
	return BookTitle;
}

public void setISBN(int ISBN) {
	this.ISBN = ISBN;
}
public int getISBN() {
	return ISBN;
}

public void setPrice(double Price) {
	this.Price = Price;
}
public double getPrice() {
	return Price;
}

public void setCondition(String Condition) {
	this.Condition = Condition;
}
public String getCondition() {
	return Condition;
}

public String BooktoString(){//added to format the output of book objects
	String format="Entry Number: "+EntryNumber+" Title:\t"+BookTitle+
			"\nAuthor:\t"+AuthorFirstname+" "+AuthorLastname+
			"\nCondition:\t"+Condition+
			"\nISBN:\t"+ISBN+
			"\nSeller:\t"+SellerName+
			"\nPrice:"+Price+"\n";
	return format;
}






}
