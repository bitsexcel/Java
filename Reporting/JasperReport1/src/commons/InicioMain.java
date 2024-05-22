package commons;

import java.awt.EventQueue;
import java.sql.Connection;

import conexiondb.ConexionDB;

public class InicioMain {
	
	private static Connection con;
	private static ConexionDB mysql;
	

	public static void main(String[] args) {
		
		mysql = new ConexionDB();
		//con = (Connection) mysql.Conectar();
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					Form1 form1 = new Form1(mysql.Conectar());
					form1.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	

	}
	
	
}
