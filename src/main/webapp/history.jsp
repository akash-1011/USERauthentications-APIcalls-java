<%@page import="java.sql.Timestamp"%>
<%@page import="com.akash.struts.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="./styles/history.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="app">
<table class="table table-hover table-bordered table-responsive">
<thead>
<tr>
    <th scope="col">UserID</th>
    <th scope="col">Username</th>
    <th scope="col">Approval status</th>
    <th scope="col">Level</th>
    <th scope="col">Approval Time</th>
  </tr>
</thead>
<tbody>
<%
List l=(List)request.getAttribute("dispHistory");
if(l!=null && (l.size() != 0))
{
Iterator it=l.iterator();

while(it.hasNext())
{

UserBean b=(UserBean)it.next();
int tempUserId = b.getUserId();
String tempName = b.getUserName();
int approved = b.getApproved();
String tempApproved = "Approved";
String tempLevel = b.getlName();
Timestamp tempApproveTime = b.getApprovedTime();
%>
<tr>
<td><%= tempUserId %></td>
<td><%= tempName %></td>
<td><%= tempApproved %></td> 
<td><%= tempLevel %></td>
<td><%= tempApproveTime %></td>
</tr>

<%

}
} else {
%>
	<tr><td colspan="5">No Records Found</td></tr>
<%
}
%>
</tbody>
</table>
</div>	
</body>
</html>