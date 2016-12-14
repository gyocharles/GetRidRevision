package MainClasses;

import java.sql.SQLException;
import javax.swing.JFrame;
import DBAccessClasses.DBConnection;
import userInt.WelcomeFrame;


/**
 * This class starts the entire program
 *
 */

public class MainClass 
{		
	public static void main(String[]args) throws SQLException
	{
		JFrame frame = new WelcomeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GetRid");
		frame.setVisible(true);
		DBConnection.getConnection();	 
	}
}
