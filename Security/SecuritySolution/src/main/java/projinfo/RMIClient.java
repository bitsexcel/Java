package com.richware.projinfo;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.*;
import javax.util.Properties;
import javax.transaction.UserTransaction;

/**
 * Class RMIClient
 * Description: The RMI client class code that invokes methods on the Project EJB 
 *
 * Copyright:    Copyright (c) 2002
 * Company:      HungryMinds
 * @author Johennie Helton <jhelton@richware.com>
 * @version 1.0 
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

public class RMIClient {
  public static void main(String[] args) {
   try {
    // Get system properties for JNDI 
    Properties prop = System.getProperties();
    Context ctx = new InitialContext(prop);
    ProjectHome home = (ProjectHome)
         javax.rmi.PortableRemoteObject.narrow(
         ctx.lookup(“ProjectHome”),ProjectHome.class); 
    // now use JNDI to find the JTA UserTransaction interface
    // and start the transaction
    UserTransaction utr = (UserTransaction)
         ctx.lookup(“javax.transaction.UserTransaction”); 
    utr.begin();
    // create the Project object and use it
    Project projs = home.create();
    Collection prjcol = projectHome.findAll();
    System.out.println("there are " + prjcol.size() 
          + “ projects.”); 
    // now remove the object and commit the transaction
    projs.remove();
    utr.commit();
  } catch (Exception e) {
     e.printStackTrace();
  }
  }
}
