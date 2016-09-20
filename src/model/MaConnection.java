package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnection {

	private static MaConnection myCon;
	
	private MaConnection(String ip, String instance, String user,String password){
		Connection myCon;
		String mesParam = "jdbc:oracle:thin:@//"+ip+":1521/"+instance;
		try {		
			//Chargement de la classe du Driver d�sir�
			Class.forName ("oracle.jdbc.OracleDriver");
			System.out.println("Driver charg�");
					
			try {
				//Pr�paration de la connection
				myCon = DriverManager.getConnection(mesParam,user,password);
				System.out.println("Connexion effectu�e");
				
			} catch (SQLException e) {
				System.out.println("Echec de la connexion");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors du chargement du driver.");
			e.printStackTrace();
		}		
	}
	
	public static MaConnection getMaConnection(){
		if (myCon==null){
			myCon = new MaConnection("195.154.70.247","XE","PRIVE","PRIVE");
		}
		return myCon;
	}
		
}
