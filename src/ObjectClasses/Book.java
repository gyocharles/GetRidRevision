package ObjectClasses;
public class Book {
	
private String BookTitle;
private String AuthorLastname;
private String AuthorFirstname;
private String ISBN;
private double Price;
private String Condition;
private int EntryNumber;
private int SellerAccNum;
private String SellerName;

public Book(int EntryNumber, int SellerAccNum, String SellerName, String BookTitle, String AuthorFirstname, String AuthorLastname, String iSBN2, double Price, String Condition)
{
	this.EntryNumber=EntryNumber;
	this.SellerAccNum=SellerAccNum;
	this.SellerName=SellerName;
	this.BookTitle=BookTitle;
	this.AuthorFirstname=AuthorFirstname;
	this.AuthorLastname=AuthorLastname;
	this.ISBN=iSBN2;
	this.Price=Price;
	this.Condition=Condition;
}

public String getSellerName() {
	return SellerName;
}

public void setSellerName(String sellerName) {
	SellerName = sellerName;
}

public void setEntryNumber(int EntryNumber){
	this.EntryNumber=EntryNumber;
}

public String getAuthorLastname() {
	return AuthorLastname;
}

public void setAuthorLastname(String authorLastname) {
	AuthorLastname = authorLastname;
}

public String getAuthorFirstname() {
	return AuthorFirstname;
}

public void setAuthorFirstname(String authorFirstname) {
	AuthorFirstname = authorFirstname;
}

public int getEntryNumber(){
	return EntryNumber;
}

public void setSellerAccNum(int SellerAccNum){
	this.SellerAccNum=SellerAccNum;
}

public int getSellerAccNum(){
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

public void setISBN(String ISBN) {
	this.ISBN = ISBN;
}
public String getISBN() {
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

public String BooktoString(){
	String format="Entry Number: "+EntryNumber+" \nTitle:\t"+BookTitle+
			"\nAuthor:\t"+AuthorFirstname+" "+AuthorLastname+
			"\nCondition:\t"+Condition+
			"\nISBN:\t"+ISBN+
			"\nSeller:\t"+SellerName+
			"\nPrice: $"+Price+"\n";
	return format;
}






}
