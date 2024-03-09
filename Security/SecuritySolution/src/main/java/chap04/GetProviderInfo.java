package chap04; 
import java.security.*;
import java.util.*;

/**
 * Class GetProviderInfo
 * Description: This is an example of a
 * retrieeiving providers
 *
 *
 * Copyright:    Copyright (c) 2002 Wiley Publishing, Inc.
 * @author Rich Helton <rhelton@richware.com>
 * @version 1.0  01-FEB-2002
* DISCALIMER: Limit of Liability/Disclaimer of Warranty: 
* This code is used for educational purposes only. This software is provided "As Is" and 
* there are no implied warranties. While the publisher and author
* have used their best efforts in preparing this book and software, they make no
* representations or warranties and specifically disclaim any implied
* warranties of merchantability or fitness for a particular purpose. No
* warranty may be created or extended by sales representatives or written
* sales materials. The advice and strategies contained herein may not be
* suitable for your situation. You should consult with a professional where
* appropriate. Neither the publisher nor author shall be liable for any loss
* of profit or any other commercial damages, including but not limited to
* special, incidental, consequential, or other damages, including, but not limited to,
* procurement of substitute goods or services, loss of use, data, profits, or business
* interruption by cause of use or theory of use arising in any damage.
 */
class GetProviderInfo
{
   public static void main(String[] args)
   {
      System.out.println("Providers installed on your system:");
      System.out.println("-----------------------------------");
      Provider[] providerList = Security.getProviders();
      for (int i = 0; i < providerList.length; i++)
      {
         System.out.println("[" + (i + 1) + "] - Provider name: " + providerList[i].getName());
         System.out.println("Provider version number: " + providerList[i].getVersion());
         System.out.println("Provider information:\n" + providerList[i].getInfo());
         System.out.println("-----------------------------------");
      }
   }
}
