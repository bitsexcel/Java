<html>
  <head> <title>XYZ's Projects Information</title> </head>
<body> <blockquote>
  <h1>XYZ's Project Information </h1>
  <h2>  <p> Welcome <%= request.getRemoteUser() %> </h2>
  <p> 
This application allows for you to view project information of XYZ Company based on your access rights.
 </p>
 <p>
<form method="Get" action="jasaprjsel">
 <td COLSPAN="2" ALIGN="right"> <input type="SUBMIT" VALUE="Enter"> </td>
 <% if (request.getRemoteUser() != null) { %>
    <p> Click here to <a href="logout.jsp"> logout</a>. 
  <% } %>    
</blockquote> </body> </html>
