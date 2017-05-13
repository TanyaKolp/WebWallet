<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Wallet</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
    </style>
</head>
<body>
	<br>
<div class="w3-container w3-green" align = "center">
    <h1>Profile</h1>
</div>
<div class="w3-display-topleft w3-xlarge w3-text-green">
		<a href="welcome.html" class="fa fa-home"></a>
	</div>
<div class="w3-display-middle w3-xlarge">
    Login: ${user.login} <br>
    Name: ${user.name}<br>
    Birthday: ${user.birthday}<br>
    Email: ${user.email}<br>
    <a href="edit.html" class="w3-button w3-grey">Edit profile</a>
</div>
