package dao;
import java.sql.SQLException;

import Models.Client;
import javafx.collections.ObservableList;


public interface ClientDao {
	/**
	 *
	 * @method:getAllClients 
	 * @return: an observable list which is the clients attribute 
	 * This method is used for initializing the tableview by getting all the registered rows from the database 
	 */
	public ObservableList<Client> getAllClients()throws SQLException;
	/**
	 * 
	 * AddClient method is used to get information from user input while trying to create a Client on the AddClient controller
	 * the method's aim is to get the object ,set it's attribute to the prepared statement object and execute the query to add the object
	 *  on the database we add also the parameter object to the table view so that changes are immediately shown on the table 
	 * @param: client: it's the parameter taken from the controller part so that all the informations required on the database row are filled from user input
	 * @throws SQLException
	 */
	public void addClient(Client clt)throws SQLException;
	/**
	 * The method updateClient is used to modify information of a certain client on the table view as well as make necessary updates on the database 
	 * @param clt this parameter here is where all the modified object is stored the id doesn't change so that we refer to it to make changes on the database
	 * @throws SQLException
	 */
	public void updateClient(Client clt)throws SQLException;
	/**
	 * This method is used to delete one selected client at time from the table of clients 
	 * @param clt here we get the client object selected to be deleted
	 * @throws SQLException 
	 */
	public void deleteClient(Client clt)throws SQLException;
	public void deleteAllClientOfUser()throws SQLException;
}
