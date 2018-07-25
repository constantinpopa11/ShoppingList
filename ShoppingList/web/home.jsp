<%@page import="beans.ShoppingListBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Shopping List App</title>

        <!-- FA Icons -->
        <link href="stylesheet" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"  crossorigin="anonymous"></script>

        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <!-- jQuery CDN - Slim version (=without AJAX) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Our Custom CSS -->
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="css/sidebar.css">
        <link rel="stylesheet" href="css/navbar.css">

    </head>

    <body>


        <div class="wrapper">
            <!-- Page Content Holder -->
            <jsp:include page="sidebar.jsp" />


            <div id="content">
                <!-- navbar Content Holder -->


                <jsp:include page="navbar.jsp" />

                <c:set var = "shoppingLists" scope="request" value = "${requestScope.shoppingLists}"/>
                <c:set var = "slItems" scope="request" value = "${requestScope.slItems}"/>

                <div id="accordion" class="shopping-list"  >
                    <!--  contenitore  roba comune STUFF -->
                    <div class="card">
                        <div class="card-header list-header" id="listHeader">
                            <div class="row h-100">
                                <div class="col-xs-4 my-auto  first-child">
                                    <h5 class="mb-0 list-name" data-toggle="" >
                                        ${shoppingLists[0].slName} <i class="fas fa-chevron-down list-expand-ic"></i>
                                    </h5>
                                </div>
                                <div class="col " data-toggle="collapse" data-target="#listDetails">
                                </div>

                                <div class="col-xs-3 my-auto " id="buttons">
                                    <div>
                                        <a class="float-right" href="detailedlist.jsp" ><i class="fas fa-list list-action-ic last-ic"></i></a>
                                        <a class="float-right" href="#removeList" data-toggle="modal"><i class="fas fa-share-alt list-action-ic"></i></a>
                                        <a class="float-right" href="#addItem" data-toggle="modal"><i class="fas fa-cart-plus list-action-ic"></i></a>
                                        <a class="float-right" href="#addItem" data-toggle="modal"><i class="fas fa-file-medical list-action-ic"></i></a>

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
                                    <img src="http://smartyessay.com/wp-content/uploads/2018/05/crazyshit-beef-curtains-crazy-shit-beef-curtain-pussy-house-interiors.jpg"  width="60" height="60"/>
                                </div>

                                <div class="col  my-auto">
                                    <div class="row">
                                        <div class="col " data-toggle="collapse" data-target="#pid${item.pid}">
                                            <h5>item di prova</h5>
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
                                                        <i class="fas fa-plus"></i> Add
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
            </div>
        </div>
    </body>

</html>
