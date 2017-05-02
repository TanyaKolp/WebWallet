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
    <h1> Welcome, ${userLogin}! </h1><br>
    <h2>Account information</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Balance</th>
            <th>Type</th>
        </tr>
        <tr>
            <td>${account.id}</td>
            <td>${account.balance}</td>
            <td>${account.type}</td>
        </tr>
    </table>
    <a href="costs.html">Costs</a>
    <a href="account.html">Edit account</a>
    <h3>${error}</h3>
</div>
</body>
</html>