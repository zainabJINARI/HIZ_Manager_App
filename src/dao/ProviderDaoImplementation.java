package dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Controllers.LoginController;
import Models.Provider;
import application.DataBaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ProviderDaoImplementation implements ProviderDao,Initializable{
	static Connection connection = DataBaseConn.getConnection();
	static ObservableList<Provider> Providers;
	private Provider prv;
    int id =LoginController.getUser1().getID();
	public Provider getPrv() {
		return prv;
	}
	public static ObservableList<Provider> getProviders() {
		return Providers;
	}
	public static void setProviders(ObservableList<Provider> providers) {
		Providers = providers;
	}
	public void setPrv(Provider prv) {
		this.prv = prv;
	}

	@Override
	public  ObservableList<Provider> getAllProviders() throws SQLException {
		 String query = "select * from provider where user_idUser = ?";
	        PreparedStatement ps
	            = connection.prepareStatement(query);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        Providers=FXCollections.observableArrayList();
	  
	        while (rs.next()) {
        	Provider prv = new Provider();
        	prv.setName(rs.getString("nameProv"));
        	prv.setEmail(rs.getString("mailProv"));
        	prv.setPhoneNum(rs.getString("phoneNumProv"));
        	prv.setLevelOfProvider(rs.getString("providerLevel"));
        	prv.setID(rs.getInt("idProvider"));
            Providers.add(prv);
		
	}
	      
	
		return Providers;
	}

	
	
	

	@Override
	public void addProvider(Provider prv) throws SQLException {
		String query
        = "insert into provider(mailProv, "
          + "nameProv,phoneNumProv,providerLevel,user_idUser) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, prv.getEmail());
		ps.setString(2, prv.getName());
		ps.setString(3, prv.getPhoneNum());
		ps.setString(4, prv.getLevelOfProvider());
		ps.setInt(5, id);
		
		ps.executeUpdate();
		System.out.println("provider added");
		Providers.add(prv);	
	}


	@Override
	public void updateProvider(Provider prv) throws SQLException {
		String query = "update provider set mailProv=?, "
          + " nameProv= ?, phoneNumProv = ? ,providerLevel = ? where idProvider = ?";
		PreparedStatement ps
			= connection.prepareStatement(query);
		System.out.println(prv.getEmail());
		ps.setString(1, prv.getEmail());
		ps.setString(2, prv.getName());
		ps.setString(3, prv.getPhoneNum());
		ps.setString(4, prv.getLevelOfProvider());
		ps.setInt(5, prv.getID());
		ps.executeUpdate();
		System.out.println("Provider is updated**");
		
		
	}


	@Override
	public void deleteProvider(Provider prv) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from provider where idProvider =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, prv.getID());
		ps.executeUpdate();
		Providers.remove(Providers.indexOf(prv)) ;
		System.out.println("Delelte provider");
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			getAllProviders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void delelteAllProviders(int idUser)throws SQLException{
		String query = "delete from provider where user_idUser =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1,idUser);
		ps.executeUpdate();
	}
	
	
	


}
