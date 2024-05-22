/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiondb;


import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario9
 */
public class ConexionDB {
    
    public String usuario = "root";
    public String clave = "daniel";
    public String url = "jdbc:mysql://localhost:3306/world";
   // public Statement statement = null;
   // public ResultSet resultSet = null;
    
    public ConexionDB(){}
    public Connection Conectar(){
        Connection enlace = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            enlace = DriverManager.getConnection(this.url, this.usuario, this.clave);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return enlace;
    }
    
    
    
}
