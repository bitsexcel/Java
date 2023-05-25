package edu.cibrtec.jframes;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class frmservidor extends JFrame implements Runnable{

	JTextArea txtmensajes;
	
	public frmservidor() {
		
		txtmensajes = new JTextArea();
		txtmensajes.setBounds(10,10,400,400);
		add(txtmensajes);
		
		setLayout(null);
		setSize(500,500);
		setVisible(true);
		Thread hilo = new Thread(this);
		hilo.start();
	
		
	}

	public static void main(String[] args) {
		new frmservidor();
		
		//Runnable r = new Thread(new frmservidor()); 
		//r.run();
		
		
		
		

	}

	@Override
	public void run() {
		try{		
		ServerSocket servidor = new ServerSocket(9091);
		Socket cli;
		
		while(true){
		cli = servidor.accept();
		DataInputStream flujo = new DataInputStream(cli.getInputStream());
		String msg =flujo.readUTF();
		txtmensajes.append("\n" + msg);
		cli.close();
		if(msg.equalsIgnoreCase("fin")){
			servidor.close();
			break;
			
		}
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
