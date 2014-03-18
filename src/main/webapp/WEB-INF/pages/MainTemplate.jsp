<%@ page import="com.springapp.domain_objects.Category" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  AuthUser: Shichirin
  Date: 03.02.14
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <style>
        body {
            width: 950px;
            margin: 0 auto;
        }

        #header {
            border: 2px solid;
            height: 150px;
            margin: 0 auto;
        }

        #menu {
            width: 240px;
            border: 2px solid;
        }

        #menu ul {
            list-style: none;
            padding: 0px;
        }

        #menu ul li a {
            padding-left: 5px;
            color: black;
            text-decoration: none;
        }

        #menu ul li:hover {
            background-color: #808080;
            color: #ffffff
        }
    </style>

</head>
<body>
<div id="header"></div>
<div id="content">
    <div id="menu">
        <ul id="categories">
            <%
                ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("cats");
                for (int i = 0; i < categories.size(); i++) {
            %>
            <li><a href="#"><%=categories.get(i).getName()%>
            </a></li>
            <%}%>
        </ul>
    </div>
    <form action="main" method="post">
        <button>post</button>
    </form>
</div>
<div id="footer"></div>
</body>
</html>