<html>
  <head> <title>XYZ's Projects Information</title> </head>
 <body>
  <blockquote>
  <h1>XYZ's Project Information </h1>
  <h2>Please enter your username and password:</h2>
  <p>
  <form method="POST" action="j_security_check">
  <table border=1>
    <tr> <td>Username:</td>
     <td><input type="text" name="j_username"></td>
    </tr>
    <tr> <td>Password:</td>
     <td><input type="password" name="j_password"></td>
    </tr>
    <tr> <td colspan=2 align=right><input type=submit value="Submit"> </td> </tr>
  </table>  </form>  </blockquote>
<font color ="red">
  <p>
    <% String errorStr = request.getParameter("errorMsg"); %>
    <strong> 
    <%= errorStr == null ? "":errorStr %>
    </strong>
  </p>
</font>
</body> </html>
