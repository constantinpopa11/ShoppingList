<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="css/shoppinglist.css">
<link rel="stylesheet" href="css/searchmode.css">

<div id="accordion">
    <c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>
    <c:set var = "prodCategories" scope="request" value = "${requestScope.prodCategories}"/>
    <c:set var = "activeSL" scope="request" value = "${sessionScope.activeSL}"/>

    <c:set var = "qlcid" scope="session" value = "${sessionScope.qlcid}"/>
    <c:set var = "qprodCat" scope="session" value = "${sessionScope.qprodCat}"/>
    <c:set var = "qsortBy" scope="session" value = "${sessionScope.qsortBy}"/>
    <c:set var = "qkey" scope="session" value = "${sessionScope.qkey}"/>
    <c:set var = "qpage" scope="session" value = "${sessionScope.qpage}"/>

    <!--  contenitore  roba comune STUFF -->
    <div class="card">
        <div class="card-header list-header" id="listHeader">


            <div class="row custom-row">
                <div class="slTitle my-auto" >
                    <a class="btn-md btn-secondary  sl-dropdown" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown">
                        <c:if test="${activeSL != null}">
                            ${activeSL.slName} <i class="fas fa-chevron-down"></i>
                        </c:if>
                        <c:if test="${activeSL == null}">
                            No lists to display <i class="fas fa-chevron-down"></i>
                        </c:if>
                    </a>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="sl-dd-item dropdown-item" href="NewShoppingList">New list <i class="fas fa-plus-circle"></i></a>

                        <c:forEach items="${shoppingLists}" var="sl">
                            <hr class="nomargin">
                            <a class="sl-dd-item dropdown-item" href="${pageContext.request.requestURI}?slid=${sl.slid}">${sl.slName}</a>
                        </c:forEach>

                    </div>

                </div>

                <div class="col" data-toggle="collapse" data-target="#listDetails">
                    <!-- empty on purpose -->
                </div>

                <div class="slTitle float-right my-auto" >
                    <a class="float-right" href="home.jsp" ><i class="fas fa-arrow-circle-left list-action-ic"></i></a>

                    <span class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="SearchProducts?sortBy=0">By product category</a>
                            <a class="dropdown-item" href="SearchProducts?sortBy=1">By product name</a>
                        </div>
                    </span>

                </div>
            </div>

            <div class="row custom-row">
                <div class="slTitle my-auto" id="filterdiv" >
                    <div class="dropdown">
                        <button onclick="myFunction()" class="dropbtn">Category <i class="fas fa-filter list-action-ic"></i> </button>
                        <div id="myDropdown" class="dropdown-content">
                            <input type="text" placeholder="Search.." id="myInput" onkeyup="filterFunction()">


                            <c:forEach items="${prodCategories}" var="prodCat">
                                <a href="SearchProducts?prodCat=${prodCat.pcid}">
                                    ${prodCat.prodCatName}
                                </a>
                            </c:forEach>

                        </div>
                    </div>
                </div>

                <div class="col" data-toggle="collapse" data-target="#listDetails">
                </div>
                <!--  search bar -->
                <div class="slTitle float-left my-auto" id="search-barid">
                    <div class="col-xs-8 " id="searchbar">
                        <div id="custom-search">
                            <form action="SearchProducts" method="GET">
                                <input type="text" name="key" class="search-query" placeholder="Search" autocomplete="off"/>
                                <button type="button" >
                                    <i class="fas fa-search list-action-ic"></i>
                                </button>
                                <input type="submit" value="Submit" style="display: none;">
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <div id="listDetails" class="collapse"  data-parent="#accordion">
            <div class="card-body custom-row">
                <c:if test="${activeSL != null}">
                    <!--TODO-->
                    <div>
                        <img width="30" src="${initParam['WEBSERVER_LOCATION']}${activeSL.slIconPath}"/>
                        ${activeSL.slDescr}
                    </div>
                    <hr>
                    <div>
                        Shop category description <br>
                        <img width="30" src="${initParam['WEBSERVER_LOCATION']}${activeSL.slCatIconPath}"/>
                        ${activeSL.slCatName} <br>
                        ${activeSL.slCatDescr}
                    </div>


                </c:if>
            </div>
        </div>
    </div>




    <!--  first elemnt  -->

    <c:forEach items="${slItems}" var="item">
        <div class="card">
            <div class="card-header list-item ">

                <div class="row custom-row">
                    <!--  banana pic -->
                    <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#pid${item.pid}">
                        <img src="http://smartyessay.com/wp-content/uploads/2018/05/crazyshit-beef-curtains-crazy-shit-beef-curtain-pussy-house-interiors.jpg"  width="40" height="40"/>
                    </div>

                    <div class="col  my-auto">
                        <div class="row">
                            <div class="col itemTitle" data-toggle="collapse" data-target="#pid${item.pid}">
                                item di prova
                            </div>
                        </div>

                        <div class="row " >
                            <div class="col itemInfo" data-toggle="collapse" data-target="#pid${item.pid}">
                                cagtararara
                            </div>

                        </div>
                    </div>



                    <div class="col-xs-3 my-auto">
                        <div class="row">
                            <div class="col my-auto">
                                <div class="input-group">
                                    <input size="3" class="form-control form-control-sm qty-field" id="inputdefault" type="text" placeholder="Qty">

                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-add" type="button" id="button-addon2">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row add-widget">
                            <div class="col my-auto itemTitle" data-toggle="collapse" data-target="#pid${item.pid}">
                                <i class="fas fa-chevron-down item-expand-ic2  float-right"></i>
                            </div>
                        </div>
                    </div>

                </div>


            </div>

            <div id="pid${item.pid}" class="collapse custom-row" data-parent="#accordion">
                <div class="card-body">
                    ${item.prodDescr}
                </div>
            </div>
        </div>
    </c:forEach>


    <!--page number -->
    <nav class="pageNum">
        <ul class="pagination justify-content-center">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">1</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">3</a>
            </li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>


</div> <!--  end accordion -->