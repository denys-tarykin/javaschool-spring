<%@ page import="com.springapp.domain_objects.Category" %>

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
        <%
            Category category = (Category) request.getAttribute("cat");
        %>
        <form method="post" action="/backend/category/edit/<%=category.getId()%>">
            <p style="width: 50px;"><label>Name</label>
                <input name="name" value="<%=category.getName()%>"></p>

            <p style="width: 50px;"><label>Description</label>
                <input name="description" value="<%=category.getDescription()%>"></p>

            <p>
                <button>Save</button>
            </p>
        </form>
    </div>
</div>
<div id="footer"></div>
</body>
</html>