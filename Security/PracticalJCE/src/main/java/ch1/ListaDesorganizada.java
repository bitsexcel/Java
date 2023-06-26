package ch1;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.Map;

public class ListaDesorganizada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		//Lookup the named provider using its formal name
//		Provider provider = Security.getProvider(args [ 0 ] ) ;
                Provider provider = Security.getProvider("BC" ) ;
		System. out. print (provider. getName ()) ;
		System.out.println(" formally supports the following algorithms:");
		//Step over the list of supported algorithms
		Iterator iter = provider.entrySet().iterator();
		while(iter.hasNext())
		{
		Map.Entry entry = (Map.Entry) iter.next();
		System.out.println("\t" +
				entry.getKey() + " = " + entry.getValue());
				}
				} catch (NullPointerException nspe)
				{
				//NPE means Provider wasn't found!
				System.err.println("The provider you requested is not installed in the 	JRE");
				} catch (ArrayIndexOutOfBoundsException aioobe)
				{
				System.err.println("Usage: java ProviderDetail providerName");
				}

	}

}
