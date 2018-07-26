<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="css/shoppinglist.css">

<div id="accordion">
    <c:set var = "shoppingLists" scope="request" value = "${sessionScope.shoppingLists}"/>
    <c:set var = "slItems" scope="request" value = "${sessionScope.slItems}"/>

    <!--  contenitore  roba comune STUFF -->
    <div class="card">
        <div class="card-header list-header" id="listHeader">
            <div class="row h-100">
                <div class="col-xs-4 my-auto  first-child">
                    <div>
                        <a class="btn-lg btn-secondary  sl-dropdown" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:if test="${shoppingLists[0].slName == null}">
                                No lists to display
                            </c:if>
                            <c:if test="${shoppingLists[0].slName != null}">
                                ${shoppingLists[0].slName} <i class="fas fa-chevron-down"></i>
                            </c:if>
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <c:forEach items="${shoppingLists}" var="sl">
                                <a class="sl-dd-item dropdown-item" href="detailedlist.jsp?slid=${sl.slid}">New list <i class="fas fa-plus-circle"></i></a>
                                <hr class="nomargin">
                                <a class="sl-dd-item dropdown-item" href="detailedlist.jsp?slid=${sl.slid}">${sl.slName}</a>
                            </c:forEach>

                        </div>
                    </div>

                </div>
                <div class="col " data-toggle="collapse" data-target="#listDetails">
                </div>

                <div class="col-xs-3 my-auto " id="buttons">
                    <div>
                        <a class="float-right" href="detailedlist.jsp" ><i class="fas fa-trash list-action-ic last-ic"></i></a>
                        <a class="float-right" href="#removeList" data-toggle="modal"><i class="fas fa-share-alt list-action-ic"></i></a>
                        <a class="float-right" href="#addItem" data-toggle="modal"><i class="fas fa-cart-plus list-action-ic"></i></a>

                        <c:if test="${! fn:endsWith(pageContext.request.requestURI, '/detailedlist.jsp')}">
                            <a class="float-right" href="#addItem" data-toggle="modal"><i class="fas fa-comment-dots list-action-ic"></i></a>
                        </c:if>


                    </div>

                </div>

            </div>
        </div>

        <div id="listDetails" class="collapse"  data-parent="#accordion">
            <div class="card-body">
                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
            </div>
        </div>
    </div>




    <!--  first elemnt  -->

    <c:forEach items="${slItems}" var="item">
        <div class="card">
            <div class="card-header list-item ">

                <div class="row" >
                    <!-- checkbox -->
                    <div class="col-xs-1  my-auto first-child">
                        <label class="checkbox-container">
                            <input type="checkbox">
                            <span class="checkmark"></span>
                        </label>
                    </div>
                    <!--  banana pic -->
                    <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#pid${item.pid}">
                        <img src="http://smartyessay.com/wp-content/uploads/2018/05/crazyshit-beef-curtains-crazy-shit-beef-curtain-pussy-house-interiors.jpg"  width="60" height="60"/>
                    </div>

                    <div class="col  my-auto">
                        <div class="row">
                            <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                                <h5>${item.prodName}</h5>
                            </div>
                        </div>

                        <div class="row " >
                            <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                                <small>${item.prodCatName}</small>
                            </div>

                        </div>

                        <div class="row ">
                            <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                                <small>Quantity: ${item.quantity} ${item.prodMeasureUnit}</small>
                            </div>
                        </div>

                    </div>

                    <div class="col-xs-2 my-auto">
                        <div class="row ">
                            <div class="col ">
                                <h5 class="float-right">
                                    <a href="edit">
                                        <i class="fas fa-pencil-alt item-action-ic last-ic float-right"></i>
                                    </a>
                                    <a href="remove">
                                        <i class="fas fa-times item-action-ic float-right"></i>
                                    </a>
                                </h5>
                            </div>
                        </div>

                        <div class="row " data-toggle="collapse" data-target="#pid${item.pid}">
                            <div class="col ">
                                <small class="float-right">&nbsp;</small>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col "  data-toggle="collapse" data-target="#pid${item.pid}">
                                <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                            </div>
                        </div>

                    </div>


                </div>
            </div>

            <div id="pid${item.pid}" class="collapse" data-parent="#accordion">
                <div class="card-body">
                    ${item.prodDescr}
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="card">
        <div class="card-header list-item ">

            <div class="row" >
                <!--  banana pic -->
                <div class="col-xs-2  my-auto first-child"  data-toggle="collapse" data-target="#pid${item.pid}">
                    <img src="http://smartyessay.com/wp-content/uploads/2018/05/crazyshit-beef-curtains-crazy-shit-beef-curtain-pussy-house-interiors.jpg"  width="40" height="40"/>
                </div>

                <div class="col  my-auto">
                    <div class="row">
                        <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                            item di prova
                        </div>
                    </div>

                    <div class="row " >
                        <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                            <small>cagtararara</small>
                        </div>

                    </div>
                </div>



                <div class="col-xs-3 my-auto last-ic">

                    <div class="row ">
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
                        <div class="col my-auto" data-toggle="collapse" data-target="#pid${item.pid}">
                            <i class="fas fa-chevron-down item-expand-ic2  float-right"></i>
                        </div>
                    </div>
                </div>

            </div>


        </div>

        <div id="pid${item.pid}" class="collapse" data-parent="#accordion">
            <div class="card-body">
                ${item.prodDescr}
            </div>
        </div>
    </div>


</div> <!--  end accordion -->