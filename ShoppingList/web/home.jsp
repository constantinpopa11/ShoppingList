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

        <style>
            /*
            .btn-remove-list {
                background-color: #ffffff; 
                border: none;
                width: 80px;
                height: 40px;
                color: #5067c2; 
                padding: 12px 16px; 
                border-radius: 5px;
                font-size: 14px; 
                cursor: pointer; 
            }

            .btn-remove-list:hover {
                background-color: #efefef;
                color: #5067c2;
                text-decoration: underline;
            }

            .btn-add-item {
                background-color: #ffffff; 
                border: none; 
                width: 80px;
                height: 40px; 
                color: #5067c2; 
                padding: 12px 16px; 
                border-radius: 5px;
                font-size: 14px; 
                cursor: pointer; 
            }

            .btn-add-item:hover {
                background-color: #efefef;
                color: #5067c2;
                text-decoration: underline;
            }

            */

            .list-header{
                height: 80px;
                background-color: #5067c2;
            }

            .list-name{
                color: #fff;
                font-size: 24px;
            }

            .list-name:hover{
                text-decoration: underline;
                cursor: pointer;
            }


            /* The container */
            .checkbox-container {
                display: block;
                position: relative;
                padding-left: 35px;
                margin-bottom: 12px;
                cursor: pointer;
                font-size: 22px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            /* Hide the browser's default checkbox */
            .checkbox-container input {
                position: absolute;
                opacity: 0;
                cursor: pointer;
            }

            /* Create a custom checkbox */
            .checkmark {
                position: absolute;
                top: 0;
                left: 0;
                height: 25px;
                width: 25px;
                background-color: #eee;
            }

            /* On mouse-over, add a grey background color */
            .checkbox-container:hover input ~ .checkmark {
                background-color: #ccc;
            }

            /* When the checkbox is checked, add a blue background */
            .checkbox-container input:checked ~ .checkmark {
                background-color: #5067c2;
            }

            /* Create the checkmark/indicator (hidden when not checked) */
            .checkmark:after {
                content: "";
                position: absolute;
                display: none;
            }

            /* Show the checkmark when checked */
            .checkbox-container input:checked ~ .checkmark:after {
                display: block;
            }

            /* Style the checkmark/indicator */
            .checkbox-container .checkmark:after {
                left: 9px;
                top: 5px;
                width: 5px;
                height: 10px;
                border: solid white;
                border-width: 0 3px 3px 0;
                -webkit-transform: rotate(45deg);
                -ms-transform: rotate(45deg);
                transform: rotate(45deg);
            }

            .item-action-ic{
                color: #727272;
            }

            .item-action-ic:hover{
                color: #3d3d3d;
                cursor: pointer;
            }

            .list-action-ic{
                color: #f7f7f7;
                font-size: 32px;
                margin-left: 10px;
                margin-right: 10px;
            }

            .list-action-ic:hover{
                color: #e5e5e5;
                cursor: pointer;
            }

            .item-expand-ic{
                color: #969696;
                font-size: 16px;
            } 

            .item-expand-ic:hover{
                color: #3d3d3d;
                cursor: pointer;
            }

            .list-expand-ic{
                color: #fff; 
                font-size: 16px;
            }


        </style>
    </head>

    <body>


        <div class="wrapper">
            <!-- Sidebar Holder -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <img src="./images/app/sl_logo.png" width="80%">
                </div>

                <ul class="list-unstyled components">
                    <p>Dummy Heading</p>
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
                                <a href="#">Page 1</a>
                            </li>
                            <li>
                                <a href="#">Page 2</a>
                            </li>
                            <li>
                                <a href="#">Page 3</a>
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
                        <a href="https://bootstrapious.com/tutorial/files/sidebar.zip" class="download">Download source</a>
                    </li>
                    <li>
                        <a href="https://bootstrapious.com/p/bootstrap-sidebar" class="article">Back to article</a>
                    </li>
                </ul>
            </nav>

            <!-- Page Content Holder -->
            <div id="content">

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

                <div id="accordion" style="width: 620px; margin: 0 auto;">


                    <div class="card">
                        <div class="card-header list-header" id="listHeader">

                            <div class="row" data-toggle="collapse" data-target="#listDetails">
                                <div class="col my-auto">
                                    <h5 class="mb-0 list-name">
                                        My shopping list <i class="fas fa-chevron-down list-expand-ic"></i>
                                    </h5>
                                </div>

                                <div class="col-4 my-auto">
                                    <a href="#addItem" data-toggle="modal"><i class="fas fa-cart-plus list-action-ic"></i></a>
                                    <a href="#addItem" data-toggle="modal"><i class="fas fa-file-medical list-action-ic"></i></a>
                                    <a href="#removeList" data-toggle="modal"><i class="fas fa-trash list-action-ic"></i></a>
                                </div>

                            </div>
                        </div>

                        <div id="listDetails" class="collapse"  data-parent="#accordion">
                            <div class="card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">


                                <div class="row" data-toggle="collapse" data-target="#collapseOne">
                                    <div class="col-1 my-auto">
                                        <label class="checkbox-container">
                                            <input type="checkbox">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>

                                    <div class="col-3 my-auto">
                                        <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                    </div>

                                    <div class="col">

                                        <div class="row" >
                                            <div class="col">
                                                Item1
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-pencil-alt item-action-ic"></i></span>
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-times item-action-ic"></i></span>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Category 1</small>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Quantity: 2 kg</small>
                                            </div>
                                            <div class="col-1 my-auto">
                                                <span><i class="fas fa-chevron-down item-expand-ic"></i></span>
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



                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">


                                <div class="row" data-toggle="collapse" data-target="#collapseOne">
                                    <div class="col-1 my-auto">
                                        <label class="checkbox-container">
                                            <input type="checkbox">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>

                                    <div class="col-3 my-auto">
                                        <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                    </div>

                                    <div class="col">

                                        <div class="row">
                                            <div class="col">
                                                Item1
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-pencil-alt item-action-ic"></i></span>
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-times item-action-ic"></i></span>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Category 1</small>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Quantity: 2 kg</small>
                                            </div>
                                            <div class="col-1 my-auto">
                                                <span><i class="fas fa-chevron-down item-expand-ic"></i></span>
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

                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h5 class="mb-0">


                                <div class="row" data-toggle="collapse" data-target="#collapseOne">
                                    <div class="col-1 my-auto">
                                        <label class="checkbox-container">
                                            <input type="checkbox">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>

                                    <div class="col-3 my-auto">
                                        <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                    </div>

                                    <div class="col">

                                        <div class="row">
                                            <div class="col">
                                                Item1
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-pencil-alt item-action-ic"></i></span>
                                            </div>
                                            <div class="col-1">
                                                <span><i class="fas fa-times item-action-ic"></i></span>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Category 1</small>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="col">
                                                <small>Quantity: 2 kg</small>
                                            </div>
                                            <div class="col-1 my-auto">
                                                <span><i class="fas fa-chevron-down item-expand-ic"></i></span>
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
                </div>




            </div>

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