<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/9/2016
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     * map: email, username, admin, customer, seller
     */
    String path = request.getContextPath();
    Map map = (Map) session.getAttribute("map");
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <script charset="gb2312" language="javascript" type="text/javascript" src="<%=path%>/js/add.js?ver=1"></script>


    <title>Add Item</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=path%>/css/bootstrap.min.css?ver=1" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=path%>/css/signup.css?ver=1" rel="stylesheet">


</head>
<body role="document">

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=path%>/servlet/NewItemAction">BuyBuyBuy</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="servlet/MyOrderAction">Hi, <%=map.get("email")%>
                </a></li>
                <li><a href="<%=path%>/signout.jsp">Sign out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <form name="form1" class="form-signup" method="post" enctype="multipart/form-data">
        <h2 class="form-signup-heading">ADD ITEM</h2>




        <label>
            <select name="option" id="option" class="form-control">
                <option value="none">Please select a type of the item!    </option>
                <option value="Books">Books</option>
                <option value="Electronics">Electronics & Computers</option>
                <option value="Sports">Sports</option>
                <option value="Home">Home</option>
            </select>
        </label>

        <label for="inputNameOfGoods" class="sr-only">Name of item</label>
        <input type="text" id="inputNameOfGoods" name="nameOfGoods" class="form-control" placeholder="Name Of Item"
               required autofocus>

        <label for="inputPrice" class="sr-only">Price</label>
        <input type="text" id="inputPrice" name="price" class="form-control" placeholder="Price" required autofocus>

        <label for="inputDescription" class="sr-only">Description</label>
        <input type="text" id="inputDescription" name="description" class="form-control" placeholder="inputDescription"
               required>

        <label for="upLoadImage" class="sr-only">Image</label>
        <input type="file" name="image" id="upLoadImage" style="margin-bottom: 10px;" accept="image/*"
               placeholder="Image" class="form-control" required>

        <button type="submit" class="btn btn-lg btn-primary btn-block" onclick="upload()">Add</button>
    </form>

</div>
</body>
</html>


