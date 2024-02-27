<!doctype html public "-//w3c/dtd HTML 4.0//en">
<% response.addHeader("Pragma","No-cache");
 response.addHeader("Cache-Control","no-cache");
response.addDateHeader("Expires",1); %>
  
<html>
<head>
<title>Edit Project Details</title>
</head>

  <body>
  <blockquote>
  <h2> Edit Project Details</h2>
  <p>
  <b>

  <% String projname = request.getParameter("projname"); 
       String projId = request.getParameter("projid"); 
    %>

<%@ page import="
weblogic.db.jdbc.*, 
java.sql.*
" %>

<%!
Connection conn  = null;
String jdbcClass = "COM.cloudscape.core.JDBCDriver";
String jdbcURL   = "jdbc:cloudscape:demo";

public Connection getCon() {
  try {
	  Driver myDriver = (Driver) Class.forName(jdbcClass).newInstance();
	  conn = myDriver.connect(jdbcURL, null);    
    
  } catch (Exception e) {}
  return conn;
}
%>
<%
try {
  conn = getCon();
  if (conn != null) {
    String selectProj = "select * from EAPROJ where EAPROJ.projid= '" +  projId  + "'";
    Statement stmtProj = conn.createStatement();
    stmtProj.execute(selectProj);
    ResultSet dsProj = stmtProj.getResultSet();      
%>

  <form method="POST" action="jasaedit">
 <table>
 <input type="hidden" name="type" value="projdesc"> </td> 
    <tr> 
        <td>Project Name: </td> 
         <%= ds.getString("name") != null ? ds.getString("name") : "&nbsp;" %>
        <% if (request.isUserInRole("manager")) { %>
        <td> Change to: <input type="text" name="projname"> </td>
       <% } %>       
     </tr>

    <tr> 
        <td>Project Description: </td>
         <%= ds.getString("DESCRIPTION") != null ? ds.getString("DESCRIPTION") : "&nbsp;" %>
        <% if (request.isUserInRole("manager")) { %>
        <td> Change to: <input type="text" name="projdesc"> </td>
       <% } %>       
     </tr>

     <% if (reuqest.isUserInRole("finance")) { %>
     <tr> 
        <td>Inital Quote: </td>
         <%= ds.getString("initquote") != null ? ds.getString("initquote") : "&nbsp;" %>
        <% if (request.isUserInRole("manager")) { %>
        <td> Change to: <input type="text" name="iquote"> </td> 
         <% } %>               
     </tr>

    <tr> 
        <td>Discount: </td>
         <%= ds.getString("discount") != null ? ds.getString("discount") : "&nbsp;" %>
        <% if (request.isUserInRole("manager")) { %>
        <td> Change to: <input type="text" name="disc"> </td> 
         <% } %>               
    </tr> 

    <tr>
        <td>Actual Quote: </td>
         <%= ds.getString("actualrate") != null ? ds.getString("actualrate") : "&nbsp;" %>
        <% if (request.isUserInRole("manager")) { %>
        <td> Change to: <input type="text" name="actual"> </td> 
         <% } %>               
     </tr>
    <% } %>
<% if (request.isUserInRole("manager")) { %>
     <tr> <td COLSPAN="2" ALIGN="right">
         &nbsp;&nbsp;
         <input type="SUBMIT" name="finance" VALUE="Submit project Info">
      </td> </tr>
<% } %>
  </table>
  </form>

  <h2>Modify team member information</h2>

<%
    String selectTeam= "select EAUSER.* from PROJ_TEAM, EAUSER WHERE PROJ_TEAM.projid = '" + projId + "' and EAUSER.userid =  PROJ_TEAM.userid";
    Statement stmtTeam = conn.createStatement();
    stmtTeam.execute(selectTeam);
    ResultSet dsTean = stmtTeam.getResultSet();      
%>

    <form method="POST" action="jasaedit">
      <table border=1 cellpadding=5>
 <input type="hidden" name="type" value="team"> </td> 
       <th>Department </th>
       <th>Main contact </th>
    <tr> 
<%
      while (dsTeam.next()) {        
%>
        <tr>
         <td> <%= dsTeam.getString("department") != null ? ds.getString("department") : "&nbsp;" %>  </td>
         <td> <%= dsTeam.getString("name") != null ? ds.getString("name") : "&nbsp;" %> </td> 
         <% if (request.isUserInRole("manager")) { %>
          <td >  <input type="SUBMIT" VALUE="Remove">  </td> 
         <% } %>
       </tr>
<%
      }
%>
   <% if (request.isUserInRole("manager")) { %>
    <tr>      
        <td>New Name: <input type="text" name="deptname"> </td>
        <td>New Contact:  <input type="text" name="contactname"> </td>
        <td COLSPAN="2" ALIGN="right"> <input type="SUBMIT" VALUE="Add member">  </td> 
     </tr>
   <% } %>
    </table>
  </form>

  <h2>Modify project artifact information</h2>
  <form method="POST" action="jasaedit">
<%
    dsTeam.close();
    String selectStm = "select ARTIFACT.* from ARTIFACT,PROJECT_ARTIFACT WHERE PROJECT_ARTIFACT.projid = '" + projId + "' and PROJECT_ARTIFACT.ARTIFACT_ID_VERSION = ARTIFACT.ARTIFACT_ID_VERSION";
    Statement stmt = conn.createStatement();
    stmt.execute(selectStm);
    ResultSet ds = stmt.getResultSet();      
 %>

      <table border=1 cellpadding=5>
 <input type="hidden" name="type" value="art"> </td> 
       <th>Artifact name </th>
       <th>Artifact description </th>
       <th>Artifact location</th>
    <tr> 
<%
      while (ds.next()) {        
%>
        <tr>
         <td> <%= ds.getString("ARTIFACT_NAME") != null ? ds.getString("ARTIFACT_NAME") : "&nbsp;" %> </td>
         <td>  <%= ds.getString("ARTIFACT_DESCRIPTION") != null ? ds.getString("ARTIFACT_DESCRIPTION") : "&nbsp;" %> </td>
         <td>  <%= ds.getString("ARTIFACT_LOCATION") != null ? ds.getString("ARTIFACT_LOCATION") : "&nbsp;" %> </td>
          <% if (request.isUserInRole("architect")) { %>
          <td >  <input type="SUBMIT" name="remove" VALUE="Remove">  </td> 
          <% } %>
       </tr>
<%
      }
%>
   <% if (request.isUserInRole("architect")) { %>     
   <tr> 
        <td>New Name: <input type="text" name="artname"> </td>
        <td>New Description: <input type="text" name="artDesc"> </td>
        <td>New Location: <input type="text" name="artLoc"> </td>
        <td COLSPAN="3" ALIGN="right">  <input type="SUBMIT" name="add" VALUE="Add Artifact">  </td> 
    </tr>
   <% } %>
  </table>
  </form>

<%
    ds.close();
     conn.close();
     out.flush();
     }
    else {
     out.print("Sorry. Database is not available.");
   }
 } catch (Exception e) {
  out.print("Exception: " + e);
}
%>


  <p><a href="../welcome.jsp">Return to welcome page</a>  
  <p><a href="../login.jsp">Return to login page</a>
  </b>
  </blockquote>
  </body>
</html>

