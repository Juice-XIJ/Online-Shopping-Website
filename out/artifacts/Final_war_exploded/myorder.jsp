<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/2/2016
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     * map: email, username, admin, customer, seller
     * mapOfUserOrder: select b.finish, g.id, g.nameOfGoods, g.price, s.nameOfShop, i.path
     */
    String path = request.getContextPath();
    List<Map<String, Object>> mapOfUserOrder = (List<Map<String, Object>>) session.getAttribute("mapOfUserOrder");
    Map map = (Map) session.getAttribute("map");

    System.out.println("map: " + map);
    System.out.println("mapOfUserOrder: " + mapOfUserOrder);
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

    <title>My Order</title>

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

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="servlet/MyOrderAction">Hi, <%=map.get("email")%>
                </a></li>
                <li><a href="<%=path%>/signout.jsp">Sign out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <form name="form3" class="nav-sidebar">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li>My order</li>
                    <li class="active"><a href="<%=path%>/myorder.jsp">All order</a></li>
                </ul>

                <ul class="nav nav-sidebar">
                    <li>My Shop</li>
                    <li><a href="servlet/ApplyShopAction">Shop Information</a></li>
                </ul>
                <%
                    if ((int) map.get("admin") == 1) {
                %>
                <ul class="nav nav-sidebar">
                    <li>My Administration</li>
                    <li><a href="servlet/AdminAction">My Administration</a></li>
                </ul>
                <%
                    }
                %>
            </div>
        </form>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">My Order</h1>

            <%
                if (mapOfUserOrder == null) {
            %>
            <div class="row placeholders">
                <div class="well">
                    <h1>You don't have any orders!!</h1>
                </div>
            </div>
            <%
            } else {
            %>

            <%
                for (Map<String, Object> aMap : mapOfUserOrder) {
            %>
            <div class="row placeholders">

                <div class="well row">
                    <div class="col-md-4">
                        <a href="servlet/ShowGoodsAction?good=<%=aMap.get("id").toString()%>"><img
                                src="<%=(aMap.get("path"))%>" class="img-thumbnail" alt="A"></a>
                    </div>

                    <div class="col-md-4" align="left">
                        <p>Items_id: <%=aMap.get("id")%>
                        </p>

                        <p>Items_type: <%=aMap.get("type")%>
                        </p>

                        <p>Name: <%=aMap.get("nameOfGoods")%>
                        </p>

                        <p>Amount: <%=aMap.get("amount")%>
                        </p>

                        <p>Date: <%=aMap.get("dealDate")%>
                        </p>

                        <p>Status: <%=aMap.get("finish")%>
                        </p>

                        <p>Price: <%=aMap.get("dealPrice")%>
                        </p>

                        <p>Rate: <%=aMap.get("rates")%>/ 5.0
                        </p>

                        <p>Shop: <%=aMap.get("nameOfShop")%>
                        </p>
                    </div>
                    <%
                        if (Objects.equals(aMap.get("finish").toString(), "pending")) {
                    %>
                    <div class="col-md-4" align="right">
                        <a type="submit" class="btn btn-success"
                           href="servlet/MyOrderAction?cancel=<%=aMap.get("id")%>&dealDate=<%=aMap.get("dealDate")%>">Cancel</a>
                    </div>
                    <%
                        }
                    %>

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
