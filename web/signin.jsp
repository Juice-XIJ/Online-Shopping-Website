<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 3/31/2016
  Time: 5:10 PM
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
  <script charset="gb2312" language="javascript"  type="text/javascript" src="<%=path%>/js/signin.js"></script>

  <title>Sign in</title>

  <!-- Bootstrap core CSS -->
  <link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<%=path%>/css/signup.css" rel="stylesheet">
</head>

<body>

<div class="container">

  <form name="form2" class="form-signup">
    <h2 class="form-signup-heading">Please sign in</h2>

    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="login()">Sign in</button>
    <a type="submit" class="btn btn-lg btn-primary btn-block" href="<%=path%>/signup.jsp">Sign up</a>
  </form>

</div>

</body>
</html>
