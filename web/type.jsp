<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/10/2016
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     * map: email, username, admin, customer, seller
     */
    /**
     * allItemOfType: g.id, g.nameOfGoods, g.seller, g.price, g.type, g.description, i.path
     */
    List<Map<String, Object>> allItemOfType = (List<Map<String, Object>>) session.getAttribute("allItemOfType");
    Object type = session.getAttribute("type");
    Map map = (Map) session.getAttribute("map");

    System.out.println("map: " + map);
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
    <link rel="icon" href="../../favicon.ico">
    <script charset="gb2312" language="javascript" type="text/javascript" src="<%=path%>/js/signin.js?ver=1"></script>
    <script charset="gb2312" language="javascript" type="text/javascript" src="<%=path%>/js/myorder.js?ver=1"></script>

    <title><%=type%>
    </title>

    <!-- Bootstrap core CSS -->
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.min.css?ver=1">
    <!-- Custom styles for this template -->
    <link href="<%=path%>/css/dashboard.css?ver=1" rel="stylesheet">


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
        <% if (map == null) {%>
        <div id="navbar" class="navbar-collapse collapse">
            <form name="form2" class="navbar-form navbar-right">

                <div class="form-group">
                    <input name="email" type="text" placeholder="Email" class="form-control">
                </div>
                <div class="form-group">
                    <input name="password" type="password" placeholder="Password" class="form-control">
                </div>
                <a type="submit" class="btn btn-success" onclick="login()">Sign in</a>
                <a type="submit" class="btn btn-success" href="<%=path%>/signup.jsp">Sign up</a>
            </form>
        </div>
        <% } else { %>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="servlet/MyOrderAction">Hi, <%=map.get("email")%>
                </a></li>
                <li><a href="<%=path%>/signout.jsp">Sign out</a></li>
            </ul>
        </div>

        <% } %>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <form name="form3" class="nav-sidebar">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li class="active"><a href="servlet/NewItemAction">What's new <span
                            class="sr-only">(current)</span></a></li>
                    <li><a href="servlet/ShowTypeAction?type=Books">Books</a></li>
                    <li><a href="servlet/ShowTypeAction?type=Electronics">Electronics & Computers</a></li>
                    <li><a href="servlet/ShowTypeAction?type=Sports">Sports</a></li>
                    <li><a href="servlet/ShowTypeAction?type=Home">Home</a></li>
                </ul>
            </div>
        </form>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <h1 class="page-header"><%=type%>
            </h1>

            <div class="row placeholders">
                <%
                    if (allItemOfType == null || allItemOfType.isEmpty()) {
                %>
                <div class="row placeholders">
                    <div class="well">
                        <h1>No item in this type!! </h1>
                    </div>
                </div>
                <%
                } else {
                    for (Map aMap : allItemOfType) {
                        /**
                         * allItemOfType: g.id, g.nameOfGoods, g.seller, g.price, g.type, g.description, i.path
                         */
                %>

                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="servlet/ShowGoodsAction?good=<%=aMap.get("id")%>"><img src="<%=aMap.get("path")%>"
                                                                                    class="img-responsive" alt="iamge" style="height: 200px"></a>
                    <h4><%=aMap.get("nameOfGoods")%>
                    </h4>
                    <span class="text-muted">Price: <%=aMap.get("price")%></span>
                    <span class="text-muted">Rate: <%=aMap.get("rates")%>/ 5.0</span>
                </div>

                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
</body>
</html>