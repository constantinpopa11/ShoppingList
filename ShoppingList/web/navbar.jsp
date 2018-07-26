<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link rel="stylesheet" href="css/navbar.css">

<nav class="navbar navbar-expand navbar-light bg-light">

    <button type="button" id="sidebarCollapse" class="navbar-btn">
        <span></span>
        <span></span>
        <span></span>
    </button>
    <div class="navbar-collapse justify-content-end collapse">

        <ul class="navbar-nav pt-1">
            <c:set var = "user" scope = "page" value = "${sessionScope.user}"/>

            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="LogOut"><i class="fas fa-sign-out-alt"></i> Log Out</a>
                </li>
            </c:if>

            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><i class="fas fa-sign-in-alt"></i> Log In</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="signup.jsp"><i class="fas fa-user-edit"></i> Sign Up</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>
