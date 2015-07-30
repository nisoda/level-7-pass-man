import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class PassMan {
	static Connection connection = null;
	static int input = 0;
	static Long starttimeconnect;
	static Long endtimeconnect;
	static Long totaltimeconnect;
	static Long starttime;
	static Long endtime;
	static Long totaltime;
	private static String master_user;
	// We'll have to change this, I assume it's probably the MASTER_USER
	// and MASTER_PASS verification
	//static String userInput; //Studio, Title, Type
	//static int userNumber; //Year, Number of Episodes

	public PassMan () {
		try {
			starttimeconnect = System.currentTimeMillis();
			// Don't forget to change the password, it's at the end of the following line
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASSMAN", "root", "password");

			//end timer
			endtimeconnect = System.currentTimeMillis();
			totaltimeconnect = endtimeconnect - starttimeconnect;
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}

	/**
	 * Display the entire list
	 */
	public static void runSql(){
		if(connection != null) {
			try {
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
			}
		} else {
			System.out.println("You are not connected.");
		}
	}
	
	public static boolean authenticateLogin(String username, String password) {
		boolean authenticated = false;
		if(connection != null) {
			try {
				Statement stmt = null;

				//start the timer
				starttime = System.currentTimeMillis();

				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER_LOGINS U WHERE U.USERNAME=\"%s\" AND U.PASSWORD=\"%s\"", username, password));
				//end the timer
				endtime = System.currentTimeMillis();
				totaltime = endtime - starttime;

				if(rs.first()) {
					master_user = username;
					authenticated = true;
				}
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
			}
			
			return authenticated;
			
		} else {
			System.out.println("You are not connected.");
			return false;
		}
	}
	
	public static ResultSet viewAllStored(String username) {
		ResultSet rs = null;
		if(connection != null) {
			try {
				Statement stmt = null;

				//start the timer
				starttime = System.currentTimeMillis();

				stmt = connection.createStatement();
				rs = stmt.executeQuery(String.format("SELECT S.SITE, S.USERNAME, S.PASSWORD FROM STORED_ACCOUNTS S WHERE S.MASTER_USER=\"%s\"", username));
				//end the timer
				endtime = System.currentTimeMillis();
				totaltime = endtime - starttime;
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
			}
			
			return rs;
			
		} else {
			System.out.println("You are not connected.");
			return null;
		}
	}
	
	public boolean addEntry(String user, String pw, String url) throws SQLException{
		String insertString = "INSERT INTO stored_accounts " +
				"(MASTER_USER,SITE,USERNAME,PASSWORD)" +
				"values(?,?,?,?)";
		PreparedStatement insertquery = connection.prepareStatement(insertString);
		insertquery.setString(1, master_user);
		insertquery.setString(2, url);
		insertquery.setString(3, user);
		insertquery.setString(4,pw);
		insertquery.executeUpdate();
		
		return true;
	}
	
	public boolean delEntry(String user, String pw, String url) throws SQLException{
		String insertString = "DELETE FROM stored_accounts " +
				" where MASTER_USER = ? and SITE = ? and USERNAME = ? and PASSWORD = ?";
		PreparedStatement insertquery = connection.prepareStatement(insertString);
		insertquery.setString(1, master_user);
		insertquery.setString(2, url);
		insertquery.setString(3, user);
		insertquery.setString(4,pw);
		insertquery.executeUpdate();
		
		return true;
	}

}