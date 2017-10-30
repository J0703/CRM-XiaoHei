<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="refresh" content="5;url=pages/frame/right.jsp">

    <link href="../BS/css/bootstrap.min.css" rel="stylesheet">
    <script src="../BS/js/jquery-3.2.1.js"></script>
    <script src="../BS/js/bootstrap.min.js"></script>
    <title>Insert title here</title>
</head>
<body onload="showMun()">
<div class="jumbotron text-center">
    <h3>您可能没有修改的权限!</h3>
    <p id="time"></p>
    <p><a class="btn btn-primary btn-lg" href="pages/frame/right.jsp" role="button">跳转主页</a></p>
</div>
</body>
<script>

    var i = 6;

    function showMun() {

        i = i - 1;

        document.getElementById("time").innerHTML = "还有" + i + "秒跳转页面";

        setTimeout('showMun()', 1000);

    }

</script>
</html>