<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Spring MVC Tutorial Series by Crunchify.com</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>ID</th>
                <th>login</th>
            </tr>
            <c:forEach var="user" items="${lists}">
                <tr>
                    <td><c:out value="${user.getId}" /></td>
                    <td><c:out value="${user.getLogin}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
	<br>
	<div style="text-align:center">
		<h2>
			All users- test<br> <br>
			<c:forEach var="user" items="${lists}">
                                <c:out value="${user}" /><br> <br>
                                <c:out value="${user}" /><br> <br>
             </c:forEach>
		</h2>
	</div>
</body>
</html>