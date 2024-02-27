package com.richware.projinfo;

import java.rmi.*;
import javax.ejb.*;

/**
 * Interface Project
 * Description: The Project EJB remote interface
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

public interface Project extends EJBObject {
    public String getProjID() throws RemoteException;
    public String getProjName() throws RemoteException;
    public String getProjDescription() throws RemoteException;
    public double getInitQuote() throws RemoteException;
    public double getDiscount() throws RemoteException;
    public double getActualRate() throws RemoteException;
    public void setProjID(String val) throws RemoteException;
    public void setProjName(String val) throws RemoteException;
    public void setProjDescription(String val) 
          throws RemoteException;
    public void setInitQuote(double val) 
          throws RemoteException;
    public void setDiscount(double val) 
          throws RemoteException;
    public void setActualRate(double val)
          throws RemoteException;
}
