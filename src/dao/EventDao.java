package dao;

import java.sql.SQLException;

import Models.Event;
import javafx.collections.ObservableList;

public interface EventDao {
	public ObservableList<Event> getAllEvents() throws SQLException ;
	public void AddEvent(Event ev)throws SQLException;
	public void UpdateEvent(Event ev)throws SQLException;
	public void DeleteEvent(int idEv,int idbudget)throws SQLException;
	public void DelelteAllEventOfClient()throws SQLException;
}
