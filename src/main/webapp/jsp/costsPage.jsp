<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<div class="w3-container w3-green" align = "center">
		<h1>Costs.</h1>
	</div>
	<div class="w3-display-topleft w3-xlarge w3-text-green">
		<a href="welcome.html" class="fa fa-home"></a>
	</div>
	<div class="w3-container w3-half">
            <table class="w3-table w3-striped w3-border w3-bordered">
                <h2>Account information</h2>
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
        </div>
	<div class="w3-container w3-half">
            <table class="w3-table w3-striped w3-border w3-bordered">
            <h2>Costs</h2>
                <tr>
                    <th>Type</th>
                    <th>Sum</th>
                </tr>
                    <c:forEach var="sum" items="${sums}">
                                    <tr>
                                        <td><c:out value="${sum.key}" /></td>
                                        <td><c:out value="${sum.value}" /></td>
                                    </tr>
                    </c:forEach>
            </table>
        </div>

</body>
</html>
