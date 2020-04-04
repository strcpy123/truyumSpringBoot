<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" type="text/css" href="style/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Header Section Started -->
	<header class="header-style">
 	 <p>
		SpidyZone
	</p>  
  	</header>
<!-- Header Section Ended -->
		<!-- Menu Section Started-->
                <form method="post" onsubmit="showDate()">
                        <label for="live-date">Date</label>
                        <input type="date" name="date" id="live-date">
                        <input type="submit" value="Click to Proceed">
                        <input type="button" value="click" onclick="showDate()">
                </form>
            <p id="local"></p>
        <!-- Menu Section Ended-->
    <!-- JavaScript Popup Started -->
            <script>
                    function showDate(){
                        var date1 = document.getElementById("live-date").value;
                        //alert(date1);
                        document.getElementById("local").innerHTML=date1;
                    }
            </script>
     <!-- JavaScript Popup Started -->
			
<!-- Footer Section Started -->
	<footer class="footer">
  	<p>Copyright<img src="https://img.icons8.com/metro/26/000000/copyright.png" height=10px width=10px> 2019</p>
	</footer>
<!-- Footer Section Ended -->
</body>
</html>