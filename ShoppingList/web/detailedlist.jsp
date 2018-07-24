<%--
    Document   : detailedlist.jsp
    Created on : Jul 22, 2018, 4:06:04 PM
    Author     : Omar
--%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- to be modified with the actual name of the list -->
        <title>Shopping list1 details</title>

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
    </head>

    <body>
        <div class="wrapper">
          <jsp:include page="sidebar.jsp" />
            <!-- Page Content Holder -->
            <div id="content">
              <!-- header -->
              <jsp:include page="navbar.jsp" />
                <!-- CARD FOR SHOPPING LIST -->
                <div id="accordion">
                    <div class="card">
                        <div class="card-header list-header" id="listHeader">

                            <div class="row" data-toggle="collapse" data-target="#listDetails">
                                <div class="col my-auto">
                                    <h5 class="mb-0 list-name">
                                        My shopping list <i class="fas fa-chevron-down list-expand-ic"></i>
                                    </h5>
                                </div>

                                <div class="col-md-">
                                    <a href="#" data-toggle="modal"><i class="fas fa-trash list-action-ic"></i></a></div>
                                  <div class="col-md-">  <a href="#" data-toggle="modal"><i class="fas fa-plus-circle list-action-ic"></i></a></div>
                                  <div class="col-md-">  <a href="#" data-toggle="modal"><i class="fas fa-cart-plus list-action-ic"></i></a></div>
                                  <div class="col-md-">  <a href="#" data-toggle="modal"><i class="fas fa-check-square list-action-ic"></i></a>
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
                    <span class="line">
                    <hr>
                    <h2>Comments:</h2>
                    <span class="line">
                      <hr>
                      <!-- comment section-->
                      <div class="card-comment">
                        <div class="row">
                            <div class="col-1">
                              <div class="media"> <!-- comment user's immage -->
                                <a href="#" class="thumbnail float-left">
                                  <img class="img-responsive" src="images/userimages/default.png" alt="user image">
                                </a>
                                </div>
                                </div>
                                  <div class="col-7">
                                  <div class="panel panel-default">
                                  <div class="panel-heading">
                                    <strong>Allenotna apop</strong> <span class="text-muted">commented on <em>22/07/2018 - 12:56</em></span>
                                  </div>
                                  <div class="panel-body"> Please add milk and choccolate i need them for my diet also for daddy</div>
                                  </div>

                              </div><!-- media -->
                            </div><!-- col -->
                        </div><!-- row -->
                      </div>

                      <div class="card-comment">
                        <div class="row">
                            <div class="col">
                              <div class="media"> <!-- comment user's immage -->
                                <a href="#" class="thumbnail pull-left">
                                  <img class="img-responsive" src="images/userimages/default.png" alt="user image">
                                </a>

                                  <div class="panel panel-default">
                                  <div class="panel-heading">
                                    <strong>User 2</strong> <span class="text-muted">commented on <em>22/07/2018 - 13:56</em></span>
                                  </div>
                                  <div class="panel-body">I hust added bananas to the list we finished them back at home</div>
                                  </div>

                              </div><!-- media -->
                            </div><!-- col -->
                        </div><!-- row -->
                      </div>
                      <!-- add comment card -->
                      <div class="cardaddcomment">
                        <div class="row">
                            <div class="col">
                              <div class="media"> <!-- current user's immage -->
                                <a href="#" class="thumbnail pull-left">
                                  <img class="img-responsive"   src="images/userimages/default.png" alt="user image">
                                </a>

                                  <div class="panel panel-default">
                                  <div class="panel-heading">
                                    <strong>add comment </strong>
                                    <div class="panel-body">
                                      <div class="widget-area no-padding blank">
                                      <div class="status-upload">
                                      <form>
                                        <textarea class="form-control" rows="3" placeholder="Add comment on this shopping list "  width="10%" ></textarea><br>
                                        <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Share</button>
                                      </form>
                                      </div><!-- Status Upload  -->
                                      </div><!-- Widget Area -->
                                    </div>
                                  </div>

                              </div><!-- media -->
                            </div><!-- col -->
                        </div><!-- row -->
                      </div>
                </div>

            </div>

        </div>
    </div>
</body>

</html>
