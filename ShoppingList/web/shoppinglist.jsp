<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="css/shoppinglist.css">

<c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>
<c:set var = "slItems" scope="request" value = "${sessionScope.slItems}"/>
<c:set var = "activeSL" scope="request" value = "${sessionScope.activeSL}"/>
<c:set var = "qslid" scope="request" value = "${sessionScope.qslid}"/>

<div id="accordion">

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
                    <c:if test="${slItems != null}">
                        <a class="float-right" href="detailedlist.jsp" ><i class="fas fa-trash list-action-ic"></i></a>

                        <a class="float-right" href="#" data-toggle="modal" data-target="#shareModal"><i class="fas fa-share-alt list-action-ic"></i></a>

                        <c:if test="${! fn:endsWith(pageContext.request.requestURI, '/detailedlist.jsp')}">
                            <a class="float-right" href="detailedlist.jsp?slid=${qslid}"><i class="fas fa-comment-dots first-child list-action-ic"></i></a>
                            </c:if>
                        </c:if>


                    <c:if test="${not empty shoppingLists}">
                        <a class="float-right" href="SearchProducts?lcid=${activeSL.lcid}&slid=${activeSL.slid}" ><i class="fas fa-cart-plus list-action-ic"></i></a>
                        </c:if>

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
        <c:if test="${item.slid == activeSL.slid}">

            <div class="card">
                <div class="card-header list-item ">

                    <div class="row custom-row" >
                        <!-- checkbox -->

                        <div class="col-xs-1  my-auto">
                            <label class="checkbox-container">
                                <input type="checkbox">
                                <span class="checkmark"></span>
                            </label>
                        </div>


                        <!--  banana pic -->
                        <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#pid${item.pid}">
                            <img src="${initParam['WEBSERVER_LOCATION']}${item.logoPath}"  width="60" height="60"/>
                        </div>

                        <div class="col  my-auto">
                            <div class="row">
                                <div class="col itemTitle" data-toggle="collapse" data-target="#pid${item.pid}">
                                    ${item.prodName}
                                </div>
                            </div>

                            <div class="row " >
                                <div class="col itemInfo" data-toggle="collapse" data-target="#pid${item.pid}">
                                    ${item.prodCatName}
                                </div>

                            </div>

                            <div class="row ">
                                <div class="col itemInfo" data-toggle="collapse" data-target="#pid${item.pid}">
                                    Quantity: ${item.quantity} ${item.prodMeasureUnit}
                                </div>
                            </div>

                        </div>

                        <div class="col-xs-2 my-auto">
                            <div class="row ">
                                <div class="col itemTitle float-right">
                                    <a href="edit">
                                        <i class="fas fa-pencil-alt item-action-ic float-right"></i>
                                    </a>
                                    <a href="remove">
                                        <i class="fas fa-times item-action-ic float-right"></i>
                                    </a>

                                </div>
                            </div>

                            <div class="row " data-toggle="collapse" data-target="#pid${item.pid}">
                                <div class="col ">
                                    <small class="float-right">&nbsp;</small>
                                </div>

                            </div>

                            <div class="row">
                                <div class="col itemTitle"  data-toggle="collapse" data-target="#pid${item.pid}">
                                    <span><i class="fas fa-chevron-down item-expand-ic float-right"></i></span>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>

                <div id="pid${item.pid}" class="collapse" data-parent="#accordion">
                    <div class="card-body">
                        <div>
                            ${item.prodDescr}
                        </div>
                        <hr>
                        <div>Category Info:
                            ${item.prodCatName}
                            <img width="30" src="${initParam['WEBSERVER_LOCATION']}${item.prodCatIconPath}"/><br>
                            ${item.prodCatDescr}
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>

</div> <!--  end accordion -->

