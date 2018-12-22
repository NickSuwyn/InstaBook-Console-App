package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		while (true) {
			input = scanner.nextLine();
			String[] options = input.split(" ", 3);
			
			try {
				if (options[0].equals("user")) {
					handleUserRequests(options);
				} else if (options[0].equals("friend")) {
					handleFriendRequests(options);
				}
			} catch (SQLException exception) {
				System.out.println(exception.getMessage());
			}
			
		}
	}
	
	public static void handleUserRequests(String[] options) throws SQLException {
		UserDao dao = new UserDao();
		
		if (options[1].equals("create")) {
			int id = dao.createUser(options[2]);
			System.out.println("User created with id: " + id);
		} else {
			System.out.println("Invalid Request.");
		}
	}
	
	public static void handlePostRequests(String[] options) {
		
	}
	
	public static void handleCommentRequests(String[] options) {
		
	}
	
	public static void handleFriendRequests(String[] options) throws NumberFormatException, SQLException {
		FriendDao dao = new FriendDao();
		
		String[] suboptions = options[2].split(" ");
		
		if (options[1].equals("create")) {
			int id = dao.createFriendship(Integer.parseInt(suboptions[0]), Integer.parseInt(suboptions[1]));
			System.out.println("Friendship created with id: " + id);
		} else if (options[1].equals("delete")) {
			dao.unfriend(Integer.parseInt(suboptions[0]));
		} else if (options[1].equals("read")) {
			List<Integer> friendIds = dao.getFriends(Integer.parseInt(suboptions[0]));
			for (Integer id : friendIds) {
				System.out.println(id);
			}
		}
	}

}
