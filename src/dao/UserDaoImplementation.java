package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.User;
import application.DataBaseConn;
public class UserDaoImplementation implements  UserDao{
	static Connection connection = DataBaseConn.getConnection();

	@Override
	public void addUser(User user) throws SQLException {
		
		String query
        = "insert into user(Email, "
          + "Name,PhoneNum,HintPassword,Password,Username) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, user.getEmail());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPhoneNum());
		ps.setString(4, user.getHintPassword());
		ps.setString(5, user.getPassword());
		ps.setString(6,user.getUsername());
		ps.executeUpdate();
		System.out.println("User added");
		
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		 String query = "select * from user";
	        PreparedStatement ps
	            = connection.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();
	        List<User> users = new ArrayList<>();
	  
	        while (rs.next()) {
           	User user = new User();
           	user.setEmail(rs.getString("Email"));
    		user.setName(rs.getString("Name"));
    		user.setPhoneNum(rs.getString("PhoneNum"));
    		user.setHintPassword(rs.getString("HintPassword"));
    		user.setPassword(rs.getString("Password"));
    		user.setUsername(rs.getString("Username"));
	        user.setID(rs.getInt("idUser"));  
	            users.add(user);
		
	}
	        return users;

	}

	@Override
	public User getUser(String username) throws SQLException {
	   System.out.println(username);
		String query
        = "select * from user where Username = ?";
		PreparedStatement ps
			= connection.prepareStatement(query);

		ps.setString(1,username);
		User user = new User();
		
		ResultSet rs = ps.executeQuery();
		
		boolean check = false;
      
		while (rs.next()) {
        check = true;
    	user.setEmail(rs.getString("Email"));
		user.setName(rs.getString("Name"));
		user.setPhoneNum(rs.getString("PhoneNum"));
		user.setHintPassword(rs.getString("HintPassword"));
		user.setPassword(rs.getString("Password"));
		user.setUsername(rs.getString("Username"));
		user.setID(rs.getInt("idUser"));
		System.out.println(rs.getInt("idUser"));
		}
		if (check) {
			return user;
		}
		else	return null;
	}

	@Override
	public void isOnline(User user) throws SQLException {
		String query = "update user set isOnline=? "
		          + " where Username = ?";
				PreparedStatement ps
					= connection.prepareStatement(query);
				
				ps.setBoolean(1, user.getIsOnline());
				ps.setString(2, user.getUsername());
				ps.executeUpdate();
	}

	@Override
	public User getOnlineUser() throws SQLException {
		String query = "select * from user where isOnline=?" ;
        PreparedStatement ps
            = connection.prepareStatement(query);
        ps.setBoolean(1,true);
		User user = new User();
		ResultSet rs = ps.executeQuery();
		boolean check = false;

		while (rs.next()) {
        check = true;
    	user.setEmail(rs.getString("Email"));
		user.setName(rs.getString("Name"));
		user.setPhoneNum(rs.getString("PhoneNum"));
		user.setHintPassword(rs.getString("HintPassword"));
		user.setPassword(rs.getString("Password"));
		user.setUsername(rs.getString("Username"));
		}
		if (check) {
			return user;
		}
		else	return null;
	}

	@Override
	public void UpdateUser(User user) throws SQLException {
		String query = "update user set Email=?, "
		          + " Name= ?, PhoneNum = ? ,HintPassword = ?,Password = ?,Username = ? where idUser = ?";
				PreparedStatement ps
					= connection.prepareStatement(query);
				
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPhoneNum());
				ps.setString(4, user.getHintPassword());
				ps.setString(5, user.getPassword());
				ps.setString(6, user.getUsername());
				ps.setInt(7, user.getID());
				ps.executeUpdate();
				System.out.println("User is updated**");
		
	}

	@Override
	public void DelelteUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		//Delete clients of user with its events 
		ClientDaoImplementation client = new ClientDaoImplementation();
		client.deleteAllClientOfUser();
		//delete Rooms
		RoomsDaoImplementation.delelteAllRooms(user.getID());
		//Delete providers 
		ProviderDaoImplementation.delelteAllProviders(user.getID());
		String query = "delete from user where idUser =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1,user.getID());
		ps.executeUpdate();
	}

}
