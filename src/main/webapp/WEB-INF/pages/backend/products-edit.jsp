<%@ page import="com.springapp.domain_objects.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="com.springapp.domain_objects.Product" %>

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
    <%
        Product product = (Product) request.getAttribute("product");
    %>
    <div class="inner-content">
        <form method="post" action="/backend/products/edit/<%=product.getId()%>">

            <p style="width: 50px;"><label>Name</label>
                <input name="name">
            </p>

            <p style="width: 50px;">
                <label>Description</label>
                <textarea name="description"></textarea>
            </p>

            <p style="width: 50px;">
                <label>Price:</label>
                <input name="price">
            </p>
            <p style="width: 50px;">
                <label>Categories:</label>
               <%=request.getAttribute("check")%>
            </p>

            <p>
                <button>Save</button>
            </p>
        </form>
    </div>
</div>
<div id="footer"></div>
</body>
</html>