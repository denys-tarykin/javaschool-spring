<%@ page import="com.springapp.domain_objects.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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
    <div class="inner-content">
        <form method="post" action="/backend/products/add">
            <% Map<String, String> errorList = (HashMap<String, String>) request.getAttribute("ErrorList");
                if (errorList == null) {
                    errorList = new HashMap<String, String>();
                }

                String name = (String) request.getAttribute("name");
                if (name == null) {
                    name = "";
                }

                String description = (String) request.getAttribute("description");
                if (description == null) {
                    description = "";
                }

                String price = (String) request.getAttribute("price");
                if (price == null) {
                    price = "";
                }

                HashSet<Integer> cats;
                cats = (HashSet<Integer>) request.getAttribute("categories");
                if (cats == null) {
                    cats = new HashSet<Integer>();
                }

                String tags = (String) request.getAttribute("tags");
                if (tags == null) {
                    tags = "";
                }

            %>

            <p style="width: 50px;"><label>Name</label>
                <input name="name" value="<%=name%>">
                <%if (errorList.containsKey("name")) {%>
                <label><%=errorList.get("name")%>
                </label><%}%>
            </p>

            <p style="width: 50px;">
                <label>Description</label>
                <textarea name="description"><%=description%>
                </textarea>
                <%if (errorList.containsKey("description")) {%>
                <label><%=errorList.get("description")%>
                </label><%}%>
            </p>

            <p style="width: 50px;">
                <label>Price:</label>
                <input name="price" value="<%=price%>">
                <%if (errorList.containsKey("price")) {%>
                <label><%=errorList.get("price")%>
                </label><%}%>
            </p>
            <p style="width: 50px;">
                <label>Categories:</label>
                <select multiple name="categories">
                <%
                        ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("cats");
                        Category cat = categories.get(1);
                        for (int i = 0; i < categories.size(); i++) {
                    %>
                    <option value="<%=categories.get(i).getId()%>" <%=cats.contains(categories.get(i).getId()) ? "selected" : ""%>><%=categories.get(i).getName()%>
                    </option>
                    <%}%>
                </select>
                <%if (errorList.containsKey("categories")) {%>
                <label><%=errorList.get("categories")%>
                </label><%}%>
            </p>
            <p>
                <label>Tags</label>
                <input name="tags" value="<%=tags%>">
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