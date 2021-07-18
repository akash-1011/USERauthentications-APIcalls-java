<%@taglib uri="/struts-tags" prefix="s" %>
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Login</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="./styles/login.css">
 </head>

  <body class="text-center">
    <form class="form-signin" action="login.action">
      <h1 class="h3 mb-3 font-weight-normal">Login</h1>
      <input type="text" name="username" id="inputEmail" class="form-control" placeholder="username">
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password">
      <a href="register.jsp">Register new user</a>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>

</body>
</html>