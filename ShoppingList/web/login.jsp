<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"  crossorigin="anonymous"></script>


        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/sidebar.css">
        <link rel="stylesheet" href="css/navbar.css">

    </head>

    <body>

        <div class="wrapper">
            <!-- Page Content Holder -->
            <jsp:include page="sidebar.jsp" />
            <!-- Page Content Holder -->
            <div id="content">

              <!-- navbar Content Holder -->
              <jsp:include page="navbar.jsp" />

                <div class="login-form">
                    <c:set var = "wrongEmail" scope = "page" value = "${param.wrongEmail}"/>
                    <c:set var = "wrongPassword" scope = "page" value = "${param.wrongPassword}"/>
                    <c:set var = "email" scope = "page" value = "${param.email}"/>

                    <form action="LogIn" method="POST">
                        <input name="prevPage" value="${header.referer}" type="hidden"/>

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
                            <button name="loginBtn" value="login" type="submit" class="btn-submit btn-lg">Log In</button>
                            <button name="loginBtn" value="cancel" type="submit" class="btn-cancel btn-lg" formnovalidate="formnovalidate">Cancel</button>
                        </div>


                        <div class="text-center">Don't have an account? <a href="signup.jsp">Sign Up here</a></div>

                    </form>

                </div>

            </div>
        </div>
    </body>

</html>
