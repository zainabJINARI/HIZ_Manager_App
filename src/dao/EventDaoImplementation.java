package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Client;
import Models.Event;
import application.DataBaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @since: 11/2023
 * @author: HIZ
 * @class : this class is the implementation of ClienDao interface it's used to describe all the methods that can be called while accessing the Manage Client interface
 *
 */

public class EventDaoImplementation implements EventDao{
	/**
	 * The class have an attribute connection which is used to get the connection object generated while connecting the java project to mysql server
	 * It has also the clients observable list which have the ability to be binded with the tableview on the fxml file so that any change that occurs on this list will automatically appear on the table view 
	 */
	
	static Connection connection = DataBaseConn.getConnection();
	
	/**
	 * evt is where we put the event selected to be modified or deleted
	 */
	private  static Event evt;
	int id;
	/**
	 * ctl is where we store the client selected to show its event area 
	 */
	private static Client ctl;
	public static Client getCtl() {
		return ctl;
	}
	public static void setCtl(Client ctl) {
		EventDaoImplementation.ctl = ctl;
	}
	public static Event getEvt() {
		return evt;
	}
	public static void setEvt(Event evt) {
		EventDaoImplementation.evt = evt;
	}
	ObservableList<Event> Events;
	/**
	 *
	 * @method:getAllClients 
	 * @return: an observable list which is the clients attribute 
	 * This method is used for initializing the tableview by getting all the registered rows from the database 
	 */


	
	public ObservableList<Event> getEvents() {
		return Events;
	}
	/**
	 * 
	 * AddClient method is used to get information from user input while trying to create a Client on the AddClient controller
	 * the method's aim is to get the object ,set it's attribute to the prepared statement object and execute the query to add the object
	 *  on the database we add also the parameter object to the table view so that changes are immediately shown on the table 
	 * @param: client: it's the parameter taken from the controller part so that all the informations required on the database row are filled from user input
	 */



//
//
//	@Override
//	public void deleteEvent(Event evt) throws SQLException {
//		// TODO Auto-generated method stub
//		String query = "delete from client where idEvent =?";
//		PreparedStatement ps = connection.prepareStatement(query);
//		ps.setInt(1, evt.getID());
//		ps.executeUpdate();
//		Events.remove(Events.indexOf(evt)) ;
//		System.out.println("Delelte client");
//		
//	}


	@Override
	public ObservableList<Event> getAllEvents() throws SQLException {
		      id =ctl.getID();
		      System.out.println();
		// TODO Auto-generated method stub
			 String query = "select * from event where Client_idClient = ?";
		        PreparedStatement ps
		            = connection.prepareStatement(query);
		        ps.setInt(1, id);
		        ResultSet rs = ps.executeQuery();
		        Events=FXCollections.observableArrayList();
		  
		        while (rs.next()) {
	        	Event event = new Event();
	        	event.setID(rs.getInt("idEvent"));
	        	event.setNbrGuets(rs.getInt("NumGuests"));
	        	event.setIdClient(id);
	        	event.setMoreDetails(rs.getString("MoreDetails"));
	        	event.setTypeOfEvent(rs.getString("EventType"));
	        	event.setStartDate(rs.getDate("startDate"));
	        	event.setEndDate(rs.getDate("endDate"));
	            event.setIdBudget(rs.getInt("Budget_idBudget"));
	            event.setIdPartyR(rs.getInt("partyRoom_idpartyRoom"));
	            event.setIdProvider(rs.getInt("Provider_idProvider"));
		           
		            Events.add(event);
			
		}
		      
		
			return Events;
		}
	@Override
	public void AddEvent(Event ev) throws SQLException {
		String query
      = "insert into event(EventType, "
        + "NumGuests,startDate,endDate,MoreDetails,Client_idClient,Provider_idProvider,partyRoom_idpartyRoom,Budget_idBudget) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, ev.getTypeOfEvent());
		ps.setInt(2, ev.getNbrGuets());
		ps.setDate(3, (Date) ev.getStartDate());
		ps.setDate(4, (Date) ev.getEndDate());
		ps.setString(5, ev.getMoreDetails());
		ps.setInt(6, id);
		ps.setInt(7, ev.getIdProvider());
		ps.setInt(8, ev.getIdPartyR());
		System.out.println(ev.getIdBudget());
		ps.setInt(9, ev.getIdBudget());


		ps.executeUpdate();
		System.out.println("event added");
		Events.add(ev);
		
	}
	@Override
	public void UpdateEvent(Event ev) throws SQLException {
		System.out.println(ev.getIdClient());
		// TODO Auto-generated method stub
		String query = "update event set EventType=?, "
	          + " NumGuests= ?, startDate = ? ,endDate = ? ,MoreDetails = ? ,Client_idClient = ? ,Provider_idProvider = ? , partyRoom_idpartyRoom = ?, Budget_idBudget = ? where idEvent = ?";
			PreparedStatement ps
				= connection.prepareStatement(query);
			
			ps.setString(1, ev.getTypeOfEvent());
			ps.setInt(2, ev.getNbrGuets());
			ps.setDate(3, (Date) ev.getStartDate());
			ps.setDate(4, (Date) ev.getEndDate());
			ps.setString(5, ev.getMoreDetails());
			ps.setInt(6, ev.getIdClient());
			ps.setInt(7, ev.getIdProvider());
			ps.setInt(8, ev.getIdPartyR());
			ps.setInt(9, ev.getIdBudget());
			ps.setInt(10, ev.getID());
			ps.executeUpdate();
			System.out.println("Event is updated**"+ev.getID());
		
	}
	@Override
	public void DeleteEvent(int idEv,int idbudget) throws SQLException {
		

			// TODO Auto-generated method stub
			String query = "delete from event where idEvent =?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, idEv);
			ps.executeUpdate();
			BudgetDaoImplementation bud = new BudgetDaoImplementation();
			bud.deleteBudget(idbudget);
			System.out.println("Delelte event "+idEv);

			for(int i = 0;i<Events.size(); i++) {
				if(Events.get(i).getID()==idEv) {
					Events.remove(i);
				}
			}
			System.out.println("Delelte event "+idEv);
	}
	public static void DeleteAllEvent(int idClient)  throws SQLException{
		String sql = "DELETE FROM * event where Client_idClient = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, idClient);
		ps.executeUpdate();
	}
	@Override
	public void DelelteAllEventOfClient() throws SQLException {
		ObservableList<Event> events =getAllEvents();
		for(int i=0;i<events.size();i++) {
			DeleteEvent(events.get(i).getID(),events.get(i).getIdBudget());
		}
		
		
	}

	
	
	

}