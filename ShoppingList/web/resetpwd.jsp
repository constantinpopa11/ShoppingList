<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var = "wrongEmail" scope = "page" value = "${param.wrongEmail}"/>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Reset Password</title>

        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">

        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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


        <link rel="stylesheet" href="css/resetpwd.css">

    </head>

    <body>

        <div class="wrapper">
            <!-- Page Content Holder -->
            <jsp:include page="sidebar.jsp" />
            <!-- Page Content Holder -->
            <div id="content">

                <!-- navbar Content Holder -->
                <jsp:include page="navbar.jsp" />

                <div class="resetpwd-form">

                    <form action="ResetPwd" method="POST">
                        <input name="prevPage" value="${header.referer}" type="hidden"/>

                        <h2 class="text-center">Forgot Password?</h2>
                        <p class="text-center resetpwd-tip">You can reset your password here.</p>

                        <hr>

                        <c:if test="${wrongEmail != null}">
                            <div class="error-msg">${wrongEmail}</div>
                            <!--<a href="signup.jsp"><span class="text-center"><u>Click here to Sign Up</u></span></a>-->
                        </c:if>


                        <div class="input-group mb-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                            </div>
                            <input type="email" class="form-control" name="email" placeholder="Email Address" required="required">

                        </div>

                        <div class="form-group text-center mb-3">
                            <button name="resetBtn" value="reset" type="submit" class="btn-submit btn-lg">Reset</button>
                            <button name="resetBtn" value="cancel" type="submit" class="btn-cancel btn-lg" formnovalidate="formnovalidate">Cancel</button>
                        </div>

                    </form>

                </div>

            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>

</html>
