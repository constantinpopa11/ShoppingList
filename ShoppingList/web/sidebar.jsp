<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <script src="scripts/sidebarscript.js"></script>
 <link rel="stylesheet" href="css/sidebar.css">
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
        <p >Dummy Heading</p>
        <li class="active">
            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">My Lists</a>
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <li>
                    <a href="#">List 1</a>
                </li>
                <li>
                    <a href="#">List 2</a>
                </li>
                <li>
                    <a href="#">List 3</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">About</a>
            <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
            <ul class="collapse list-unstyled" id="pageSubmenu">
                <li>
                    <a href="detailedlist.jsp">List details</a>
                </li>
                <li>
                    <a href="login.jsp">Login </a>
                </li>
                <li>
                    <a href="signup.jsp">Signup</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">Portfolio</a>
        </li>
        <li>
            <a href="#">Contact</a>
        </li>
    </ul>

    <ul class="list-unstyled CTAs">
        <li>
            <a href="http://www.lemonparty.org" class="download">Don' click here plz</a>
        </li>
    </ul>
</nav>
