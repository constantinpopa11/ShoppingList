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
        <link rel="stylesheet" href="css/detailedlist.css">
        <link rel="stylesheet" href="css/shoppinglist.css">

    </head>

    <!-- body -->
    <body>
        <div class="wrapper">
            <!-- Page Content Holder -->
            <jsp:include page="sidebar.jsp" />


            <div id="content">
                <!-- navbar Content Holder -->
                <jsp:include page="navbar.jsp" />

                <jsp:include page="shoppinglist.jsp" />


                <div id="comment-section">
                    <!-- inizio sezione commenti -->

                    <div class="commentbox">
                        <div class="row">

                            <div class="col-sm-12">
                                <h3>Comments</h3>
                            </div><!-- /col-sm-12 -->

                        </div><!-- /row -->
                        <hr>
                        <c:set var = "commentsList" scope="request" value = "${session.commentsList}"/>

                        <c:forEach items="${commentsList}" var="comment">

                            <c:choose>
                                <c:when test = "${comment.type == 0}">
                                    <div class="coomentbox row centere">
                                        <div class="col-sm-1" style="padding:0px 45px 0px 5px;">
                                            <div class="thumbnail" >
                                                <img class="img-responsive user-photo img-rounded" src="${initParam['WEBSERVER_LOCATION']}/images/userAvatars/default.png">
                                            </div><!-- /thumbnail -->
                                        </div><!-- /col-sm-1  foto col -->
                                        <div class="col-sm-11"> <!-- body comment col -->
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <strong>${comment.firstName} ${comment.lastName}</strong>  <span class="text-muted float-right"><em>${comment.date}</em></span>
                                                </div>
                                                <div class="panel-body">
                                                    ${comment.message}
                                                </div><!-- /panel-body -->
                                            </div><!-- /panel panel-default -->
                                        </div><!-- /col-sm-5 -->
                                    </div><!-- /container commento copia fino a qua-->
                                </c:when>

                                <c:when test = "${comment.type == 1}">
                                    <div class="coomentbox row centere comment-added">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <!-- nome -->
                                                <div class="col-md-4">
                                                    <strong>${comment.firstName} ${comment.lastName}</strong>
                                                </div>
                                                <div class="col-md-4">
                                                </div>
                                                <!-- data -->
                                                <div class="col-md-4">
                                                    <span class="text-muted float-right"><em>${comment.date}</em>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <!-- contenuto -->
                                                <div class="col-md-12">
                                                    "ADDED" ${comment.message}
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /container commento copia fino a qua-->
                                </c:when>

                                <c:when test = "${comment.type == 2}">
                                    <div class="coomentbox row centere comment-updated">
                                         <div class="col-md-12">
                                            <div class="row">
                                                <!-- nome -->
                                                <div class="col-md-4">
                                                    <strong>${comment.firstName} ${comment.lastName}</strong>
                                                </div>
                                                <div class="col-md-4">
                                                </div>
                                                <!-- data -->
                                                <div class="col-md-4">
                                                    <span class="text-muted float-right"><em>${comment.date}</em>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <!-- contenuto -->
                                                <div class="col-md-12">
                                                    "ADDED" ${comment.message}
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /container commento copia fino a qua-->
                                </c:when>

                                <c:when test = "${comment.type == 3}">
                                    <div class="coomentbox row centere comment-removed">
                                         <div class="col-md-12">
                                            <div class="row">
                                                <!-- nome -->
                                                <div class="col-md-4">
                                                    <strong>${comment.firstName} ${comment.lastName}</strong>
                                                </div>
                                                <div class="col-md-4">
                                                </div>
                                                <!-- data -->
                                                <div class="col-md-4">
                                                    <span class="text-muted float-right"><em>${comment.date}</em>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <!-- contenuto -->
                                                <div class="col-md-12">
                                                    "ADDED" ${comment.message}
                                                </div>
                                            </div>
                                        </div>
                                    </div><!-- /container commento copia fino a qua-->
                                </c:when>
                            </c:choose>
                            <!-- commmento box2 -->

                            <hr>
                        </c:forEach>




                        <!-- inizio sezione aggiungi commento -->
                        <div class="addcoomentbox row">
                            <div class="col-sm-1" style="padding:0px 45px 0px 5px;">
                                <div class="thumbnail">
                                    <img class="img-responsive user-photo img-rounded" src="${initParam['WEBSERVER_LOCATION']}/images/userAvatars/default.png">
                                </div><!-- /thumbnail -->
                            </div><!-- /col-sm-1  foto col -->
                            <div class="col-sm-11"> <!-- body comment col -->
                                <div class="panel-body"><!-- form body -->
                                    <div class="widget-area no-padding blank">
                                        <div class="status-upload">
                                            <form>
                                                <div class="row container-fluid">
                                                    <div class="col-9 ">
                                                        <textarea maxlength="300" class="form-control" rows="3"  placeholder="Add comment on this shopping list "  width="10%" ></textarea><br>
                                                    </div>
                                                    <div class="col-3 float-right">
                                                        <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Comment</button>
                                                    </div>
                                                    <div >
                                                        </form>
                                                    </div><!-- Status Upload  -->
                                                </div><!-- Widget Area -->
                                        </div>
                                    </div><!-- /container commento copia fino a qua-->

                                </div><!-- fine sezione commenti e aggiungi commenti -->

                                <!-- fine sezione commenti -->
                            </div> <!--  end accordion -->
                        </div>
                    </div>
                </div>
            </div>


        </div> <!-- fine wrapper -->
    </body>
</html>
