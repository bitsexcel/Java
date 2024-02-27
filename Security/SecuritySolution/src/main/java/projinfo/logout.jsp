<html>
  <head>
    <title>XYZ's Projects Information</title>
  </head>
 <body>
  <blockquote>
  <h1>XYZ's Project Information </h1>

  <h2> Goodbye <%= request.getRemoteUser() %> </h2>
  
  <% session.invalidate(); %>
  
  <p> You are now logged out. 
  
 <!-- <p> Click here to <a href=<%= "\""+request.getContextPath()+"\""%>>revisit 
  the site</a>. -->
  
  </blockquote>
  </body>
  
</html>
