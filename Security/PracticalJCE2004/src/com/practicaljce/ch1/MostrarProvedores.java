package com.practicaljce.ch1;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.Map;

public class MostrarProvedores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			//Dynamica11y register our Cryptix provider //without requiring java. security modification
			//P1ace the provider in the fifth position
			Provider prov = new cryptix.jce.provider.CryptixCrypto() ;
			Security.insertProviderAt(prov, 14) ;
			//if( "all".equalsIgnoreCase(args [0] ) )
			if( "all".equalsIgnoreCase("all" ) )
			{
				Provider[] providers = Security. getProviders() ;
				for(int i = 0; i < providers.length; i++)
				{
					System.out.println("********************"); 
					System.out.println(" ** Provider : " +providers[i].getName()) ;
					System.out.println("********************");
					System.out.print(providers[i].toString());
					System.out.print(" is formally referred to as '" +
							providers[i].getName() + "'") ;
					System.out.println(" in a get Instance() factory method") ;
					System.out.println( "" ) ;
					System.out.println( "Total Providers : " + providers.length);
				} }
				else {
					Provider provider = Security.getProvider(args[0])  ;
					System.out.println(provider.getName() +  "formally supports: "); 
					Iterator iter = provider.entrySet().iterator();
					while (iter.hasNext ( ))
					{
						Map.Entry entry = (Map.Entry) iter.next(); 
						System.out.println("\t" + entry.getKey() + " = " + entry.getValue());

					} 
				}
			}

			catch (NullPointerException nspe) {
				//NPE means Provider wasn't found!
				System.err.print("The requested provider is not installed in the JR.E");
			} 
			catch(ArrayIndexOutOfBoundsException aioobe) {
				System.err.println( "Usage: java ProviderDetail providerName");
				System.err.println( "Set providerName to 'all' to list all") ;
			}


		}
	}
