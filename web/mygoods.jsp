<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/7/2016
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     * map: email, username, admin, customer, seller
     * mapOfUserOrder: b.finish, g.id, g.price, s.nameOfShop, i.path
     * goodsOfSeller: nameOfGoods, price, description, g.id, nameOfShop, g.type, i.path
     */
    String path = request.getContextPath();
    List<Map<String, Object>> goodsOfSeller = (List<Map<String, Object>>) session.getAttribute("goodsOfSeller");
    Map map = (Map) session.getAttribute("map");

    System.out.println("map: " + map);
    System.out.println("goodsOfSeller: " + goodsOfSeller);
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
    <script charset="gb2312" language="javascript" type="text/javascript"
            src="<%=path%>/js/applyshop.js?ver=1"></script>

    <title>My Items</title>

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
                    <li><a href="<%=path%>/myorder.jsp">All order</a></li>
                </ul>

                <ul class="nav nav-sidebar">
                    <li>My Shop</li>
                    <li class="active"><a href="<%=path%>/mygoods.jsp">Shop Information</a></li>
                    <%
                        if ((int) map.get("seller") == 1) {
                    %>
                    <li><a href="servlet/SellerMessageAction">Message</a></li>
                    <%
                        }
                    %>
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

            <%
                if ((int) map.get("seller") == -1) {
            %>

            <h1 class="page-header">My Shop</h1>

            <div class="row placeholders">
                <div class="well">
                    <h1>You don't have a shop!! </h1>

                    <form name="form1" class="navbar-form">
                        <div class="form-group">
                            <input name="shopname" type="text" placeholder="Please enter your shop name"
                                   class="form-control">
                        </div>
                        <a type="submit" class="btn btn-success" onclick="applyshop()">Apply now!</a>
                    </form>
                </div>
            </div>
            <%
            } else if ((int) map.get("seller") == 0) {
            %>
            <h1 class="page-header">My Shop</h1>

            <div class="row placeholders">
                <div class="well">
                    <h1>Your application is under review! </h1>
                </div>
            </div>
            <%
            } else {
            %>

            <%
                if (goodsOfSeller == null) {
            %>
            <h1 class="page-header">My Shop<a type="submit" class="btn btn-success" style="float:right"
                                              href="<%=path%>/additem.jsp"><span>Add item</span></a></h1>

            <div class="row placeholders">
                <div class="well">
                    <h1>You don't have any Items!!</h1>
                </div>
            </div>
            <%
            } else {
            %>
            <h1 class="page-header"><%=goodsOfSeller.get(0).get("nameOfShop")%><a type="submit" class="btn btn-success"
                                                                                  style="float:right"
                                                                                  href="<%=path%>/additem.jsp"><span>Add Item</span></a>
            </h1>
            <%
                for (Map<String, Object> aMap : goodsOfSeller) {
            %>

            <div class="row placeholders">

                <div class="well row">
                    <div class="col-sm-4">
                        <a href="servlet/ShowGoodsAction?good=<%=aMap.get("id").toString()%>"><img
                                src="<%=path%>/<%=(aMap.get("path"))%>" class="img-thumbnail" alt="A"></a>
                    </div>

                    <%
                        /**
                         * goodsOfSeller: nameOfGoods, price, description, g.id, nameOfShop, i.path
                         */
                    %>

                    <div class="col-sm-8" align="left">
                        <p>Item_id: <%=aMap.get("id")%>
                        </p>

                        <p>Item_type: <%=aMap.get("type")%>
                        </p>

                        <p>Price: <%=aMap.get("price")%>
                        </p>

                        <p>Rate: <%=aMap.get("rates")%>/ 5.0
                        </p>

                        <p>nameOfItems: <%=aMap.get("nameOfGoods")%>
                        </p>

                        <p>description: <%=aMap.get("description")%>
                        </p>
                    </div>

                </div>

            </div>
            <%
                        }
                    }
                }
            %>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
</body>
</html>
