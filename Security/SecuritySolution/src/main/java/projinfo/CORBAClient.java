package com.richware.projinfo;

import java.util.*;
import org.omg.CosNaming.*;
import org.omg.CosTransactions.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;

/**
 * Class CORBAClient
 * Description: The CORBA client class code that invokes methods on the Project EJB 
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

public class CORBAClient {
  public static main(String[] args) throws Exception {
    // init the ORB
    org.omg.CORBA.ORB orb = org.imprise.ejb.Global.orb(); 
   // obtain naming context 
    NamingContext nctx = NamingContextHelper.narrow(
        orb.resolve_initial_reference(“NameService”));
    // look up home object
    NameComponent[] components = {
        new NameComponent(ProjectHome”,””)}
    ProjectHome home = ProjectHomeHelper.narrow(nctx.resolve(
        components));
    // get the OTS Current interface
    Current cTrn = CurrentHelper.narrow (
        orb.resolve_initial_references(“TransactionCurrent”));
    cTrn.begin();
    // create the Project object and use it
    Project projs = home.create();
        Collection prjcol = projectHome.findAll();
    System.out.println("there are " + prjcol.size() 
          + “ projects.”); 
    // now remove the object and commit the transaction
    projs.remove();
    // commit the transaction
    cTrn.commit(true);
 }
}
