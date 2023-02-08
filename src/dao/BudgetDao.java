package dao;

import java.sql.SQLException;

import Models.Budget;
import Models.Client;
import javafx.collections.ObservableList;

public interface BudgetDao {
   public void addBudget(Budget bd)throws SQLException;
   public void updateBudget(Budget bd) throws SQLException;
   public Budget getBudgetById(int IdBud)throws SQLException;
   public void deleteBudget(int idBud)throws SQLException;
  
}
