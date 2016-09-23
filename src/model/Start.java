package model;

import java.sql.SQLException;

public class Start {

	public static void main(String[] args) throws SQLException{
		
		//Création de l'instance unique de la connection (singleton)
		MaConnection myCon 	= MaConnection.getMaConnection();
		System.out.println("Adresse de l'objet : "+ myCon);
		System.out.println("Connexion inactive :"+ myCon.isNotConnexionActive());

		//Fermeture de l'instance unique de la connection
		myCon.FermerConnexion();
		System.out.println("Adresse de l'objet : "+ myCon);
		System.out.println("Connexion inactive :"+ myCon.isNotConnexionActive());
	}
	
}
