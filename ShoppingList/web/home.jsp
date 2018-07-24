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
            <jsp:include page="sidebar.jsp" />

            <!-- Page Content Holder -->
            <div id="content">

                <jsp:include page="navbar.jsp" />

                <div id="accordion" >
                    <!--  contenitore  roba comune STUFF -->
                    <div class="card">
                        <div class="card-header list-header" id="listHeader">
                            <div class="row h-100">
                                <div class="col-4 my-auto nopadding first-child">
                                    <h5 class="mb-0 list-name" data-toggle="" >
                                        My shopping list <i class="fas fa-chevron-down list-expand-ic"></i>
                                    </h5>
                                </div>
                                <div class="col nopadding" data-toggle="collapse" data-target="#listDetails">
                                </div>

                                <div class="col-3 my-auto nopadding" id="buttons">
                                    <div>
                                        <a class="float-right" href="#removeList" data-toggle="modal"><i class="fas fa-trash list-action-ic last-ic"></i></a>
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
                    <div class="card">
                        <div class="card-header list-item " id="headingOne">

                            <div class="row h-100" >
                                <div class="col-1 nopadding my-auto first-child">
                                    <label class="checkbox-container">
                                        <input type="checkbox">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <!--  banana pic -->
                                <div class="col-2 my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                    <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                </div>

                                <div class="col"  >
                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <h5>Item1</h5>
                                        </div>
                                        <div class="col-2 nopadding">
                                            <span><i class="fas fa-pencil-alt item-action-ic last-ic float-right"></i></span>
                                            <span><i class="fas fa-times item-action-ic float-right"></i></span>
                                        </div>
                                    </div>

                                    <div class="row"  data-toggle="collapse" data-target="#collapseOne">
                                        <div class="col nopadding">
                                            <small>Category 1</small>
                                        </div>

                                    </div>

                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <small>Quantity: 2 kg</small>
                                        </div>
                                        <div class="col-1 nopadding my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                            <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>
                    <!--  second elemnt  -->
                    <div class="card">
                        <div class="card-header list-item " id="headingOne">

                            <div class="row h-100" >
                                <div class="col-1 nopadding my-auto first-child">
                                    <label class="checkbox-container">
                                        <input type="checkbox">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <!--  banana pic -->
                                <div class="col-2 my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                    <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                </div>

                                <div class="col"  >
                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <h5>Item2</h5>
                                        </div>
                                        <div class="col-2 nopadding">
                                            <span><i class="fas fa-pencil-alt item-action-ic last-ic float-right"></i></span>
                                            <span><i class="fas fa-times item-action-ic float-right"></i></span>
                                        </div>
                                    </div>

                                    <div class="row"  data-toggle="collapse" data-target="#collapseOne">
                                        <div class="col nopadding">
                                            <small>Category 2</small>
                                        </div>

                                    </div>

                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <small>Quantity: 2 kg</small>
                                        </div>
                                        <div class="col-1 nopadding my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                            <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>
                    <!--  third elemnt  -->
                    <div class="card">
                        <div class="card-header list-item " id="headingOne">

                            <div class="row h-100" >
                                <div class="col-1 nopadding my-auto first-child">
                                    <label class="checkbox-container">
                                        <input type="checkbox">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <!--  banana pic -->
                                <div class="col-2 my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                    <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                </div>

                                <div class="col"  >
                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <h5>Item3</h5>
                                        </div>
                                        <div class="col-2 nopadding">
                                            <span><i class="fas fa-pencil-alt item-action-ic last-ic float-right"></i></span>
                                            <span><i class="fas fa-times item-action-ic float-right"></i></span>
                                        </div>
                                    </div>

                                    <div class="row"  data-toggle="collapse" data-target="#collapseOne">
                                        <div class="col nopadding">
                                            <small>Category 3</small>
                                        </div>

                                    </div>

                                    <div class="row" >
                                        <div class="col nopadding"  data-toggle="collapse" data-target="#collapseOne">
                                            <small>Quantity: 2 kg</small>
                                        </div>
                                        <div class="col-1 nopadding my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                            <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>



                </div> <!--  end accordion -->
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').toggleClass('active');
                    $(this).toggleClass('active');
                });
            });
        </script>
    </body>

</html>
