<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<html>
<head>
<title>Wallet</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>
<div class="w3-container w3-green" align = "center">
    <h1> Welcome to Wallet!</h1>
</div>
<div class="w3-display-middle">
		  	<form class="w3-container" action="logIn"  method="post">
                       Enter your login: <input class="w3-input w3-border" type="text" name="userLogin"><br> <br>
                       Enter your password: <input class="w3-input w3-border" type="text" name="userPassword"><br> <br>
                      <input class="w3-input w3-border" type="submit" value="LogIn">
            </form>
            <h3>or <a class="w3-button w3-medium w3-grey" href="signUp.html">Sign up</a></h3>
</div>
		<div class="w3-red"  align = "center">
		<h2>${error}</h2>
		</div>

    <div class="w3-display-bottomright">
        <a class="w3-button w3-grey" href="users.html">All users</a>
	</div>
</body>
</html>
