package com.bean;

import java.io.Serializable;

public class Orden implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ip;
	private String orden;
	
	
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
