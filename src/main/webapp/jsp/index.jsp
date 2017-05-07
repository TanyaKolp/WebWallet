<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<html>
<head>
<title>Wallet</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>
<div align="center">
    <h1> Welcome to Wallet!</h1>

		<h2>
			    	<form action="logIn"  method="post">
                       Enter your login: <input type="text" name="userLogin"><br> <br>
                       Enter your password: <input type="text" name="userPassword"><br> <br>
                      <input type="submit" value="LogIn">
            </form><br> <br>
		</h2>
		<h2>${error}</h2>
		<h3>or <a href="signUp.html">Sign up</a> </h3>
</div>
    <div align="right">
        <h3><button><a href="users.html">All users</a></button></h3>
	</div>
</body>
</html>
