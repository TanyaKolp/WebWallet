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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type='text/javascript'>
        var counter = 1;
function addInput(){
counter++;
    var table = document.getElementById("costTable");
    var row = table.insertRow(-1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    cell1.innerHTML = "<select id='type"+counter+"' class='w3-select' name='sectionType'> <option value='Market'>Market</option> <option value='Utilities'>Utilities</option> <option value='Clothes'>Clothes</option></select>";
    cell2.innerHTML = "<input id='name"+counter+"' class='w3-input w3-border' type='text' name='name'>";
    cell3.innerHTML = "<input id='worth"+counter+"' class='w3-input w3-border' type='text' name='worth'>";

}
</script>
 <script>
$(document).ready(function(){
     $("#submit").click(function(){
      var costs = [];
		 for (i = 1; i <= counter; i++) {
				costs.push({servicesSection: {name: $("#type"+i).val()}, name: $("#name"+i).val(), worth: $("#worth"+i).val()});
			}
		var x = JSON.stringify(costs);
       $.ajax({ headers: {"Content-Type": "application/json"},
            		type: "POST",
                      url: "addCost",
                      dataType: 'json',
                      data: x,
                      success:  alert("Send " +x)
        })
     });
});
</script>
</head>
<body>

<div class="w3-container w3-green" align="center">
    <h1> Welcome, ${user.login}! </h1><br>
</div>

<div class="w3-row-padding">
    <div class="w3-display-topright">
        <a href="edit.html" class="w3-button  w3-grey">Edit profile</a>
    </div>
    <div class="w3-display-topleft">
        <a href="profile.html" class="w3-button w3-grey">View profile</a>
    </div>

    <div class="w3-container w3-half">
        <h2>Account information</h2>
        <div class="w3-container w3-half">
            <table class="w3-table w3-striped w3-border w3-bordered">
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
        </div>
    </div>
    <div class="w3-container w3-half">
        <h2>Add cost</h2>
        <div class="w3-container w3-half">
            <form class="w3-container" action="addCost" method="post">
                <table id="costTable" class="w3-striped">
                    <tr>
                        <th>Category</th>
                        <th>Name</th>
                        <th>Worth</th>
                    </tr>
                    <tr id="costs">
                        <td>
                            <select id="type1" class="w3-select" name="sectionType">
                                <option value="Market">Market</option>
                                <option value="Utilities">Utilities</option>
                                <option value="Clothes">Clothes</option>
                            </select></td>
                        <td><input id="name1" class="w3-input w3-border" type="text" name="name" required></td>
                        <td><input id="worth1" class="w3-input w3-border" type="text" name="worth" required></td>
                    </tr>
                </table>
                <input class="w3-button w3-grey" type="button" value="+" onClick="addInput();">
                <input id="submit" type="button" value="Save" class="w3-button w3-grey">
            </form>
        </div>
    </div>
</div>
<div class="w3-red" align="center">
    <h3>${error}</h3>
</div>
<div class="w3-display-bottomleft">
    <a href="costs.html" class="w3-button w3-grey">View costs</a>
    <a href="account.html" class="w3-button w3-grey">Edit account</a>
</div>
</body>
</html>
