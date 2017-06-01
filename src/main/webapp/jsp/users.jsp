<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Wallet</title>
<style type="text/css">
body {
	background-image: url('http://crunchify.com/bg.png');
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
$(document).ready(function(){
     $("#submit").click(function(){
		var x = JSON.stringify([
        		{login: $("#login1").val(), password: $("#pas1").val()},
        		{login: $("#login2").val(), password: $("#pas2").val()}
        		])
                    $.ajax({ headers: {"Content-Type": "application/json"},
            		type: "POST",
                      url: "test",
                      dataType: 'json',
                      data: x,
                      success: alert( "Data Loaded: " + x)
        })
     });
});
</script>
</head>
<body>
<div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
            </tr>
            <c:forEach var="user" items="${lists}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.login}" /></td>
                    <td><c:out value="${user.password}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
	<br>
	<form class="w3-container" action="test"  method="post">
                            Enter your login: <input id="login1"  class="w3-input w3-border" type="text" name="login"><br> <br>
                                                      Enter your password: <input id="pas1"  class="w3-input w3-border" type="text" name="password"><br> <br>
                                                       Enter your login: <input id="login2"  class="w3-input w3-border" type="text" name="login"><br> <br>
                                                      Enter your password: <input id="pas2"  class="w3-input w3-border" type="text" name="password"><br> <br>
                                                      <input id="submit" class="w3-input w3-border" type="button" value="Sing Up">
                </form>
</body>
</html>