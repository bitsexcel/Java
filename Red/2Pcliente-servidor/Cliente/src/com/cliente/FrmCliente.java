package com.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.bean.Orden;

public class FrmCliente extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txtip, txtorden;
	JButton btnenviar;

	public FrmCliente() {
		txtip = new JTextField();
		txtip.setBounds(10, 10, 100, 20);
		
		txtorden = new JTextField();
		txtorden.setBounds(10, 30, 100, 20);
		
		btnenviar = new JButton("Enviar");
		btnenviar.setBounds(10, 50, 100, 20);
		btnenviar.addActionListener(this);
		
		add(txtip);
		add(txtorden);
		add(btnenviar);
		
		setLayout(null);
		setSize(400, 400);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new FrmCliente();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnenviar){
			try{
				Socket cli = new Socket("127.0.0.1", 9093);
				Orden bean = new Orden();
				bean.setIp(txtip.getText());
				bean.setOrden(txtorden.getText());
				ObjectOutputStream flujo = new ObjectOutputStream(cli.getOutputStream());
				flujo.writeObject(bean);
				cli.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		
	}

}
