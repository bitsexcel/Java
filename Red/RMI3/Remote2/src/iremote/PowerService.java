package iremote;

import java.math.BigInteger;
import java.rmi.RemoteException;

public interface PowerService extends java.rmi.Remote{
	
	public BigInteger square(int number)throws RemoteException;
	public BigInteger power (int num1, int num2)throws RemoteException;

}
