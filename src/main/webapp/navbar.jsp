<!DOCTYPE html>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.akash.struts.UserBean"%>
<%@page import="com.akash.struts.Utils"%>
<%@page import="com.akash.struts.DbConnection"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #edeef7;overflow:hidden">

<nav class="navbar navbar-inverse" style="border-radius:0px">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" style="color:whitesmoke">Zoho</a>
    </div>
    <ul class="nav navbar-nav">
    <%
   		String level="";
		try {
			Cookie cw[] = request.getCookies();
			for(Cookie c : cw) {
				if(c.getName().equals("username")) {
					if(!c.getValue().toLowerCase().equals("admin")){					
						level = DbConnection.getLevelName(c.getValue());
					}
				}
			}
		} catch(Exception e) {
		}		
		ResultSet rs = DbConnection.getTabs(level);
		while(rs.next()){%>
			<li><a href="<%=rs.getString("tabLink") %>"><%=rs.getString("tabName") %></a></li>
		<%}
		DbConnection.close();
    %>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>


</body>
</html>








<%--  <nav class="navbar navbar-expand-lg navbar-inverse">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" style="color:whitesmoke">Zoho</a>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                  
       <%
   		String level="";
		try {
			Cookie cw[] = request.getCookies();
			for(Cookie c : cw) {
				if(c.getName().equals("username")) {
					if(!c.getValue().toLowerCase().equals("admin")){					
						level = DbConnection.getLevelName(c.getValue());
					}
				}
			}
		} catch(Exception e) {
		}		
		ResultSet rs = DbConnection.getTabs(level);
		while(rs.next()){%>
			<li class="nav-item">
				<a class="nav-link" href="<%=rs.getString("tabLink") %>"><%=rs.getString("tabName") %></a>
			</li>
		<%}
		DbConnection.close();
    %>
      
    </ul>
   <!--  <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form> -->
    
    <ul class="nav navbar-nav navbar-right">
      <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
    
  </div>
</nav> --%>