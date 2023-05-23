/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

/**
 *
 * @author Oficina
 */
public class Ayuda {
    Key key;
    public Ayuda(){
        System.out.println("iniciando ayuda class!");
        
    }
    
    public Key getk() throws IOException, ClassNotFoundException{
        try{
        System.out.println("iniciando getk");
         ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("C:\\Users\\Oficina\\Documents\\NetBeansProjects\\JavaCrypt\\SecretKey.ser)"));
            key = (Key)in.readObject();
            in.close();
        }
        catch(FileNotFoundException ex){
            createf();
        }
            return key;
    }
    public void createf() throws IOException{
        System.out.println("iniciando createf");
         ObjectOutputStream  out = new ObjectOutputStream(
                    new FileOutputStream("C:\\Users\\Oficina\\Documents\\NetBeansProjects\\JavaCrypt\\SecretKey.ser"));
            out.writeObject(key);
            out.close();
    }
    public String encry(){
        return new String("hi");
        
    }
    public void decryp(){
        
    }
}
