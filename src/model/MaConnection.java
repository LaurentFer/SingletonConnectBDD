/**
 * Author : Laurent Ferrer
 * 
 * Classe destinée à créer une instance unique de connexion à une BDD (singleton)
 * 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MaConnection {
	
	private	static	final	int				TIMEOUTCONNEXION	=	2;
	private	static			Connection		myConnect			= null;
	private static 			MaConnection 	myCon				= null;
	
	//Constructeur vide
	private MaConnection(){}
	
	/**
	 * @Method d'instanciation
	 * @return MaConnection (Singleton de connexion)
	 * @throws SQLException 
	 */
	public static synchronized MaConnection getMaConnection() {
		try {
			if (myCon==null || isNotConnexionActive()){
				myCon = new MaConnection();
				myCon.EtablirConnexion("195.154.70.247","XE","DIVE","//Prive//");
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de la vérification de la non-existence d'une connexion");
			e.printStackTrace();
		}
		return myCon;
	}
	
	/**
	 * @Method d'établissement de la connexion
	 */
	private void EtablirConnexion(String ip, String instance, String user,String password){
		String mesParam = "jdbc:oracle:thin:@//"+ip+":1521/"+instance;
		try {		
			//Chargement de la classe du Driver désiré
			Class.forName ("oracle.jdbc.OracleDriver");
			System.out.println("Driver chargé");
					
			try {
				//Préparation de la connection
				myConnect = DriverManager.getConnection(mesParam,user,password);
				myConnect.setAutoCommit(false);
				System.out.println("Connexion effectuée");
				
			} catch (SQLException e) {
				System.out.println("Echec de la connexion");
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur lors du chargement du driver.");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws SQLException 
	 * @Method de fermeture de la connexion
	 */
	public void FermerConnexion() {
		try {
			myConnect.close();
			System.out.println("Connexion fermée");
		} catch (SQLException e) {
			System.out.println("Erreur lors de la fermeture de la connexion");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return booléen d'état de la connexion
	 * @throws SQLException
	 * @Method pour vérifier si la connexion n'est pas active
	 */
	public static boolean isNotConnexionActive() throws SQLException{
		if (myConnect.isValid(TIMEOUTCONNEXION)){
			return false;
		}else{
			return true;
		}
	}
	
}
