import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PassMan {
	static int input = 0;
	static Long starttimeconnect;
	static Long endtimeconnect;
	static Long totaltimeconnect;
	static Long starttime;
	static Long endtime;
	static Long totaltime;
	// We'll have to change this, I assume it's probably the MASTER_USER
	// and MASTER_PASS verification
	//static String userInput; //Studio, Title, Type
	//static int userNumber; //Year, Number of Episodes
	
	/**
	 * Display the entire list
	 */
	public static void runSql(){
		Connection connection = null;

		try {
			starttimeconnect = System.currentTimeMillis();
			// Don't forget to change the password, it's at the end of the following line
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASSMAN", "root", "password");
			
			//end timer
			endtimeconnect = System.currentTimeMillis();
			totaltimeconnect = endtimeconnect - starttimeconnect;
			
			Statement stmt = null;
			
			//start the timer
			starttime = System.currentTimeMillis();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select * From stored_accounts");
			ResultSetMetaData rsmd = rs.getMetaData();
			//end the timer
			endtime = System.currentTimeMillis();
			totaltime = endtime - starttime;
			
			stmt = connection.createStatement();

			while (rs.next()) {
			// ID, and MASTER_USER goes here maybe?
		        String website = rs.getString("Site");
		        String username = rs.getString("Username");
		        String password = rs.getString("Password");
		         
		        // print the results
		        System.out.format("%s\n%s\n%s\n%s\n%s\n", "Website: " + website, "Username: " + username, "Password: " + password);
				System.out.println();
			}
			
			System.out.println("Result to connect: " + totaltimeconnect + " MS");
			System.out.println("Result: " + totaltime + " MS");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	}
	
	public static void main(String[] args) {
		runSql();
	}

}
