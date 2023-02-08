package dao;

import java.sql.SQLException;


import Models.PartyRoom;
import javafx.collections.ObservableList;

public interface RoomsDao {
	public ObservableList<PartyRoom> getAllRooms()throws SQLException;
	public void addRooms(PartyRoom rms)throws SQLException;
	public void updateRooms (PartyRoom rms) throws SQLException;
	public void deleteRooms(PartyRoom rms) throws SQLException;
	
}