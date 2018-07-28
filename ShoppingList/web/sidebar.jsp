<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="scripts/sidebarscript.js"></script>
<link rel="stylesheet" href="css/sidebar.css">


<c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>
<c:set var = "privileges" scope="request" value = "${sessionScope.privileges}"/>
<c:set var = "firstName" scope="request" value = "${sessionScope.firstName}"/>

<!-- Sidebar Holder -->
<nav id="sidebar">
    <div class="sidebar-header">
        <p>
            <a href="home.jsp">
                <img   src="./images/app/sl_logo_pro.svg" width="80%">
            </a>
        </p>
    </div>

    <ul class="list-unstyled components">
        <c:if test="${privileges == -3}">
            <p>You are not logged in currently</p>
        </c:if>

        <c:if test="${privileges != -3}">
            <p>Hi, </p>
        </c:if>

        <li>
            <a href="#joinusSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Join Us</a>
            <ul class="collapse list-unstyled" id="joinusSubmenu">
                <li>
                    <a href="login.jsp">Log In</a>
                </li>
                <li>
                    <a href="signup.jsp">Sign Up</a>
                </li>
                <li>
                    <a href="signup.jsp">Log Out</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">My Lists</a>
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>


                <c:forEach items="${shoppingLists}" var="sl">
                    <li>
                        <a href="detailedlist.jsp?slid=${sl.slid}">${sl.slName}</a>
                    </li>
                </c:forEach>

                <c:if test="${shoppingLists == null}">
                    <li class="active">
                        <a href="newlist.jsp">No lists to display</a>
                    </li>
                </c:if>
            </ul>
        </li>

        <li>
            <a href="#">New Shopping List</a>
        </li>

        <li>
            <a href="NewProduct">New Product</a>
        </li>

        <li>
            <a href="#adminSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Admin Panel</a>
            <ul class="collapse list-unstyled" id="adminSubmenu">
                <li>
                    <a href="#">New Shop Category</a>
                </li>
                <li>
                    <a href="#">New Product Category</a>
                </li>
            </ul>
        </li>



    </ul>

    <ul class="list-unstyled CTAs">
        <li>
            <a href="https://www.pornhub.com/view_video.php?viewkey=ph5af5c6e4ab31c&t=873" class="download">Don' click here plz</a>
        </li>
    </ul>
</nav>
