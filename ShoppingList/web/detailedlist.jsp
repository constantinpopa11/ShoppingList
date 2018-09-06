<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var = "commentsList" scope="request" value = "${session.commentsList}"/>
<c:set var = "commentsList" scope="request" value = "${session.picturesList}"/>

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
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Lightbox -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.js"></script>
        <!-- Our Custom CSS -->
        <link rel="stylesheet" href="css/detailedlist.css">
        <link rel="stylesheet" href="css/shoppinglist.css">
        <link rel="stylesheet" href="css/createtemplates.css">
        <script src="scripts/custom-file-input.js"></script>

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


                    <div class="container">
                        <div class="row">
                            <div class="col-12 col-center m-auto">
                                <h3><br>Pictures</h3>
                            </div>
                        </div>
                        <hr>


                        <div class="row">
                            <div class="col-12 col-center m-auto">
                                <div id="picsCarousel" class="carousel slide" data-ride="carousel" data-interval="0">
                                    <!-- Carousel indicators -->

                                    <c:set var = "bulletsNum" scope="request" value = "${(fn:length(picturesList) / 4)}"/>
                                    <ol class="carousel-indicators">
                                        <li data-target="#picsCarousel" data-slide-to="0" class="active"></li>
                                            <c:forEach begin="1" end="${bulletsNum}" varStatus="loop">
                                            <li data-target="#picsCarousel" data-slide-to="${loop.index}"></li>
                                            </c:forEach>
                                    </ol>
                                    <!-- Wrapper for carousel items -->
                                    <div class="carousel-inner">
                                        <div class="item carousel-item active">
                                            <div class="row">
                                                <div class="col-3 no-padding">
                                                    <a href="#" data-toggle="modal" data-target="#uploadUserPic">
                                                        <div class="img-box">
                                                            <img src="http://www.arcdocendi.com/Forms/images/upload.png" class="img-responsive img-fluid tn" alt="">
                                                        </div>
                                                    </a>
                                                </div>


                                                <c:forEach items="${picturesList}" var="img" varStatus="picStatus">
                                                    <div class="col-3 no-padding">
                                                        <a href="${initParam['WEBSERVER_LOCATION']}/${img.picPath}" data-toggle="lightbox" data-gallery="img-gallery">
                                                            <div class="img-box">
                                                                <img src="${initParam['WEBSERVER_LOCATION']}/${img.picPath}" class="img-responsive img-fluid tn" alt="">
                                                            </div>
                                                        </a>
                                                    </div>

                                                    <c:if test="${picStatus.index == 2 || (picStatus.index > 4 && (picStatus.index+2) %4 == 0) && fn:length(picturesList) >= picStatus.index + 2 }">

                                                    </div>
                                                </div>

                                                <div class="item carousel-item">
                                                    <div class="row">
                                                    </c:if>
                                                </c:forEach>

                                            </div>
                                        </div>


                                    </div>
                                    <!-- Carousel controls -->
                                    <a class="left carousel-control-prev carousel-control " href="#picsCarousel" data-slide="prev">
                                        <i class="fa fa-chevron-left"></i>
                                    </a>
                                    <a class="right carousel-control-next carousel-control " href="#picsCarousel" data-slide="next">
                                        <i class="fa fa-chevron-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- inizio sezione commenti -->
                    <div class="commentbox">
                        <div class="row">

                            <div class="col-sm-12">
                                <h3>Comments</h3>
                            </div><!-- /col-sm-12 -->

                        </div><!-- /row -->
                        <hr>

                        <c:if test="${fn:length(commentsList) > 0}">
                            <c:forEach items="${commentsList}" var="comment">

                                <c:choose>
                                    <c:when test = "${comment.type == 0}">
                                        <div class="row coomentbox centere border-up">
                                            <div class="col-2 comm-avatar">
                                                <img width="60" alt="user-photo" src="${initParam['WEBSERVER_LOCATION']}${comment.avatarPath}">
                                            </div>
                                            <div class="col">
                                                <div class="row">
                                                    <div class="col nopadding">
                                                        <strong>${comment.firstName} ${comment.lastName}</strong>
                                                    </div>

                                                    <div class="col nopadding">
                                                        <cite class=" float-right comm-date">${comment.date}</cite>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <p class="mb-0 comm-text">
                                                        ${comment.message}
                                                    </p>
                                                </div>

                                            </div>
                                        </div>
                                    </c:when>

                                    <c:when test = "${comment.type == 1}">
                                        <div class="coomentbox row centere comment-added">
                                            <div class="col-md-12">
                                                <div class="row">
                                                    <!-- nome -->
                                                    <div class="comm-author">
                                                        <strong>${comment.firstName} ${comment.lastName}</strong>
                                                    </div>
                                                    <div class="col">
                                                    </div>
                                                    <!-- data -->
                                                    <div class="comm-date">
                                                        <span class="text-muted float-right"><em>${comment.date}</em>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <!-- contenuto -->
                                                    <div class="col-md-12">
                                                        <i>${comment.message}</i>
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
                                                    <div class="comm-author">
                                                        <strong>${comment.firstName} ${comment.lastName}</strong>
                                                    </div>
                                                    <div class="col">
                                                    </div>
                                                    <!-- data -->
                                                    <div class="comm-date">
                                                        <span class="text-muted float-right"><em>${comment.date}</em>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <!-- contenuto -->
                                                    <div class="col-md-12">
                                                        <i>${comment.message}</i>
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
                                                    <div class="comm-author">
                                                        <strong>${comment.firstName} ${comment.lastName}</strong>
                                                    </div>
                                                    <div class="col">
                                                    </div>
                                                    <!-- data -->
                                                    <div class="comm-date">
                                                        <span class="text-muted float-right"><em>${comment.date}</em>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <!-- contenuto -->
                                                    <div class="col-md-12">
                                                        <i>${comment.message}</i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /container commento copia fino a qua-->
                                    </c:when>
                                </c:choose>
                                <!-- commmento box2 -->

                                <br>
                            </c:forEach>
                        </c:if>

                        <c:if test="${fn:length(commentsList) == 0}">
                            <div class="text-center">
                                <i>There are no posts yet. Be the first to comment! <br><br></i>
                            </div>
                        </c:if>

                        <hr>

                        <!-- inizio sezione aggiungi commento -->
                        <div class="row addcoomentbox border">

                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-2"> <!-- image div-->
                                        <div class="thumbnail">
                                            <img class="img-responsive user-photo img-rounded" src="${initParam['WEBSERVER_LOCATION']}/images/userAvatars/default.png">
                                        </div><!-- /thumbnail -->
                                    </div>
                                    <div class="col-md-10">
                                        <form action="AddComment" method="POST">
                                            <div class="row">
                                                <div class="col-md-8"> <!-- text area div -->
                                                    <textarea maxlength="300" class="form-control" rows="3" name="msgText" placeholder="Add comment on this shopping list "  width="10%" ></textarea><br>
                                                </div>
                                                <div class="col-md-4 float-right"><!--button div -->
                                                    <button type="submit" class="btn btn-primary green"><i class="fa fa-share"></i> Comment</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div><!-- end add comment section -->
                    </div>
                </div>
            </div>
        </div> <!-- fine wrapper -->
        <jsp:include page="modals.jsp" />
        <jsp:include page="footer.jsp" />
    </body>
</html>

<script>
    $('#removeItemModal').on('show.bs.modal', function (e) {
        $('input[name="removePid"]').attr('value', e.relatedTarget.id);
    });

    $('#updateModal').on('show.bs.modal', function (e) {
        $('input[name="updatePid"]').attr('value', e.relatedTarget.id);
    });

    $(document).on('click', '[data-toggle="lightbox"]', function (event) {
        event.preventDefault();
        $(this).ekkoLightbox();
    });

</script>
