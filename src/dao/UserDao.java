package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	private final Connection connection = ConnectionHandler.getConnection();
	private final String CREATE_QUERY = "INSERT INTO users(userName) VALUES(?)";
	
	public int createUser(String userName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, userName);
		ps.executeUpdate();
		
		ResultSet resultSet = ps.getGeneratedKeys();
		int id = 0;
		if (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		
		return id;
	}
}
