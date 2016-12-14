package ObjectClasses;
public class Post {

	String Seller;
	String Condition;
	String Book;
	String Author;
	String ISBN;
	double Price;
	int Account;
	
	public Post (String Seller,	String Condition, String Book, String Author, String ISBN, double Price, int Account)
	{
		this.Seller=		Seller;
		this.Condition= 	Condition;
		this.Book=  		Book;
		this.Author=  		Author;
		this.ISBN=  		ISBN;
		this.Price= 		Price;
		this.Account= 		Account;	
	}

	public String getSeller() 
	{
		return Seller;
	}

	public String getCondition() 
	{
		return Condition;
	}

	public String getBook() 
	{
		return Book;
	}

	public String getAuthor() 
	{
		return Author;
	}

	public String getISBN() 
	{
		return ISBN;
	}

	public double getPrice() 
	{
		return Price;
	}

	public int getAccount() 
	{
		return Account;
	}

	public void setSeller(String seller) 
	{
		Seller = seller;
	}

	public void setCondition(String condition) 
	{
		Condition = condition;
	}

	public void setBook(String book) 
	{
		Book = book;
	}

	public void setAuthor(String author) 
	{
		Author = author;
	}

	public void setISBN(String iSBN) 
	{
		ISBN = iSBN;
	}

	public void setPrice(double price) 
	{
		Price = price;
	}

	public void setAccount(int account) 
	{
		Account = account;
	}

	
}
