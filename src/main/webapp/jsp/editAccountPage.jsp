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
	<div class="w3-container w3-green" align = "center">
		 <h1> Edit Account!</h1>
	</div>
<div class="w3-display-middle w3-text-black" align="center">

        <form class="w3-container" action="editAccount" method="post" >
            Account type: <select  class="w3-select" name="accountType">
                              <option value="Default">Default</option>
                              <option value="Checking">Checking</option>
                              <option value="Savings">Savings</option>
                              <option value="Money market">Money market</option>
                            </select>
                            <br>
            Account balance: <input class="w3-input w3-border"  type="text" name="accountBalance"><br>
            Payroll month: <select class="w3-select" name="month">
                              <option value="00">January</option>
                              <option value="01">February</option>
                              <option value="02">March</option>
                              <option value="03">April</option>
                              <option value="04">May</option>
                              <option value="05">June</option>
                              <option value="06">July</option>
                              <option value="07">August</option>
                              <option value="08">September</option>
                              <option value="09">October</option>
                              <option value="10">November</option>
                              <option value="11">December</option>
                           </select>
            Payroll day: <input class="w3-input w3-border"  type="number" name="day"  min="1" max="31"><br>
            <input class="w3-button w3-grey" type="submit" value="Save">
         </form>
</div>
<div class="w3-red"  align = "center">
		<h3>${error}</h3>
	</div>
</div>
</body>
</html>
