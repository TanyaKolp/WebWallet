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
    <h1> Welcome, ${user.login}! </h1><br>
</div>
<div align="right">
    <h3>
        <button><a href="edit.html">Edit profile</a></button>
</div>
<div align="left">
    <button><a href="profile.html">View profile</a></button>
    </h3>
</div>
<h2>Account information</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>Type</th>
        <th>Balance</th>
        <th>Payroll date</th>
    </tr>
    <tr>
        <td>${account.type}</td>
        <td>${account.balance}</td>
        <td>${account.payrollDate}</td>
    </tr>
</table>
<h2>Add cost</h2>
<form action="addCost" method="post">
    <table border="1" cellpadding="5">
        <tr>
            <th>Category</th>
            <th>Name</th>
            <th>Worth</th>
        </tr>
        <tr>
            <td><select name="sectionType">
                <option value="Market">Market</option>
                <option value="Utilities">Utilities</option>
                <option value="Clothes">Clothes</option>
            </select></td>
            <td><input type="text" name="name"></td>
            <td><input type="text" name="worth"></td>
        </tr>
    </table>
    <br>
        <input type="submit" value="Save">
</form>
<h3>${error}</h3>
<div align="center">
    <a href="costs.html">View costs</a>
    <a href="account.html">Edit account</a>
</div>
</div>
</body>
</html>