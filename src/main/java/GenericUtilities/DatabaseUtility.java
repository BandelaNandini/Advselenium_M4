package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	public Connection con;

	public Connection getDatabaseConnection(String url, String username, String password) throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

	public Connection getDatabaseConnection() throws SQLException {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qsp", "root", "root");
		return con;
	}

	public ResultSet fetchDataFromDatabase(String query) throws SQLException {
		Statement stat = con.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result;
	}

	public int updateDataToDatabase(String query) throws SQLException {
		Statement stat = con.createStatement();
		int result = stat.executeUpdate(query);
		return result;
	}

	public void closeDatabaseConnection() throws SQLException {
		con.close();
	}
}
