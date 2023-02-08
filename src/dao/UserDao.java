package dao;

import java.sql.SQLException;
import java.util.List;

import Models.User;





public interface UserDao {
	public List<User> getAllUsers()throws SQLException ;
	public void addUser(User user)throws SQLException;
	public User getUser(String username)throws SQLException;
	/**
	 * this method is used to set the online property of the user to true  so that if he is online we could get its information  since one user is connected at a time
	 * @param user
	 * @throws SQLException
	 */
	public void isOnline(User user)throws SQLException;
	public User getOnlineUser ()throws SQLException;
	public void UpdateUser(User user)throws SQLException;
	public void DelelteUser(User user)throws SQLException;
}
