package application;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConn {

	

	
		
		 public static Connection connection = null;
		
			static {
			    String databaseName = "hiz_manager";
	            String databaseUser = "root";
			    String databasePassword = "05052003";
	            String url = "jdbc:mysql://localhost:3306/"+databaseName+"?autoReconnect=true&useSSL=false&characterEncoding=utf8";
	     //jdbc:mysql://localhost:3306/"+databaseName+"?autoReconnect=true&useSSL=false";
			    
			    
			   try {
				   System.out.println("avant connection");
				   Class.forName("com.mysql.cj.jdbc.Driver");
				   connection =  DriverManager.getConnection(url,databaseUser,databasePassword);
				   System.out.println("apr�s connexion");
			   }catch (Exception e) {
				   e.printStackTrace();
				   e.getCause();
			   }
			}
			
			public static Connection getConnection()
		    {
		        return connection;
		    }
	


}