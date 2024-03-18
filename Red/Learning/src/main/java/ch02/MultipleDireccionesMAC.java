package ch02;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class MultipleDireccionesMAC {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
		System.out.println("Name MAC Address");
		for (NetworkInterface element : Collections.list(interfaceEnum)) {
			System.out.printf("%-6s %s\n", element.getName(), getMACIdentifier(element));
		}

	}

	public static String getMACIdentifier(NetworkInterface network) {
		StringBuilder identifier = new StringBuilder();
		try {
			byte[] macBuffer = network.getHardwareAddress();
			if (macBuffer != null) {
				for (int i = 0; i < macBuffer.length; i++) {
					identifier.append(String.format("%02X%s", macBuffer[i], (i < macBuffer.length - 1) ? "-" : ""));
				}
			} else {
				return "---";
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return identifier.toString();
	}
}
