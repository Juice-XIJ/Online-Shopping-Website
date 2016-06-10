<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/7/2016
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     * map: email, username, admin, customer, seller
     * goodsOfSeller: nameOfGoods, price, description, g.id, nameOfShop, i.path
     */
    String path = request.getContextPath();
    List<Map<String, Object>> goodsOfSeller = (List<Map<String, Object>>) session.getAttribute("goodsOfSeller");
    List<List> sellerMessage = (List<List>) session.getAttribute("sellerMessage");
    Map map = (Map) session.getAttribute("map");

    System.out.println("map: " + map);
    System.out.println("goodsOfSeller: " + goodsOfSeller);
    System.out.println("sellerMessage: " + sellerMessage);
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
                    <li><a href="<%=path%>/myorder.jsp">All order</a></li>
                </ul>

                <ul class="nav nav-sidebar">
                    <li>My Shop</li>
                    <li><a href="<%=path%>/mygoods.jsp">Shop Information</a></li>
                    <li class="active"><a href="<%=path%>/message.jsp">Message</a></li>
                </ul>

                <%
                    if ((int)map.get("admin")==1){
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
                if (goodsOfSeller == null) {
            %>
            <h1 class="page-header">My Shop</h1>

            <div class="row placeholders">
                <div class="well">
                    <h1>You don't have any Items now!! </h1>
                </div>
            </div>
            <%
            } else {
            %>
            <h1 class="page-header"><%=goodsOfSeller.get(0).get("nameOfShop")%>
            </h1>

            <%
                int amount = 0;
                int i = 0;
                for (List<Map<String, Object>> aList : sellerMessage) {
                    if (aList != null) {
                        amount++;
                        for (Map aMap : aList) {%>
            <div class="row placeholders">

                <div class="well row">
                    <div class="col-md-4">
                        <a href="servlet/ShowGoodsAction?good=<%=goodsOfSeller.get(i).get("id")%>"><img
                                src="<%=(goodsOfSeller.get(i).get("path"))%>" class="img-thumbnail" alt="A"></a>
                    </div>


                    <div class="col-md-4" align="left">
                        <p>Item_id: <%=goodsOfSeller.get(i).get("id")%>
                        </p>

                        <p>Item_type: <%=goodsOfSeller.get(i).get("type")%>
                        </p>

                        <p>Price: <%=goodsOfSeller.get(i).get("price")%>
                        </p>

                        <p>Rate: <%=goodsOfSeller.get(i).get("rates")%>/ 5.0
                        </p>

                        <p>nameOfItems: <%=goodsOfSeller.get(i).get("nameOfGoods")%>
                        </p>

                        <p>description: <%=goodsOfSeller.get(i).get("description")%>
                        </p>

                        <p>Status: <%=aMap.get("finish")%>
                        </p>

                        <p>dealPrice: <%=aMap.get("dealPrice")%>
                        </p>

                        <p>dealDate: <%=aMap.get("dealDate")%>
                        </p>

                        <p>username: <%=aMap.get("username")%>
                        </p>

                        <p>commentOfItems: <%=aMap.get("commentOfGoods")%>
                        </p>
                    </div>

                    <%
                        if (Objects.equals(aMap.get("finish").toString(), "pending")) {
                    %>
                    <div class="col-md-4" align="right">
                        <a type="submit" class="btn btn-success"
                           href="servlet/SellerMessageAction?status=cancel&username=<%=aMap.get("username")%>&good_id=<%=goodsOfSeller.get(i).get("id")%>&dealDate=<%=aMap.get("dealDate")%>">Cancel</a>
                    </div>

                    <div style="padding-top: 10px" class="col-md-4" align="right">
                        <a type="submit" class="btn btn-success"
                           href="servlet/SellerMessageAction?status=delivering&username=<%=aMap.get("username")%>&good_id=<%=goodsOfSeller.get(i).get("id")%>&dealDate=<%=aMap.get("dealDate")%>">Delivering</a>
                    </div>
                    <%
                        }else if (Objects.equals(aMap.get("finish").toString(), "delivering")){
                    %>
                    <div class="col-md-4" align="right">
                        <a type="submit" class="btn btn-success"
                           href="servlet/SellerMessageAction?status=Delivered&username=<%=aMap.get("username")%>&good_id=<%=goodsOfSeller.get(i).get("id")%>&dealDate=<%=aMap.get("dealDate")%>">Delivered</a>
                    </div>
                    <%
                        }
                    %>
                </div>

            </div>
            <%
                    }
                }
            %>
            <%
                    i++;
                }
                if (amount == 0) {
            %>
            <h1 class="page-header"><%=goodsOfSeller.get(0).get("nameOfShop")%>
            </h1>

            <div class="row placeholders">
                <div class="well">
                    <h1>You don't have any messages!! </h1>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>

