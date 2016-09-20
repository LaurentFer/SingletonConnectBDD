package model;

public class Start {

	public static void main(String[] args){
		MaConnection myCon 	= MaConnection.getMaConnection();
		MaConnection myCon2 = MaConnection.getMaConnection();
		System.out.println(myCon+"/"+myCon2);
	}
	
}
