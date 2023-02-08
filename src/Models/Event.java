package Models;

import java.sql.Timestamp;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Event {
	
	
	private SimpleIntegerProperty idClient = new SimpleIntegerProperty();
	private SimpleIntegerProperty ID = new SimpleIntegerProperty();
	private SimpleIntegerProperty idProvider = new SimpleIntegerProperty();
	private SimpleIntegerProperty idPartyR = new SimpleIntegerProperty();
	private SimpleIntegerProperty idBudget = new SimpleIntegerProperty();
    
    private final ObjectProperty<Date> startDate = new SimpleObjectProperty<>(this, "startDate");
    private SimpleStringProperty MoreDetails = new SimpleStringProperty();
    private final ObjectProperty<Date> endDate = new SimpleObjectProperty<>(this, "endDate");
    private SimpleIntegerProperty nbrGuets = new SimpleIntegerProperty() ;
    private SimpleStringProperty typeOfEvent = new SimpleStringProperty();

    
	public SimpleStringProperty getMoreDetailsProperty() {
		return MoreDetails;
	}
	public void setMoreDetails(String moreDetails) {
		MoreDetails.set(moreDetails);;
	}
	public String getMoreDetails() {
		return MoreDetails.get();
	}
	public SimpleIntegerProperty getIDProperty() {
		return ID;
	}
	
	public int getID() {
		return ID.get();
	}
	public void setID(int iD) {
		ID.set(iD);
	}
	public SimpleIntegerProperty getIdClientProperty() {
		return idClient;
	}
	
	public int getIdClient() {
		return idClient.get();
	}
	public void setIdClient(int iD) {
		idClient.set(iD);
	}
	public int getIdProvider() {
		return idProvider.get();
	}
	public void setIdProvider(int iD) {
		idProvider.set(iD);
	}
	public SimpleIntegerProperty getIdProviderProperty() {
		return idProvider;
	}
	public int getIdPartyR() {
		return idPartyR.get();
	}
	public void setIdPartyR(int iD) {
		idPartyR.set(iD);
	}
	public SimpleIntegerProperty getIdPartyRProperty() {
		return idPartyR;
	}
	public int getIdBudget() {
		return idBudget.get();
	}
	public void setIdBudget(int iD) {
		idBudget.set(iD);
	}
	public SimpleIntegerProperty getIdBudgetProperty() {
		return idBudget;
	}
	

	
				
	public final void setStartDate(Date startDate) {
			   this.startDate.set(startDate);
	}
				
	public final Date getStartDate() {
			 return startDate.get();
	}
	public final ObjectProperty<Date> startDateProperty() {
			return startDate;
	}
	

    public final void setEndDate(Date endDate) {
        this.endDate.set(endDate);
    }

    public final Date getEndDate() {
        return endDate.get();
    }

   
	public final ObjectProperty<Date> endDateProperty() {
        return endDate;
    }


	
	
	public Event( int provider,Date Start ,Date End, String moreDetails, int budget,int party,
			int nbrGuets, String typeOfEvent) {
		super();
		
		this.idProvider.set(provider);
		this.MoreDetails.set(moreDetails);
		this.idBudget.set(budget);
		this.idPartyR.set(party);
		this.nbrGuets.set(nbrGuets);
		this.typeOfEvent.set(typeOfEvent);
		this.startDate.set(Start);
		this.endDate.set(End);
	}
	public Event() {
		super();
	}
	
	
	public int getNbrGuets() {
		return nbrGuets.get();
	}
	public SimpleIntegerProperty getNbrGuetsProperty() {
		return nbrGuets;
	}
	
	public void setNbrGuets(int nbrGuets) {
		this.nbrGuets.set(nbrGuets); 
	}

	public SimpleStringProperty getTypeOfEventProperty() {
		return typeOfEvent;
	}
	public String getTypeOfEvent() {
		return typeOfEvent.get();
	}
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent.set(typeOfEvent);
	}
	
	
}