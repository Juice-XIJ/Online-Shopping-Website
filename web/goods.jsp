<%--
  Created by IntelliJ IDEA.
  User: XIJ
  Date: 4/7/2016
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=GBK" pageEncoding="utf-8" %>
<%
    /**
     *
     * map: email, username, admin, customer, seller
     */
    Map map = (Map) session.getAttribute("map");
    Map goodsInfo = (Map) session.getAttribute("goodsInfo");
    List<Map<String, Object>> map1 = (List<Map<String, Object>>) goodsInfo.get("path");
    List<Map<String, Object>> commentOfGoods = (List<Map<String, Object>>) session.getAttribute("commentOfGoods");
    int image = map1.size();
    System.out.println("goodsInfo: " + goodsInfo);
    System.out.println("map1: " + map1);
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
    <script charset="gb2312" language="javascript" type="text/javascript" src="<%=path%>/js/buy.js?ver=1"></script>

    <title>Items</title>

    <!-- Bootstrap core CSS -->
    <link type="text/css" rel="stylesheet" href="<%=path%>/css/bootstrap.min.css?ver=1">
    <script src="<%=path%>/js/jquery.min.js"></script>
    <script src="<%=path%>/js/bootstrap.min.js"></script>


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

<div class="container marketing" style="background-color:white;padding-top: 50px">

    <div class="row featurette">
        <div class="col-sm-7 col-md-push-6" style="padding-top: 58px">
            <%
                /**
                 * goodsInfo: s.nameOfShop, g.nameOfGoods, g.price, g.description, g.type,  i.path (List)
                 */
            %>
            <h1 style="margin-top: -16px"><%=goodsInfo.get("nameOfGoods")%>
            </h1>

            <div class="list-group">
                <ul class="list-group-item">
                    <p class="news-content">Type: <%=goodsInfo.get("type")%>
                    </p>

                    <p class="news-content">Price: <%=goodsInfo.get("price")%>
                    </p>

                    <p class="news-content">Rate: <%=goodsInfo.get("rates")%>/ 5.0
                    </p>

                    <p class="news-content">Shop: <%=goodsInfo.get("nameOfShop")%>
                    </p>

                    <p class="news-content">Description: <%=goodsInfo.get("description")%>
                    </p>

                    <form name="form1" class="navbar-form" style="padding-left: 0px">

                        <input name="amount" type="text" placeholder="Amount"
                               class="form-control" required>
                        <input name="customer_id" type="hidden" value="<%=map.get("id")%>">
                        <input name="goods_id" type="hidden" value="<%=goodsInfo.get("id")%>">
                        <input name="price" type="hidden" value="<%=goodsInfo.get("price")%>">

                    </form>

                    <a type="submit" class="btn btn-success"
                       onclick="amount()">Buy</a>


                </ul>
            </div>

            <%
                for (Map aMap : commentOfGoods) {
                    if (aMap.get("commentOfGoods") != "") {
            %>
            <div class="list-group">
                <ul class="list-group-item">
                    <p class="news-content">Comment: <%=aMap.get("commentOfGoods")%>
                    </p>
                </ul>
            </div>
            <%
                    }
                }
            %>

        </div>

        <div class="col-md-5 col-md-pull-8"  style="padding-top: 40px">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <%
                        for (int i = 1; i < image; i++) {
                    %>
                    <li data-target="#carousel-example-generic" data-slide-to="<%=i%>"></li>
                    <%
                        }
                    %>
                </ol>
                <div class="carousel-inner" align="center" >
                    <div class="item active">
                        <img src="<%=map1.get(0).get("path")%>" style="height: 300px">
                    </div>
                    <%
                        for (int i = 1; i < image; i++) {
                    %>
                    <div class="item">
                        <img src="<%=map1.get(i).get("path")%>" class="img-responsive" style="height: 300px">
                    </div>
                    <%
                        }
                    %>
                </div>
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>


