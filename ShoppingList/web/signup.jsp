<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Sign Up</title>

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
        <link rel="stylesheet" href="css/home.css">

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"  crossorigin="anonymous"></script>


        <link rel="stylesheet" href="css/signup.css">
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



                <div class="signup-form">
                    <c:set var = "firstName" scope = "page" value = "${param.firstName}"/>
                    <c:set var = "lastName" scope = "page" value = "${param.lastName}"/>
                    <c:set var = "email" scope = "page" value = "${param.email}"/>
                    <c:set var = "confirmEmail" scope = "page" value = "${param.confirmEmail}"/>


                    <form action="SignUp" method="POST">
                        <h2 class="text-center">Sign Up</h2>
                        <p class="text-center signup-tip">Create your account. It's free and only takes a minute.</p>

                        <hr>

                        <!--TODO css-->
                        <div class="error-msg">${errorMessage}</div>


                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-user"></i></span>
                            </div>

                            <c:if test="${firstName != null}">
                                <input type="text" name="firstName" value="${firstName}" class="form-control" required>
                            </c:if>
                            <c:if test="${firstName == null}">
                                <input type="text" class="form-control" name="firstName" placeholder="First Name" required="required" >
                            </c:if>

                            <c:if test="${lastName != null}">
                                <input type="text" name="lastName" value="${lastName}" class="form-control" required>
                            </c:if>
                            <c:if test="${lastName == null}">
                                <input type="text" class="form-control" name="lastName" placeholder="Last Name" required="required">
                            </c:if>

                        </div>


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
                                <span class="input-group-text"><i class="fa fa-envelope"></i><i class="fa fa-check" id="mail-check"></i></i></span>
                            </div>

                            <c:if test="${confirmEmail != null}">
                                <input type="email" name="confirmEmail" value="${confirmEmail}" class="form-control" required>
                            </c:if>
                            <c:if test="${confirmEmail == null}">
                                <input type="email" class="form-control" name="confirmEmail" placeholder="Confirm Email Address" required="required">
                            </c:if>

                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i></span></span>
                            </div>
                            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-lock"></i><i class="fa fa-check"></i></span></span>
                            </div>
                            <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" required="required">
                        </div>


                        <div class="form-group text-center">
                            <label class="checkbox-inline"><input name="acceptCheckbox" type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
                        </div>

                        <div class="form-group text-center">
                            <button type="submit" class="btn btn-primary btn-lg">Sign Up</button>
                        </div>

                        <div class="text-center">Already have an account? <a href="login.jsp">Log In here</a></div>
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