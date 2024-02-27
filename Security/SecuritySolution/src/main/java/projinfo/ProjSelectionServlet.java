package com.richware.projinfo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 * Class ProjSelectionServlet
 * Description: The ProjSelectionServlet displays the main page
 *  for this example.
 * If the user has access, it displays the list of all projects
 *  and their rates.
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

public class ProjSelectionServlet extends HttpServlet {
  private ProjectHome projectHome;
  private Vector projects;
 /**
  * Initialize the servlet with all the EJB home objects needed.
  */
 public void init(ServletConfig config) throws ServletException {
    super.init(config);
 try {
        /*
        * Use JNDI for the initialization parameters.
        * Get the initial JNDI context & lookup the user home object
        */
         Context ctx = new InitialContext();
         projectHome = (ProjectHome)ctx.lookup("projinfo.ProjectHome");
        /*
         * get all projects
         */
         Collection col = projectHome.findAll();
         Iterator it = col.iterator();
         this.projects = new Vector();
         while (it.hasNext()) {
          Project prjInfo = (Project) PortableRemoteObject.narrow(it.next(), Project.class);
           this.projects.addElement(prjInfo);
         }
      } catch (Exception e) {
        getServletConfig().getServletContext().log(e," ");
        throw new ServletException(e.toString());
      }
}
  /**
   * Service a request
   */
   public void service (HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
     String urlStr = resp.encodeURL("jasaprojs");
    /*
     * make sure the user is authenticated. If not redirect to login
     */
     HttpSession session = req.getSession(false);
     if(session == null) {
       doRedirectToLogin(req,resp,"Please login first.");
      return;
     }
     /**
      * display the project list w/ a logout option at the top
      */
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    StringBuffer htmlCode = new StringBuffer();
    htmlCode.append("<!doctype html public \"-//w3c/dtd HTML 4.0//en\">");
    htmlCode.append("<% response.addHeader(\"Pragma\",\"No-cache\"); \n");
    htmlCode.append("   response.addHeader(\"Cache-Control\",\"no-cache\"); \n ");
    htmlCode.append("   response.addDateHeader(\"Expires\",1); %> \n");
    htmlCode.append("<html> <head><title>Project List</title></head>\n");
    htmlCode.append("<body bgcolor=\"#FFFFFF\"> <center> <hr><br> &nbsp; \n");
    htmlCode.append("<h1> <font size=\"+2\" color=\"red\">Welcome to XYZ's Projects</font> \n");
    htmlCode.append("</h1> </center> <br> &nbsp; <hr> <br> &nbsp; \n");
    htmlCode.append("<a href=logout.jsp>Logout</a>");
    htmlCode.append("<table x:str border=1.0pt solid windowtext;");
    htmlCode.append(" cellpadding=0 cellspacing=0 width=1111> <tr>");
    htmlCode.append(" <td>Project ID</td> \n");
    htmlCode.append(" <td>Project Name</td> \n ");
    htmlCode.append(" <td>Project Description</td> \n");
    // if the user has access to financial information show quotes and discounts
    if (req.isUserInRole("finance")) {
      htmlCode.append("<td>Initial Quote</td> \n <td>Discount</td> \n");
      htmlCode.append("<td>Actual Rate</td>  \n");
    }
   // if the user is an administrator then show edit choices
   if (req.isUserInRole("admin")) { 
      htmlCode.append("<td>Edit Option</td> \n"); 
    }
    for (int i=0; i < projects.size(); i++){
        Project prjInfo = (Project) projects.elementAt(i);
        htmlCode.append("<tr> <td> <a href=\"projectDetail.jsp?projid=");
        htmlCode.append(prjInfo.getProjID());
        htmlCode.append("&projname="); htmlCode.append(prjInfo.getProjName());
        htmlCode.append("\">");
        htmlCode.append(prjInfo.getProjID());
        htmlCode.append("</a>");
        htmlCode.append("</td> <td> \n");
        htmlCode.append(prjInfo.getProjName());
        htmlCode.append("</td> <td> \n");
        htmlCode.append(prjInfo.getProjDescription());
        htmlCode.append("</td>");
        if (req.isUserInRole("finance")) {
          htmlCode.append("<td> \n");
          htmlCode.append(prjInfo.getInitQuote());
          htmlCode.append("</td> <td> \n");
          htmlCode.append(prjInfo.getDiscount());
          htmlCode.append("</td> <td> \n");
          htmlCode.append(prjInfo.getActualRate());
          htmlCode.append("</td>");
        }
        if (req.isUserInRole("admin")) { 
          htmlCode.append("<td> <a href=\"admin\editproj.jsp?projid=");  htmlCode.append(prjInfo.getProjID());
          htmlCode.append("&projname="); htmlCode.append(prjInfo.getProjName());
         htmlCode.append("\">Edit</a> </td>"); 
        }
        htmlCode.append("</tr> \n");
   }
   htmlCode.append("</table>");
   htmlCode.append("</form></body></html>");
   writer.println( htmlCode.toString());
   writer.close();
}
   private void doRedirectToLogin(HttpServletRequest request,
           HttpServletResponse response, String msg)
    throws IOException, ServletException {
    String url = "/login.jsp?errorMsg=" + msg;
    response.sendRedirect(response.encodeURL(url));
}
}
