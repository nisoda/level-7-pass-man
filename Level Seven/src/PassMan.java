import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	final private static int MAX_USERNAME = 40;
	final private static int MAX_PASSWORD = 40;
	final private static int MAX_URL = 240;
	final private static int KEY_EXIST = 3;
	final private static int EMPTY_STRING = 2;
	final private static int OVERFLOW_ERROR = 1;

	private static String master_user;

	public PassMan() {
		try {
			starttimeconnect = System.currentTimeMillis();

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PASSMAN", "root", "password");

			// end timer
			endtimeconnect = System.currentTimeMillis();
			totaltimeconnect = endtimeconnect - starttimeconnect;
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}

	/**
	 * Hashes the password provided from myFrame
	 * 
	 * @param password the password of the login
	 * @return the generated password
	 */
	// Hash the password
	private static String getPass(String passToHash, String salt) {
		String genPass = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passToHash.getBytes());

			StringBuilder strBuild = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				strBuild.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			genPass = strBuild.toString();
		} catch (NoSuchAlgorithmException ne) {
			ne.printStackTrace();
		}
		return genPass;
	}

	/**
	 * Salts the password provided from myFrame after it has been hashed
	 * 
	 * @param password the password of the login
	 * @return salted password as a string
	 */
	private static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
		SecureRandom secRand = SecureRandom.getInstance("SHA1PRNG", "SUN");
		byte[] salt = new byte[16];
		secRand.nextBytes(salt);
		return salt.toString();
	}

	/**
	 * Authenticates the login provided from myFrame
	 * Checks the string character length to prevent overflow 
	 * Checks with database to verify login is correct
	 * @param username		the username of the login
	 * @param password		the password of the login
	 * @return				0 for success, 1 for overflow_error
	 */
	@SuppressWarnings("unused")
	public static int authenticateLogin(String username, String password) {
		int pass = verifyInput(username,password);
		int authenticate = -1;

		String passToHash = password;
		if(pass == 0){
			if (connection != null) {
				// This prevents SQL injections as it uses correctly
				// parameterized queries
				PreparedStatement stmt = null;

				// Start the timer
				starttime = System.currentTimeMillis();

				try {

					String salt = getSalt();

					// By using bind variables (question marks) and setString method
					// SQL injection can be prevented
					stmt = connection.prepareStatement("SELECT * FROM USER_LOGINS U WHERE U.USERNAME=? AND U.PASSWORD=?");
					stmt.setString(1, username);
					stmt.setString(2, password);
					ResultSet rs = stmt.executeQuery();

					if (rs.first()) {
						password = rs.getString(3);
						boolean passCheck = false;

						String securePass = getPass(passToHash, salt);
						String verifyPass = getPass(password, salt);

						if (securePass.equals(verifyPass)) {
							passCheck = true;
							
							master_user = username;
							authenticate = 0;
							
						} else {
							passCheck = false;
							System.out.println("Error: Not the same password.");
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// End the timer
				endtime = System.currentTimeMillis();
				totaltime = endtime - starttime;
			}
		}
		else if(pass == 1){
			authenticate = 1;
		}
		return authenticate;
	}

	/**
	 * Displays all stored accounts under the given username
	 * @param username		the username of the master login
	 * @return				ResultSet of all associated accounts
	 */
	public static ResultSet viewAllStored(String username) {
		ResultSet rs = null;
		if (connection != null) {
			try {
				Statement stmt = null;

				// start the timer
				starttime = System.currentTimeMillis();

				stmt = connection.createStatement();
				rs = stmt.executeQuery(String.format(
						"SELECT S.SITE, S.USERNAME, S.PASSWORD FROM STORED_ACCOUNTS S WHERE S.MASTER_USER=\"%s\"",
						username));
				// end the timer
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

	/**
	 * Verifies the length of the string to be below assigned limit
	 * @param username		the username to be checked
	 * @param password		the password to be checked
	 * @return				0 for success, 1 for overflow_error
	 */
	private static int verifyInput(String username, String password){
		int verify = 1;
		if(username.length() > MAX_USERNAME || password.length() > MAX_PASSWORD){
			verify = OVERFLOW_ERROR;
		}
		else if(username.length() == 0 || password.length() == 0){
			verify = EMPTY_STRING;
		}
		else{
			verify = 0;
		}
		return verify;

	}
	/**
	 * Verifies the length of the string to be below assigned limit
	 * @param url			the url to be checked
	 * @param username		the username to be checked
	 * @param password		the password to be checked
	 * @return				0 for success, 1 for overflow_error
	 */
	private int verifyInput(String url, String username, String password){
		int verify = 1;
		if(username.length() > MAX_USERNAME || password.length() > MAX_PASSWORD
				|| url.length() > MAX_URL){
			verify = OVERFLOW_ERROR;
		}
		else if(username.length() == 0 || password.length() == 0
				|| url.length() == 0){
			verify = EMPTY_STRING;
		}
		else{
			verify = 0;
		}
		return verify;
	}
	/**
	 * Verifies that username,password and url are in character limit
	 * Adds it to the database if all was good
	 * @param url		the url to be added
	 * @param user		the user name to be added
	 * @param pw		the password to be added
	 * @return			0 for success, 1 for overflow_error
	 * @throws SQLException		if something was wrong with the sql server
	 */
	public int addEntry(String url, String user, String pw) throws SQLException {
		int pass = verifyInput(url, user, pw);
		if(pass == 0){
			String insertString = "INSERT INTO stored_accounts (MASTER_USER,SITE,USERNAME,PASSWORD)"
					+ "values(?,?,?,?)";
			PreparedStatement insertquery = connection.prepareStatement(insertString);
			insertquery.setString(1, master_user);
			insertquery.setString(2, url);
			insertquery.setString(3, user);
			insertquery.setString(4, pw);
			insertquery.executeUpdate();
		}
		return pass;
	}

	/**
	 * Deletes the entry selected from passManagerWindow
	 * @param url		the url given
	 * @param user		the username given
	 * @return			if operation was successful
	 * @throws SQLException		if something was wrong with the sql server
	 */
	public boolean delEntry(String url, String user) throws SQLException {
		String insertString = "DELETE FROM stored_accounts "
				+ " where MASTER_USER = ? and SITE = ? and USERNAME = ?";
		PreparedStatement insertquery = connection.prepareStatement(insertString);
		insertquery.setString(1, master_user);
		insertquery.setString(2, url);
		insertquery.setString(3, user);
		insertquery.executeUpdate();

		return true;
	}

	/**
	 * Obtains password for selected row. Copies to clipboard.
	 * @param url			the URL of the selected account
	 * @param username		the username of the selected account
	 * @return				0 for success, -1 for error
	 */
	public static int obtainPass(String url, String username) {
		int sucess = -1;
		String password = "";
		if (connection != null) {
			// This prevents SQL injections as it uses correctly
			// parameterized queries
			PreparedStatement stmt = null;

			// Start the timer
			starttime = System.currentTimeMillis();

			try {
				// By using bind variables (question marks) and setString method
				// SQL injection can be prevented
				stmt = connection.prepareStatement("SELECT S.PASSWORD FROM STORED_ACCOUNTS S WHERE S.SITE=? AND S.USERNAME=?");
				stmt.setString(1, url);
				stmt.setString(2, username);
				ResultSet rs = stmt.executeQuery();

				if (rs.first()) {
					password = rs.getString(1);

					StringSelection stringSelection = new StringSelection(password);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents (stringSelection, null);

					sucess = 0;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// End the timer
			endtime = System.currentTimeMillis();
			totaltime = endtime - starttime;
		}
		return sucess;
	}	

	/**
	 * Verifies the input is in character limit
	 * Checks against database that username key does not exist
	 * Adds to the database
	 * @param user				the username to add
	 * @param password			the password to add
	 * @return					0 for success, 1 for overflow_error, 2 for empty_strings
	 * 							3 for key_exist
	 * @throws SQLException
	 */
	public int addUser(String user, String password) throws SQLException{ 	
		int pass = verifyInput(user,password);
		int rowCount = -1;
		if(pass == 0){
			String exist = "SELECT COUNT(*) FROM USER_LOGINS WHERE USERNAME = ?";
			PreparedStatement checkName = connection.prepareStatement(exist);
			checkName.setString(1, user);
			ResultSet rs = checkName.executeQuery();
			rs.next();
			rowCount = rs.getInt(1);
			if(rowCount == 0){
				String insertString = "INSERT INTO USER_LOGINS (USERNAME, PASSWORD)" +
						"VALUES (?,?)";
				PreparedStatement insertQuery =  connection.prepareStatement(insertString);
				insertQuery.setString(1, user);
				insertQuery.setString(2, password);
				insertQuery.executeUpdate();
			}
			else{
				pass = KEY_EXIST;
			}
		}
		return pass;
	}
}