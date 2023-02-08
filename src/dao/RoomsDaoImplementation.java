package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controllers.LoginController;
import Models.PartyRoom;
import application.DataBaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**

 * @author: HIZ
 * @class : this class is the implementation of RoomsDao interface it's used to describe all the methods that can be called while accessing the ManagePartyRoom interface
 */

public class RoomsDaoImplementation implements RoomsDao{
	
	/**
	 * The class have an attribute connection which is used to get the connection object generated while connecting the java project to mysql server
	 * It has also the Rooms observable list which have the ability to be binded with the tableview on the fxml file so that any change that occurs on this list will automatically appear on the table view 
	 */
	
	static Connection connection = DataBaseConn.getConnection();
	private PartyRoom rms;
    int idClient = LoginController.getUser1().getID();
	public PartyRoom getRms() {
		return rms;
	}

	public void setRms(PartyRoom rms) {
		this.rms = rms;
	}
	
	static ObservableList<PartyRoom> Rooms;
	public static ObservableList<PartyRoom> getRooms() {
		return Rooms;
	}
	/**
	 *
	 * @method: getAllRooms 
	 * @return: an observable list which is the rooms attribute 
	 * This method is used for initializing the tableView by getting all the registered rows from the database 
	 */

	@Override
	

	public  ObservableList<PartyRoom> getAllRooms() throws SQLException {
		 String query = "select * from partyroom where user_idUser= ?";
	        PreparedStatement ps
	            = connection.prepareStatement(query);
	        ps.setInt(1, idClient);
	        ResultSet rs = ps.executeQuery();
	        Rooms=FXCollections.observableArrayList();
	  
	        while (rs.next()) {
	        	PartyRoom rooms = new PartyRoom();
	        	
	        	rooms.setName(rs.getString("partyRoomName"));
	        	rooms.setAddress(rs.getString("partyRoomAddress"));
	        	
	        	rooms.setBudgetRoom(rs.getDouble("partyRoomBudget"));
	 		    rooms.setID(rs.getInt("idpartyRoom"));
	           System.out.println(rooms.getBudgetRoom());
	        	Rooms.add(rooms);
		
	}
	      
	
		return Rooms;
	}

	
	
	/**
	 * 
	 * addRooms method is used to get information from user input while trying to create a Room on the addRooms controller
	 * the method's aim is to get the object ,set it's attribute to the prepared statement object and execute the query to add the object
	 *  on the database we add also the parameter object to the table view so that changes are immediately shown on the table 
	 * @param: rms: it's the parameter taken from the controller part so that all the informations required on the database row are filled from user input
	 */
	
	
	
	@Override
	public void addRooms(PartyRoom rms) throws SQLException {
		String query
        = "insert into partyroom(partyRoomName, "
          + "partyRoomAddress ,partyRoomBudget ,user_idUser) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, rms.getName());
		ps.setString(2, rms.getAddress());
		ps.setDouble(3, rms.getBudgetRoom());
		ps.setInt(4, idClient);
		
		
		ps.executeUpdate();
		System.out.println("room added");
		Rooms.add(rms);	
	}
	
	/**
	 * 
	 * updateRooms method is used to get information of the selected item from database and modify them
	 * the method's aim is to get the object ,set it's attribute to the prepared statement object and execute the query to update the object
	 *  on the database we update also the parameter object to the table view so that changes are immediately shown on the table 
	 * @param: rms: it's the parameter taken from the controller part so that all the informations required on the database row are filled from user input
	 */
	@Override
	public void updateRooms(PartyRoom rms) throws SQLException {
		// TODO Auto-generated method stub
		String query = "update partyroom set partyRoomName=?, "
		          + " partyRoomAddress= ?, partyRoomBudget = ? where idpartyRoom = ?";
				PreparedStatement ps
					= connection.prepareStatement(query);
				System.out.println(rms.getName());
				ps.setString(1, rms.getName());
				ps.setString(2, rms.getAddress());
				ps.setDouble(3, rms.getBudgetRoom());
				ps.setInt(4, rms.getID());
				
				ps.executeUpdate();
				System.out.println("room is updated");
		
	}

	/**
	 * deleteRooms method is used to get information of the selected item from database and delete them
	 * the method's aim is to get the object , and execute the query to delete the object
	 *  on the database we delete also the parameter object to the table view so that changes are immediately shown on the table 
	 *  the item deleted is no more on the table  
	 * @param: rms: it's the parameter taken from the controller part so that all the informations required on the database row are filled from user input
	 */
	@Override
	public void deleteRooms(PartyRoom rms) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from partyroom where idpartyRoom =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, rms.getID());
		ps.executeUpdate();
		Rooms.remove(Rooms.indexOf(rms)) ;
		System.out.println("Delete rooms");
		
		
	}
	public static void delelteAllRooms(int idUser)throws SQLException{
		String query = "delete from partyroom where user_idUser =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1,idUser);
		ps.executeUpdate();
	}

}