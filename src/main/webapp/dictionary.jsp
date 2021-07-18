<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dictionary</title>
<link rel="stylesheet" href="./styles/dictionary.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div class="app">
	<div class="title">
            <h1>Dictionary</h1>
    </div>
 	<div class="search">
		<s:form class="form" action="find-meaning">
			<s:textfield class="input" name="word" placeholder="Enter a word to search"/>
 			<s:submit class="btn" value="Search" />
		</s:form>
    </div>
</div>
<div class="dictionary">
<h1>
<s:property value="word"/>
</h1> <br />
<h3>
<s:property value="meaning"/>
</h3> <br />
</div>
</body>
</html>