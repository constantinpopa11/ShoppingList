<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="scripts/sidebarscript.js"></script>
<script src="scripts/shopfinder-service.js"></script>
<link rel="stylesheet" href="css/sidebar.css">


<c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>
<c:set var = "privileges" scope="request" value = "${sessionScope.privileges}"/>
<c:set var = "firstName" scope="request" value = "${sessionScope.firstName}"/>

<!-- Sidebar Holder -->
<nav id="sidebar">
    <div class="sidebar-header">
        <p>
            <a href="home.jsp">
                <img   src="${initParam['WEBSERVER_LOCATION']}/images/app/logo_v2.svg" width="100%">
            </a>
        </p>
    </div>

    <ul class="list-unstyled components">
        <c:if test="${firstName == null}">
            <p class="text-center"><i class="fas fa-user-slash"></i> You are not logged in currently</p>
        </c:if>

        <c:if test="${firstName != null && privileges >= 0}">
            <p class="text-center"> <i class="fas fa-user"></i> You are logged in as ${firstName}</p>
        </c:if>

        <c:if test="${firstName != null && privileges == -1}">
            <p class="text-center"> <i class="fas fa-user"></i> You are logged in as ${firstName} (unverified)</p>
        </c:if>

        <hr>


        <c:if test="${privileges == 0}">
            <li>
                <a href="#adminSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Admin Panel</a>
                <ul class="collapse list-unstyled" id="adminSubmenu">
                    <li>
                        <a href="newshopcat.jsp">New Shop Category</a>
                    </li>
                    <li>
                        <a href="NewProdCat">New Product Category</a>
                    </li>
                </ul>
            </li>
        </c:if>

        <c:if test="${privileges == -2}">
            <li>
                <a href="#joinSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Join Us</a>
                <ul class="collapse list-unstyled" id="joinSubmenu">
                    <li>
                        <a href="signup.jsp">Sign Up</a>
                    </li>
                    <li>
                        <a href="login.jsp">Log In</a>
                    </li>
                </ul>
            </li>
        </c:if>

        <li>
            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">My Shopping Lists</a>
            <ul class="collapse list-unstyled" id="homeSubmenu">


                <c:forEach items="${shoppingLists}" var="sl">

                    <c:if test="${privileges >= 0}">
                        <li>
                            <a href="detailedlist.jsp?slid=${sl.slid}">${sl.slName}</a>
                        </li>
                    </c:if>

                    <c:if test="${privileges < 0}">
                        <li>
                            <a href="home.jsp?slid=${sl.slid}">${sl.slName}</a>
                        </li>
                    </c:if>

                </c:forEach>

                <c:if test="${shoppingLists == null}">
                    <li class="active">
                        <a href="NewShoppingList">No lists to display</a>
                    </li>
                </c:if>
            </ul>
        </li>

        <li>
            <a href="NewShoppingList">New Shopping List</a>
        </li>

        <c:if test="${privileges >=0 }">
            <li>
                <a href="NewProduct">New Product</a>
            </li>
        </c:if>

        <li>
            <a href="#" onclick="getUserCoordinates(false)">Nearby shops</a>
        </li>

        <c:if test="${privileges >=-1 }">
            <li>
                <a href="LogOut">Log Out</a>
            </li>
        </c:if>

    </ul>

    <!--
    <ul class="list-unstyled CTAs">
        <li>
            <a href="#" class="download">button</a>
        </li>
    </ul>
    -->
</nav>


<script>
    var newShopsOnly = false;
    function getUserCoordinates(option) {
        newShopsOnly = option;
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(findNearShops, gpsFail, {timeout: 3000});
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    function findNearShops(position) {
        $.ajax({
            url: "FindNearShops",
            type: "get", //send it through get method
            data: {
                userLong: position.coords.longitude,
                userLat: position.coords.latitude,
                newShopsOnly: newShopsOnly
            },
            success: function (response) {;
                if (newShopsOnly && response === "noNewShops") {
                    //do nothing
                } else if (newShopsOnly && response !== "noNewShops") {
                    $('#responseBody').empty();
                    $('#responseBody').append(response);
                    $('#nearShopsModal').modal("show")
                } else if (!newShopsOnly) {
                    $('#responseBody').empty();
                    $('#responseBody').append(response);
                    $('#nearShopsModal').modal("show");
                }


            },
            error: function (xhr) {
                //alert("There's been an error while searching for near shops");
            }
        });
    }

    function gpsFail(err) {
        /*
         if (err.code == 1) {
         alert("Error: Access is denied!");
         } else if (err.code == 2) {
         alert("Error: Position is unavailable!");
         }
         */
    }

    setInterval(getUserCoordinates, 1000 * 60 * 5, true);
</script>
