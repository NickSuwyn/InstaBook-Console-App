package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FriendDao {
	
	private final Connection connection = ConnectionHandler.getConnection();
	private final String CREATE_FRIENDSHIP = "INSERT INTO friends(userId1, userId2) VALUES (?,?)";
	private final String UNFRIEND = "DELETE FROM friends WHERE friendId = ?";
	private final String GET_FRIENDS = "SELECT * FROM friends WHERE userId1 = ? OR userId2 = ?";
	
	public int createFriendship(int userId1, int userId2) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_FRIENDSHIP, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, userId1);
		ps.setInt(2,  userId2);
		ps.executeUpdate();
		
		ResultSet resultSet = ps.getGeneratedKeys();
		int id = 0;
		if (resultSet.next()) {
			id = resultSet.getInt(1);
		}
		
		return id;
	}
	
	public void unfriend(int friendId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UNFRIEND);
		ps.setInt(1, friendId);
		ps.executeUpdate();
	}
	
	public List<Integer> getFriends(int userId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_FRIENDS);
		ps.setInt(1, userId);
		ps.setInt(2, userId);
		
		List<Integer> friendIds = new ArrayList<Integer>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt(2) != userId) {
				friendIds.add(rs.getInt(2));
			} else {
				friendIds.add(rs.getInt(3));
			}
		}
		
		return friendIds;
	}

}
