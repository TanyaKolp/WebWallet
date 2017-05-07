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
        <form action="editAccount" method="post" >
            Account type: <select name="accountType">
                              <option value="Default">Default</option>
                              <option value="Checking">Checking</option>
                              <option value="Savings">Savings</option>
                              <option value="Money market">Money market</option>
                            </select>
                            <br><br>
            Account balance: <input type="text" name="accountBalance"><br><br>
            Payroll date: <input type="date" name="payrollDate"><br><br>
            <input type="submit" value="Save">
         </form>
    <br> <br>
		<h2>${error}</h2>
</div>
</body>
</html>