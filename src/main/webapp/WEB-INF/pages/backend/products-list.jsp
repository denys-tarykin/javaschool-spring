<%@ page import="com.springapp.domain_objects.Product" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  AuthUser: Shichirin
  Date: 03.02.14
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="backend-header.jsp" %>
<div id="content">
    <div id="menu">
        <ul>
            <li><a href="/backend/category">Categories</a></li>
            <li><a href="/backend/products">Products</a></li>
        </ul>
    </div>
    <div class="inner-content">
        <div id="sub-menu"><a href="/backend/products/add">Add</a></div>

        <form method="post" name="Products-list" action="">
            <table>
                <thead>
                <tr>
                    <td style="width: 300px;">Name</td>
                    <td style="width: 200px">Action</td>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
                    if(products != null){
                    for (int i = 0; i < products.size(); i++) {
                %>

                <tr>
                    <td><%=products.get(i).getName()%>
                    </td>
                    <td><a href="/backend/products/edit/<%=products.get(i).getId()%>">Edit</a> <a
                            href="/backend/products/delete/<%=products.get(i).getId()%>">Delete</a> <a
                            href="">Up</a> <a href="">Down</a></td>
                </tr>
                <%}}%>
                </tbody>
            </table>
        </form>
        <ul id="pagination-flickr" style="margin-left: 20%;">
            <li class="previous-off">Previous</li>
            <li class="active">1</li>
            <li><a href="/page=2">2</a></li>
            <li><a href="?page=3">3</a></li>
            <li><a href="?page=4">4</a></li>
            <li><a href="?page=5">5</a></li>
            <li><a href="?page=6">6</a></li>
            <li><a href="?page=7">7</a></li>
            <li class="next"><a href="?page=2">Next</a></li>
        </ul>
    </div>
</div>
<div id="footer"></div>
</body>
</html>