package com.richware.projinfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.rmi.*;
import java.util.*;

/**
 * Class ProjectBean
 * Description: The Project EJB class
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

public class ProjectBean implements EntityBean  {
   protected EntityContext   context;

    // flag to determine whether or not the
    // bean needs to be written to storage.
    private transient boolean isDirty;    

    // container managed fields
    public String projID = null;
    public String projName = null;
    public String projDescription = null;
    public double initQuote = 0.0;
    public double discount = 0.0;
    public double actualRate = 0.0;

    //
    // constructor
    public ProjectBean() {
      System.out.println(
       "ProjectBean is created by EJB container.");
    }

    // EJB required methods
    // called after activation by the EJB container

    public void ejbActivate() throws RemoteException {
        System.out.println("ProjectBean:ejbActivate()");
    }

    // When the Home Object is called, the Home Object calls
    // this method.  Populate the attributes so that the 
    // container can
    // create the database rows needed.

    public String ejbCreate(String projID, String projName, String projDescription,
                           double initQuote, double discount,
                           double actualRate)
                throws RemoteException {

        System.out.println("ProjectBean: ejbCreate ()");

        this.projID = projID;
        this.projName   = projName;
        this.projDescription = projDescription;
        this.initQuote = initQuote;
        this.discount = discount;
        this.actualRate = actualRate;
        return this.projID;
    }

    // updates the object with data from database but 
    // we are using CMP so we do not need it.
    // Just do post-processing needed.
    public void ejbLoad() throws RemoteException {
        System.out.println("ProjectBean:ejbLoad()");
    }

    // called before passivation by the EJB container
    public void ejbPassivate() throws RemoteException {
        System.out.println("ProjectBean:ejbPassivate()");
    }

    // called after ejbCreate.  The instance has been 
    // associated with an EJB Object and we can get a 
    // reference via the context if we
    // need to.
    public void ejbPostCreate(String projID, String projName, 
        String projDescription,
        double initQuote, double discount,
        double actualRate)
        throws RemoteException {
      System.out.println("ProjectBean:ejbPostCreate()");
    }

    // this method is called by the container right before
    // removing entity information from the database.

    public void ejbRemove() throws RemoteException {
        System.out.println("ProjectBean:ejbRemove()");
    }

    // updates the database but we are using CMP so we do not 
    // need it. Just do preprocessing needed.
    public void ejbStore() throws RemoteException {
     System.out.println(
          "ProjectBean:ejbStore( " + projID + ")");
     setModified(false);
    }

    // keep the context so we can access it later if needed
    public void setEntityContext(EntityContext ecxt) 
       throws RemoteException {
     System.out.println("ProjectBean:setEntityContext()");
     context = ecxt;
    }

    // disassociate the context
    public void unsetEntityContext() throws RemoteException {
        System.out.println("ProjectBean:unsetEntityContext()");
        context = null;
    }

    //
    // business methods
    public String getProjID() throws RemoteException{
        System.out.println("ProjectBean:getID()");
       return projID;
    }
    public void setProjID(String id) throws RemoteException {
        System.out.println("ProjectBean:setID()");
       this.projID = id;
    }
    public String getProjName() throws RemoteException {
        System.out.println("ProjectBean:getName()");
       return projName;
    }
    public String getProjDescription() throws RemoteException {
        System.out.println("ProjectBean:getDescription()");
       return projDescription;
    }
    public double getDiscount() throws RemoteException {
        System.out.println("ProjectBean:getDiscount()");
       return discount;
    }
    public double getInitQuote() throws RemoteException {
        System.out.println("ProjectBean:getInitQuote()");
       return initQuote;
    }
    public double getActualRate() throws RemoteException {
        System.out.println("ProjectBean:getActual()");
       return actualRate;
    }
    public void setProjName(String name) throws RemoteException {
        System.out.println("ProjectBean:setProjName()");
        setModified(true);
        this.projName = name;
    }
   
    public void setProjDescription(String name) 
        throws RemoteException {
        System.out.println("ProjectBean:setProjDescription()");
        setModified(true);
        this.projDescription = name;
    }
    public void setInitQuote(double initQuote) 
        throws RemoteException {
        System.out.println("ProjectBean:setInitQuote()");
        setModified(true);
        this.initQuote = initQuote;
    }
    public void setDiscount(double discount) 
       throws RemoteException {
        System.out.println("ProjectBean:setDisc()");
        setModified(true);
        this.discount = discount;
    }
    public void setActualRate(double actualRate) 
       throws RemoteException {
        System.out.println("ProjectBean:setActual()");
        setModified(true);
        this.actualRate = actualRate;
    }
    // Returns whether the EJBean has been modified or not.
    // This method must be public for the container to be 
    // able to invoke it.
   public boolean isModified() {
       return isDirty;
   }
    // Sets the EJBean's modified flag.
    public void setModified(boolean flag) {
        isDirty = flag;
        System.out.println("setModified(): " + projID + (String) (flag ? ": requires saving"
                  : ": saving not required"));
    }
 }
