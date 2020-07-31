package ntut.csie.accountManagementService.adapter.gateways.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlDatabaseHelper {
	private static SqlDatabaseHelper instance = null;
	private String serverUrl;
	private String databaseName;
	private String account;
	private String password;
	private Connection connection;
	private boolean transacting;


	public SqlDatabaseHelper() {
		serverUrl = "127.0.0.1";
		databaseName = "user";
		account = "root";
		password = "root";
		transacting = false;
		createUserDatabase();
		createUserTable();
		createEventTable();
	}

	public void connectToDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+serverUrl+":3306/" + databaseName + "?useSSL=false", account, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createUserDatabase() {
		Statement statement = null;
		String sql = "Create Database If Not Exists " + databaseName;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+serverUrl+":3306?useSSL=false&autoReconnect=true", account, password);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
			closeConnection();
		}
	}
	
	private void createUserTable() {
		connectToDatabase();
		Statement statement = null;
		String sql = "Create Table If Not Exists " + UserTable.tableName + " ("
				+ UserTable.id + " Varchar(50) Not Null, "
				+ UserTable.username + " Varchar(50) Not Null, " 
				+ UserTable.email + " Varchar(50) Not Null, "
				+ UserTable.password + " Varchar(72) Not Null, "
				+ UserTable.nickname + " Varchar(50) Not Null, "
				+ UserTable.systemRole + " Varchar(50) Not Null, "
				+ "Primary Key (" + UserTable.id + ")"
				+ ")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
			closeConnection();
		}
	}

	private void createEventTable() {
		connectToDatabase();
		Statement statement = null;
		String sql = "Create Table If Not Exists " + EventTable.tableName + " ("
				+ EventTable.eventId + " Int(11) Not Null Auto_Increment, "
				+ EventTable.eventBody + " Varchar(20000) Not Null, "
				+ EventTable.eventType + " Varchar(250) Not Null, "
				+ "Primary Key (" + EventTable.eventId + ")"
				+ ")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
			closeConnection();
		}
	}

	public boolean isTransacting() {
		return transacting;
	}

	public void transactionStart() throws SQLException {
		connection.setAutoCommit(false);
		transacting = true;
	}

	public void transactionError(){
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void transactionEnd() throws SQLException {
		connection.commit();
		transacting = false;
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement;
	}

	public ResultSet getResultSet(String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		return resultSet;
	}

	public void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePreparedStatement(PreparedStatement preparedStatement) {
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
