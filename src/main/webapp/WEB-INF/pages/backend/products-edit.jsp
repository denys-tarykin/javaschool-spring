<%@ page import="com.springapp.domain_objects.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="com.springapp.domain_objects.Product" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>

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
                <input name="name" value="<%=product.getName()%>">
            </p>

            <p style="width: 50px;">
                <label>Description</label>
                <textarea name="description"><%=product.getDescription()%></textarea>
            </p>

            <p style="width: 50px;">
                <label>Price:</label>
                <input name="price" value="<%=product.getPrice()%>">
            </p>
            <p style="width: 50px;">
                <label>Categories:</label>
                <select multiple name="categories">
                        <%
                        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("cats");
                        Set<Category> cat_to_product = (Set<Category>)request.getAttribute("cats_to_product");
                        for (int i = 0; i < categories.size(); i++) {%>
                        <option value="<%=categories.get(i).getId()%>" <% if(cat_to_product.contains(categories.get(i))){%> selected<%}%>><%=categories.get(i).getName()%></option>
                        <%}%>
                </select>
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