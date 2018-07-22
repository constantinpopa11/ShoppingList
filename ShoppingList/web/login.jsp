<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="other.Utils" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Log In</title>

        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">

        <!-- jQuery CDN - Slim version (=without AJAX) -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <!-- FA Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Bootstrap CSS CDN -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"  crossorigin="anonymous">
        <!-- Our Custom CSS -->
        <link rel="stylesheet" href="css/style.css">

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"  crossorigin="anonymous"></script>


        <link rel="stylesheet" href="css/login.css">
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
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Log In</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="signup.jsp">Sign Up</a>
                            </li>
                        </ul>
                    </div>
                </nav>


                <div class="login-form">
                    <c:set var = "wrongEmail" scope = "page" value = "${param.wrongEmail}"/>
                    <c:set var = "wrongPassword" scope = "page" value = "${param.wrongPassword}"/>
                    <c:set var = "email" scope = "page" value = "${param.email}"/>


                    <form action="LogIn" method="POST">
                        <h2 class="text-center">Log In</h2>
                        <p class="text-center login-tip">Registered members can access extra features</p>

                        <hr>
                        
                        <!--TODO css-->
                        <c:if test="${wrongEmail != null}">
                            <div class="error-msg">${wrongEmail}</div>
                            <!--<a href="signup.jsp"><span class="text-center"><u>Click here to Sign Up</u></span></a>-->
                        </c:if>

                        <c:if test="${wrongPassword != null}">
                            <div class="error-msg">${wrongPassword}</div>
                            <!--<a href="forgot.jsp"><span class="text-center"><u>Forgot password?</u></span></a>-->
                        </c:if>

                        

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                            </div>
                            <c:if test="${email != null}">
                                <input type="email" name="email" value="${email}" class="form-control" required>
                            </c:if>
                            <c:if test="${email == null}">
                                <input type="email" class="form-control" name="email" placeholder="Email Address" required="required">
                            </c:if>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span></span>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                        </div>

                        <div class="clearfix mb-4">
                            <label class="pull-left checkbox-inline" id="remember-me">
                                <input type="checkbox" name="rememberMe"> Remember me</input>
                            </label>
                            <a href="#" class="pull-right">Forgot Password?</a>
                        </div>


                        <div class="form-group text-center mb-3">
                            <button type="submit" class="btn btn-primary btn-lg">Log In</button>
                        </div>


                        <div class="text-center">Don't have an account? <a href="signup.jsp">Sign Up here</a></div>

                    </form>

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