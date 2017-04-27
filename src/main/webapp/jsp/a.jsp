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
	<br>
	<div style="text-align:center">
		<h1>
			Welcome to Wallet!!<br> <br>
		</h1>
<h2>Show your account</h2>
 <form:form method = "POST" action="a">
    <p><b>Enter your name</b><br/>
        <form:input path = "userName" />
    </p>
    <p><input type="submit" value="Show account"/>
        <input type="reset" value="Clear"/></p>
 </form:form>
		<h2>
        			<a href="welcome.html">Click here</a>
        		</h2>
        <h3>
        <button><a href="users.html">All users</a></button>

        </h3>
	</div>
</body>
</html>