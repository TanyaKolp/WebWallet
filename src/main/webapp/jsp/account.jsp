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
	<br>
	<div style="text-align:center">
		<h1>
			Your account!!<br> <br>
		</h1>
	</div>
	<div >
            <table border="1" cellpadding="5">
                <caption><h2>Account information</h2></caption>
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
<div >

            <table border="1" cellpadding="5">
                <caption><h2>Expenses account</h2></caption>
                <tr>
                    <th>Type</th>
                    <th>Sum</th>

                </tr>

                    <c:forEach var="sec" items="${sections}">
                                    <tr>
                                        <td><c:out value="${sec.name}" /></td>
                                        <td><c:out value="${sec.allWorth}" /></td>
                                    </tr>
                                </c:forEach>

            </table>
        </div>

</body>
</html>