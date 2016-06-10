<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 3/30/2016
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="favicon.ico">
  <script charset="gb2312" language="javascript"  type="text/javascript" src="<%=path%>/js/signup.js"></script>


  <title>Sign  up</title>

  <!-- Bootstrap core CSS -->
  <link href="<%=path%>/css/bootstrap.min.css?ver=1" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<%=path%>/css/signup.css?ver=1" rel="stylesheet">
</head>

<body>

<div class="container">

  <form name="form1" class="form-signup">
    <h2 class="form-signup-heading">Please sign up</h2>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>

    <label for="inputUserName" class="sr-only">Username</label>
    <input type="username" id="inputUserName" name="username" class="form-control" placeholder="Username" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required autofocus>

    <label for="inputPasswordAgain" class="sr-only">Password Again</label>
    <input type="password" id="inputPasswordAgain" name="passwordAgain" class="form-control" placeholder="Password Again" required autofocus>

    <label for="street" class="sr-only">street</label>
    <input type="text" id="street" name="street" class="form-control" placeholder="street" required autofocus>

    <label for="city" class="sr-only">city</label>
    <input type="text" id="city" name="city" class="form-control" placeholder="city" required autofocus>

    <label for="state" class="sr-only">state</label>
    <input type="text" id="state" name="state" class="form-control" placeholder="state" required autofocus>

    <label for="zipcode" class="sr-only">zipcode</label>
    <input type="text" id="zipcode" name="zipcode" class="form-control" placeholder="ZipCode" required autofocus>

    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="dosubmit()">Sign up</button>
    <a type="submit" class="btn btn-lg btn-primary btn-block" href="<%=path%>/signin.jsp">Sign in</a>
  </form>

</div>

</body>
</html>

<script>
  var errori ='<%=request.getParameter("error")%>';
  if(errori=='yes'){
    alert("Email address already in use. Please try again!");
  }
</script>
