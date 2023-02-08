package Models;

import java.util.Date;

public class Tasks {
      
	private Date dateTask = new Date();
	private boolean isCompleted;
	private String name;
	public Date getDateTask() {
		return dateTask;
	}
	public void setDateTask(Date dateTask) {
		this.dateTask = dateTask;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
