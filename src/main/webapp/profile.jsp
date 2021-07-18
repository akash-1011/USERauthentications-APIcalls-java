<%@page import="java.sql.Timestamp"%>
<%@page import="com.akash.struts.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" href="./styles/profile.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="app">
<div class="card">
  <img src="https://picsum.photos/id/1005/200/150" alt="John" style="width:100%">
<%
List l=(List)request.getAttribute("dispProfile");
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
if(b.getApproved() == 0){
	tempApproved = "Not yet";
}
String tempLevel = b.getlName();
Timestamp tempApproveTime = b.getApprovedTime();
Timestamp tempRegisterTime = b.getRegisterTime();
%>

  <h1><%=tempName %></h1>
  <p class="title"><%=tempLevel %></p>
  <p><%=tempApproved %></p>
  <p>Approval time: <%=tempApproveTime %></p>
  <p>Register time: <%=tempRegisterTime %></p>

<%

}
} else {
%>
	<h3>Welcome Admin</h3>
<%
}
%>

</div>
</div>
</body>
</html>