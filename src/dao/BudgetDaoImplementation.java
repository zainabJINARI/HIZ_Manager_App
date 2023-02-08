package dao;

import java.sql.Connection;

import Models.Budget;
import Models.Client;
import Models.Event;
import Models.User;
import application.DataBaseConn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class BudgetDaoImplementation implements BudgetDao{
	/**
	 * The id attribute is where the id of the new budget created is stored so that you can add it to the event object later 
	 */
	private static int id ;

	/**
	 * @return the id
	 */
	public static int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public static void setId(int id) {
		BudgetDaoImplementation.id = id;
	}

	static Connection connection = DataBaseConn.getConnection();
	ObservableList<Budget> Budgets=FXCollections.observableArrayList();

	@Override
	public void addBudget(Budget bd) {
		String sql = "INSERT INTO budget (avance, totalBudget, roomBudget,providerBudget) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		    statement.setDouble(1, bd.getAvance());
		    statement.setDouble(2, bd.getTotalBudget());
		    statement.setDouble(3, bd.getBudgetRoom());
		    statement.setDouble(4, bd.getBudgetProvider());
		    statement.executeUpdate();
		    try (ResultSet rs = statement.getGeneratedKeys()) {
		        if (rs.next()) {
		            id = rs.getInt(1);
		            System.out.println("Generated ID: " + id);
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		Budgets.add(bd);
		
    }
	@Override
	public void updateBudget(Budget bd) throws SQLException {
		String query = "update budget set avance=?, "
          + " totalBudget= ?, roomBudget = ? ,providerBudget = ? where idBudget = ?";
		PreparedStatement ps
			= connection.prepareStatement(query);
		
		ps.setDouble(1, bd.getAvance());
		ps.setDouble(2, bd.getTotalBudget());
		ps.setDouble(3, bd.getBudgetRoom());
		ps.setDouble(4, bd.getBudgetProvider());
		ps.setInt(5, id);
		ps.executeUpdate();
		System.out.println("Budget Updated**"+bd.getID());
		
		
		
	}

	@Override
	public Budget getBudgetById(int IdBud) throws SQLException {
	
			   System.out.println(IdBud);
				String query
		        = "select * from budget where idBudget = ?";
				PreparedStatement ps
					= connection.prepareStatement(query);

				ps.setInt(1,IdBud);
				Budget budget = new Budget();
				
				ResultSet rs = ps.executeQuery();
				
				boolean check = false;
		      
				while (rs.next()) {
		        check = true;
		        budget.setAvance(rs.getDouble("avance"));
		        budget.setBudgetProvider(rs.getDouble("providerBudget"));
		        budget.setTotalBudget(rs.getDouble("totalBudget"));
		        budget.setBudgetRoom(rs.getDouble("roomBudget"));
		    	
				budget.setID(IdBud);
				}
				if (check) {
					return budget;
				}
				else	return null;
			}

	@Override
	public void deleteBudget(int idBud) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from budget where idBudget =?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, idBud);
		ps.executeUpdate();
		
		System.out.println("Delelte budg "+idBud);
		
	}
		
	
}
