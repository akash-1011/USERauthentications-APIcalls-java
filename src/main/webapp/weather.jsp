<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Weather</title>
<link rel="stylesheet" href="./styles/weather.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
	<div class="app">
	<div class="title">
            <h1>Weather</h1>
    </div>
 	<div class="search">
		<s:form class="form" action="find-weather">
			<s:textfield class="input" name="city" placeholder="Enter a city to search"/>
 			<s:submit class="btn" value="Search" />
		</s:form>
    </div>
</div>
<div class="weather">
<h1>
<s:property value="city"/>
</h1> <br />
<h3>
<s:property value="temp"/>
</h3> <br />
<h3>
<s:property value="description"/>
</h3>
</div>
</body>
</html>