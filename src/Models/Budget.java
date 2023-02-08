package Models;



import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Budget {
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
    public SimpleIntegerProperty getIDProperty() {
		return ID;
	}
	public void setID(int iD) {
		ID.set(iD);
	}
	  public int getID() {
			return ID.get();
		}
	private SimpleDoubleProperty avance= new SimpleDoubleProperty();
    private SimpleDoubleProperty totalBudget= new SimpleDoubleProperty();
    private SimpleDoubleProperty budgetRoom= new SimpleDoubleProperty();
    private SimpleDoubleProperty budgetProvider= new SimpleDoubleProperty();
	public SimpleDoubleProperty getAvanceProperty() {
		return avance;
	}
	public void setAvance(Double avance) {
		this.avance.set(avance);
	}
	public Double getAvance() {
		return avance.get();
	}
	public SimpleDoubleProperty getTotalBudgetProperty() {
		return totalBudget;
	}
	public Double getTotalBudget() {
		return totalBudget.get();
	}
	public void setTotalBudget(Double totalBudget) {
		this.totalBudget.set(totalBudget); 
	}
	public SimpleDoubleProperty getBudgetRoomProperty() {
		return budgetRoom;
	}
	public void setBudgetRoom(Double budgetRoom) {
		this.budgetRoom.set(budgetRoom);
	}
	public Budget() {
		super();
	}
	public Budget(Double avance, Double totalBudget, Double budgetRoom,
			Double budgetProvider) {
		super();
		this.avance.set(avance);;
		this.totalBudget.set(totalBudget);
		this.budgetRoom.set(budgetRoom);
		this.budgetProvider.set(budgetProvider); 
	}
	public Double getBudgetRoom() {
		return budgetRoom.get();
	}
	
	public SimpleDoubleProperty getBudgetProviderProperty() {
		return budgetProvider;
	}
	
	public void setBudgetProvider(Double budgetProvider) {
		this.budgetProvider.set(budgetProvider);
	}
	public Double getBudgetProvider() {
		return budgetProvider.get();
	}
    
}