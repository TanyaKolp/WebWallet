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
    <h1> Profile</h1>
        <form action="editProfile" method="post" >
            Enter your name: <input type="text" name="name"><br><br>
            Enter your email: <input type="text" name="email"><br><br>
            Enter your birthday: <input type="date" name="birthday"><br>
            <input type="submit" value="Save">
        </form>
    <br> <br>
		<h2>${error}</h2>
</div>
</body>
</html>