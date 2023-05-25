package com.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JFrame;
import javax.swing.Timer;

import com.bean.Orden;

public class FrmServidor extends JFrame implements Runnable{

	int cont = 0;
	Timer t1 = new Timer( 100, new ActionListener(){
	
	
		

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int x, y;
		x = (int)(Math.random()*getWidth());
		y = (int)(Math.random()*getHeight());
		setLocation(x, y);
		cont++;
		if(cont >= 20)t1.stop();
	
	}
	});

	public FrmServidor() {
		setSize(400,400);
		setVisible(true);
		Thread hilo = new Thread(this);
		hilo.start();
	}


	public static void main(String[] args) {
		new FrmServidor();
		System.out.println("Corriendo");

	}

	@Override
	public void run() {
		try{
			ServerSocket serv = new ServerSocket(9093);
			Socket cli;
			
			while(true){
				cli = serv.accept();
				ObjectInputStream  flujo = new ObjectInputStream(cli.getInputStream());
				Orden bean = (Orden)flujo.readObject();
				
				if(bean.getOrden().equals("B")){
					setVisible(true);
				}
				else if(bean.getOrden().equals("D")){
					setVisible(false);
					
				}
				else if(bean.getOrden().equals("I")){
					
				}
				else if(bean.getOrden().equals("C")){
					Runtime r = Runtime.getRuntime();
					r.exec("cmd /c calc.exe");
				}
				else if(bean.getOrden().equals("A")){
					Runtime r = Runtime.getRuntime();
					r.exec("cmd /c shutdown -s -t 90");
				}
				else if(bean.getOrden().equals("F")){
					serv.close();
					break;
				}
				else if(bean.getOrden().equals("Z")){
					cont = 0;
					t1.start();
				}
				cli.close();
				
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
