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
                <c:choose>
                    <c:when test="${account == null}">
                        You have no account. Please, fill that form to set account.
                        <form action="addAccount"  method="post">
                                               Enter account type: <input type="text" name="accountType">
                                               Enter account balance: <input type="text" name="accountBalance">
                                              <input type="submit" value="Set account">
                        </form><br> <br>
                    </c:when>
                    <c:otherwise>
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
                              <a href="accountInfo.html">info</a>
                    </c:otherwise>
                </c:choose>
        <h3>${error}</h3>


    </div>
</body>
</html>