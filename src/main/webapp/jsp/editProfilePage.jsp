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
	<br>
	<div class="w3-display-topleft w3-xlarge w3-text-green">
		<a href="welcome.html" class="fa fa-home"></a>
	</div>
<div class="w3-container w3-green"  align="center">
    <h1>Edit Profile</h1>
    <div class="w3-display-middle w3-text-black" align="center">

        <form class="w3-container" action="editProfile" method="post" >
            Enter your name: <input class="w3-input w3-border" type="text" name="nameU"><br><br>
            Enter your email: <input class="w3-input w3-border" type="text" name="emailU"><br><br>
            Enter your birthday: <input class="w3-input w3-border" type="date" name="bdU"><br>
            <input  class="w3-button w3-grey" type="submit" value="Save">
        </form>
    <br> <br>
		<h2>${error}</h2>
</div>
</body>
</html>
