package dao;

import java.sql.SQLException;

import Models.Provider;
import javafx.collections.ObservableList;

public interface ProviderDao {
	public ObservableList<Provider> getAllProviders() throws SQLException;
	public void addProvider(Provider prv) throws SQLException;
	public void updateProvider(Provider prv) throws SQLException;
	public void deleteProvider(Provider prv) throws SQLException;
} 
