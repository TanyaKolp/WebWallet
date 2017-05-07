<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <h1> Profile.</h1><br>
    Login: ${user.login} <br>
    Name: ${user.name}<br>
    Birthday: ${user.birthday}<br>
    Email: ${user.email}<br>
    <p><h3><button><a href="edit.html">Edit profile</a></button></p>
</div>