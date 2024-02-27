<!doctype html public "-//w3c/dtd HTML 4.0//en">
<html>
<!-- The Project Detail Tables -->
<head>
<title>Project Detail</title>
</head>
<center>
<h2>
<font color=#DB1260>
  <p> 
    <% String projname = request.getParameter("projname"); 
       String projId = request.getParameter("projid"); 
    %>
    <strong> 
    <%= projname == null ? "": projname %>
    </strong>
    Project Details
  </p>
</font> </h2> </center> <p/><p/>
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
<body>
<%
try {
  conn = getCon();
  if (conn != null) {
    String selectTeam= "select EAUSER.* from PROJ_TEAM, EAUSER WHERE PROJ_TEAM.projid = '" + projId + "' and EAUSER.userid =  PROJ_TEAM.userid";
    Statement stmtTeam = conn.createStatement();
    stmtTeam.execute(selectTeam);
    ResultSet dsTeam = stmtTeam.getResultSet();      
%>
      <center><h2>Project Team </h2></center>
      <table border=1 cellpadding=5>
       <th>Department </th>
       <th>Main contact </th>
<%
      while (dsTeam.next()) {        
%>
        <tr>
         <td><%= dsTeam.getString("department") != null ? dsTeam.getString("department") : "&nbsp;" %> </td>
         <td><%= dsTeam.getString("name") != null ? dsTeam.getString("name") : "&nbsp;" %></td> 
       </tr>
<%
      }
%>      
      </table>
<%
    dsTeam.close();
    String selectStm = "select ARTIFACT.* from ARTIFACT,PROJECT_ARTIFACT WHERE PROJECT_ARTIFACT.projid = '" + projId + "' and PROJECT_ARTIFACT.ARTIFACT_ID_VERSION = ARTIFACT.ARTIFACT_ID_VERSION";
    Statement stmt = conn.createStatement();
    stmt.execute(selectStm);
    ResultSet ds = stmt.getResultSet();      
 %>
      <center><h2>Project Artifacts </h2></center>
      <table border=1 cellpadding=5>
       <th>Artifact name </th>
       <th>Artifact description </th>
       <th>Artifact location</th>
<%
      while (ds.next()) {        
%>
        <tr>
         <td><%= ds.getString("ARTIFACT_NAME") != null ? ds.getString("ARTIFACT_NAME") : "&nbsp;" %> </td>
         <td><%= ds.getString("ARTIFACT_DESCRIPTION") != null ? ds.getString("ARTIFACT_DESCRIPTION") : "&nbsp;" %></td>
         <td><%= ds.getString("ARTIFACT_LOCATION") != null ? ds.getString("ARTIFACT_LOCATION") : "&nbsp;" %></td>
        </tr>
<%
      }
%>      
      </table>
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
<p> </body> </html>
