package ntut.csie.accountManagementService.adapter.gateways.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ntut.csie.accountManagementService.adapter.gateways.database.SqlDatabaseHelper;
import ntut.csie.accountManagementService.adapter.gateways.database.UserTable;

import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.UserRepository;

public class MySqlUserRepositoryImpl implements UserRepository{
	private SqlDatabaseHelper sqlDatabaseHelper = new SqlDatabaseHelper();
	private UserMapper userMapper;
	
	public MySqlUserRepositoryImpl() {
		userMapper = new UserMapper();
	}

	@Override
	public void save(User user) {
		sqlDatabaseHelper.connectToDatabase();
		PreparedStatement preparedStatement = null;

		try {
			sqlDatabaseHelper.transactionStart();

			UserData data = userMapper.transformToUserData(user);

			String sql = String.format("Insert Into %s Values (?, ?, ?, ?, ?, ?) On Duplicate Key Update %s=?, %s=?, %s=?, %s=?",
					UserTable.tableName, UserTable.email, UserTable.password, UserTable.nickname, UserTable.systemRole);
			preparedStatement = sqlDatabaseHelper.getPreparedStatement(sql);
			preparedStatement.setString(1, data.getId());
			preparedStatement.setString(2, data.getUsername());
			preparedStatement.setString(3, data.getEmail());
			preparedStatement.setString(4, data.getPassword());
			preparedStatement.setString(5, data.getNickname());
			preparedStatement.setString(6, data.getSystemRole());
			preparedStatement.setString(7, data.getEmail());
			preparedStatement.setString(8, data.getPassword());
			preparedStatement.setString(9, data.getNickname());
			preparedStatement.setString(10, data.getSystemRole());
			preparedStatement.executeUpdate();
			sqlDatabaseHelper.transactionEnd();
		} catch (SQLException e) {
			sqlDatabaseHelper.transactionError();
			e.printStackTrace();
		} finally {
			sqlDatabaseHelper.closePreparedStatement(preparedStatement);
			sqlDatabaseHelper.closeConnection();
		}
		
	}

	@Override
	public User getUserById(String id) {
		if(!sqlDatabaseHelper.isTransacting()) {
			sqlDatabaseHelper.connectToDatabase();
		}
		ResultSet resultSet = null;
		User user = null;

		try {

			String query = String.format("Select * From %s Where %s = '%s'",
					UserTable.tableName,
					UserTable.id,
					id);

			resultSet = sqlDatabaseHelper.getResultSet(query);

			if(resultSet.first()) {
				String userId = resultSet.getString(UserTable.id);
				String username = resultSet.getString(UserTable.username);
				String email = resultSet.getString(UserTable.email);
				String password = resultSet.getString(UserTable.password);
				String systemRole = resultSet.getString(UserTable.systemRole);
				String nickname = resultSet.getString(UserTable.nickname);
				
				UserData data = new UserData();
				data.setId(userId);
				data.setUsername(username);
				data.setEmail(email);
				data.setPassword(password);
				data.setSystemRole(systemRole);
				data.setNickname(nickname);

				user = userMapper.transformToUser(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlDatabaseHelper.closeResultSet(resultSet);
			if(!sqlDatabaseHelper.isTransacting()) {
				sqlDatabaseHelper.closeConnection();
			}
		}
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		if(!sqlDatabaseHelper.isTransacting()) {
			sqlDatabaseHelper.connectToDatabase();
		}
		User user = null;
		ResultSet resultSet = null;
		try {
			String query = String.format("Select * From %s Where %s = '%s'",
					UserTable.tableName,
					UserTable.username,
					username);
			resultSet = sqlDatabaseHelper.getResultSet(query);

			if(resultSet.first()) {
				String userId = resultSet.getString(UserTable.id);
				String email = resultSet.getString(UserTable.email);
				String password = resultSet.getString(UserTable.password);
				String systemRole = resultSet.getString(UserTable.systemRole);
				String nickname = resultSet.getString(UserTable.nickname);
				
				UserData data = new UserData();
				data.setId(userId);
				data.setUsername(username);
				data.setEmail(email);
				data.setPassword(password);
				data.setSystemRole(systemRole);
				data.setNickname(nickname);

				user = userMapper.transformToUser(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlDatabaseHelper.closeResultSet(resultSet);
			if(!sqlDatabaseHelper.isTransacting()) {
				sqlDatabaseHelper.closeConnection();
			}
		}
		return user;
	}
}
