package ch02;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class InterfazRed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Enumeration<NetworkInterface> interfaceEnum = NetworkInterface.getNetworkInterfaces();
			System.out.printf("Name Display name\n");
			for(NetworkInterface element : Collections.list(interfaceEnum)) {
			System.out.printf("%-8s %-32s\n",
			element.getName(), element.getDisplayName());
			Enumeration<InetAddress> addresses = element.getInetAddresses();
					for (InetAddress inetAddress : Collections.list(addresses)) {
					System.out.printf(" InetAddress: %s\n",
					inetAddress);
					}
			} 
		}
			catch (SocketException ex) {
			// Handle exceptions
			}

	}

}
