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
                <link rel="stylesheet" href="css/detailedlist.css">
                <link rel="stylesheet" href="css/dropdown.css">
                <script src="scripts/dropdown.js"></script>
                </head>

                      <!-- body -->
                      <body>
                        <div class="wrapper">
                          <!-- Page Content Holder -->
                          <jsp:include page="sidebar.jsp" />


                          <div id="content">
                            <!-- navbar Content Holder -->
                            <jsp:include page="navbar.jsp" />

                            <div id="accordion" class="shopping-list" >
                              <!--  contenitore  roba comune STUFF -->
                              <div class="card">
                                <div class="card-header list-header" id="listHeader">
                                  <div class="row h-100">
                                    <div class="col-xs-4 my-auto  first-child">
                                      <div class="wrap-select efix">
                                        <div id="dd" class="wrapper-dropdown-3">
                                          <span class="mb-0 list-name"><strong>My Shopping list</strong></span>
                                          <ul class="dropdown">
                                            <li class="mb-0 list-name" ><a href="#">My Shopping listMy Shopping list</a></li>
                                            <li class="mb-0 list-name"><a href="#">My Shopping list</a></li>
                                            <li class="mb-0 list-name"><a href="#">My Shopping list</a></li>
                                            <li class="mb-0 list-name"><a href="#">My Shopping list My Shopping list</a></li>
                                          </ul>
                                        </div>
                                      </div>
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
                                      <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#collapseOne">
                                        <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                      </div>

                                      <div class="col  my-auto">
                                        <div class="row">
                                          <div class="col " data-toggle="collapse" data-target="#collapseOne">
                                            <h5>Item1</h5>
                                          </div>
                                        </div>

                                        <div class="row " >
                                          <div class="col " data-toggle="collapse" data-target="#collapseOne">
                                            <small>Category 1</small>
                                          </div>

                                        </div>

                                        <div class="row ">
                                          <div class="col " data-toggle="collapse" data-target="#collapseOne">
                                            <small>Quantity: 2 kg</small>
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

                                        <div class="row " data-toggle="collapse" data-target="#collapseOne">
                                          <div class="col ">
                                            <small class="float-right">&nbsp;</small>
                                          </div>

                                        </div>

                                        <div class="row">
                                          <div class="col "  data-toggle="collapse" data-target="#collapseOne">
                                            <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                          </div>
                                        </div>

                                      </div>


                                    </div>
                                  </div>

                                  <div id="collapseOne" class="collapse" data-parent="#accordion">
                                    <div class="card-body">
                                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                    </div>
                                  </div>
                                </div>
                                <!--  second elemnt  -->
                                <div class="card">
                                  <div class="card-header list-item " >

                                    <div class="row" >
                                      <!-- checkbox -->
                                      <div class="col-xs-1  my-auto first-child">
                                        <label class="checkbox-container">
                                          <input type="checkbox">
                                            <span class="checkmark"></span>
                                          </label>
                                        </div>
                                        <!--  banana pic -->
                                        <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#collapseTwo">
                                          <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                        </div>

                                        <div class="col  my-auto">
                                          <div class="row">
                                            <div class="col " data-toggle="collapse" data-target="#collapseTwo">
                                              <h5>Item2</h5>
                                            </div>
                                          </div>

                                          <div class="row " >
                                            <div class="col " data-toggle="collapse" data-target="#collapseTwo">
                                              <small>Category 1</small>
                                            </div>

                                          </div>

                                          <div class="row ">
                                            <div class="col " data-toggle="collapse" data-target="#collapseTwo">
                                              <small>Quantity: 2 kg</small>
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

                                          <div class="row " data-toggle="collapse" data-target="#collapseTwo">
                                            <div class="col ">
                                              <small class="float-right">&nbsp;</small>
                                            </div>

                                          </div>

                                          <div class="row">
                                            <div class="col "  data-toggle="collapse" data-target="#collapseTwo">
                                              <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                            </div>
                                          </div>

                                        </div>


                                      </div>
                                    </div>

                                    <div id="collapseTwo" class="collapse" data-parent="#accordion">
                                      <div class="card-body">
                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                      </div>
                                    </div>
                                  </div>
                                  <!--  third elemnt  -->
                                  <div class="card">
                                    <div class="card-header list-item " >

                                      <div class="row" >
                                        <!-- checkbox -->
                                        <div class="col-xs-1  my-auto first-child">
                                          <label class="checkbox-container">
                                            <input type="checkbox">
                                              <span class="checkmark"></span>
                                            </label>
                                          </div>
                                          <!--  banana pic -->
                                          <div class="col-xs-2  my-auto"  data-toggle="collapse" data-target="#collapseThree">
                                            <img src="http://www.gayalliance.org/wp-content/uploads/2016/02/banana-png-image.jpg"  width="60" height="60"/>
                                          </div>

                                          <div class="col  my-auto">
                                            <div class="row">
                                              <div class="col " data-toggle="collapse" data-target="#collapseThree">
                                                <h5>Item3</h5>
                                              </div>
                                            </div>

                                            <div class="row " >
                                              <div class="col " data-toggle="collapse" data-target="#collapseThree">
                                                <small>Category 1</small>
                                              </div>

                                            </div>

                                            <div class="row ">
                                              <div class="col " data-toggle="collapse" data-target="#collapseThree">
                                                <small>Quantity: 2 kg</small>
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

                                            <div class="row " data-toggle="collapse" data-target="#collapseThree">
                                              <div class="col ">
                                                <small class="float-right">&nbsp;</small>
                                              </div>

                                            </div>

                                            <div class="row">
                                              <div class="col "  data-toggle="collapse" data-target="#collapseThree">
                                                <span><i class="fas fa-chevron-down item-expand-ic last-ic float-right"></i></span>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                      </div>

                                      <div id="collapseThree" class="collapse" data-parent="#accordion">
                                        <div class="card-body">
                                          Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                                        </div>
                                      </div>

                                      <!-- inizio sezione commenti -->
                                      <hr>
                                        <nav aria-label="Page navigation example">
                                          <ul class="pagination justify-content-center">
                                            <li class="page-item disabled">
                                              <a class="page-link" href="#" tabindex="-1">Previous</a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item">
                                              <a class="page-link" href="#">Next</a>
                                            </li>
                                          </ul>
                                        </nav>
                                        <hr>
                                          <div class="commentbox">
                                            <div class="row">
                                              <hr>
                                                <div class="col-sm-12">
                                                  <h3>Commenti</h3>
                                                </div><!-- /col-sm-12 -->
                                              </div><!-- /row -->
                                              <!-- commento box -->
                                              <div class="coomentbox row">
                                                <div class="col-sm-1" style="padding:0px 50px 0px 5px;">
                                                  <div class="thumbnail">
                                                    <img class="img-responsive user-photo" src="images/userimages/default.png">
                                                    </div><!-- /thumbnail -->
                                                  </div><!-- /col-sm-1  foto col -->
                                                  <div class="col-sm-11"> <!-- body comment col -->
                                                    <div class="panel panel-default">
                                                      <div class="panel-heading">
                                                        <strong>Party Lemon</strong>  <span class="text-muted float-right "><u><em>24/02/2017 - 12:65</em></u></span>
                                                      </div>
                                                      <div class="panel-body">
                                                        Can you please add milk to the list, i need it plz
                                                      </div><!-- /panel-body -->
                                                    </div><!-- /panel panel-default -->
                                                  </div><!-- /col-sm-5 -->
                                                </div><!-- /container commento copia fino a qua-->
                                                <!-- inizio sezione aggiungi commento -->
                                                <hr>
                                                  <!-- commmento box2 -->
                                                  <div class="coomentbox row centere">
                                                    <div class="col-sm-1" style="padding:0px 45px 0px 5px;">
                                                      <div class="thumbnail" >
                                                        <img class="img-responsive user-photo img-rounded" src="images/userimages/default4bob.png">
                                                        </div><!-- /thumbnail -->
                                                      </div><!-- /col-sm-1  foto col -->
                                                      <div class="col-sm-11"> <!-- body comment col -->
                                                        <div class="panel panel-default">
                                                          <div class="panel-heading">
                                                            <strong>Rosa Culetto</strong>  <span class="text-muted float-right"><u> <em>26/12/2017 - 12:55</em></u></span>
                                                          </div>
                                                          <div class="panel-body">
                                                            I need more potatoes and some ringo biscuit, mom add them please because i don't have the admin privileges
                                                          </div><!-- /panel-body -->
                                                        </div><!-- /panel panel-default -->
                                                      </div><!-- /col-sm-5 -->
                                                    </div><!-- /container commento copia fino a qua-->
                                                    <hr>
                                                      <!-- prova log -->
                                                      <div class="row " >
                                                        <div class="box-gray centered">
                                                          <div class="row" style="padding: 10px;">
                                                            <!-- enter user who updated here -->
                                                            <strong style="padding: 0px 10px 0px 5px; " >Mother Rosa Culetto</strong>
                                                            <span class="date"><u>on <em> <!--enter date --> 26/12/2017 - 12:55</em></u></span>
                                                          </div>
                                                          <div class="row" style="padding: 0px 10px 0px 10px;">
                                                            <u><em>added</em></u>
                                                            <span class="contentlog" style="padding:0px 25px 0px 5px;" >Biscuits to the list</span>
                                                          </div>
                                                        </div>
                                                      </div>
                                                      <hr>
                                                        <!-- third comment sample -->
                                                        <div class="coomentbox row">
                                                          <div class="col-sm-1" style="padding:0px 45px 0px 5px;">
                                                            <div class="thumbnail" >
                                                              <img class="img-responsive user-photo img-rounded" src="images/userimages/default3woman.png">
                                                              </div><!-- /thumbnail -->
                                                            </div><!-- /col-sm-1  foto col -->
                                                            <div class="col-sm-11"> <!-- body comment col -->
                                                              <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                  <strong>Pino Gerna - F.A.R.T. CEO</strong>  <span class="text-muted float-right"><u> <em>32/12/2017 - 12:85</em></u></span>
                                                                </div>
                                                                <div class="panel-body">
                                                                  This is the best shopping list on the internet i couldn't find any better one on the internet it's so material design and responsive yeah!<br>
                                                                  Also, i need condoms, add them to the list
                                                                </div><!-- /panel-body -->
                                                              </div><!-- /panel panel-default -->
                                                            </div><!-- /col-sm-5 -->
                                                          </div><!-- /container commento copia fino a qua-->
                                                          <hr>
                                                            <!-- log di delete qualcosa -->
                                                            <div class="row" >
                                                              <div class="box-red">
                                                                <div class="row" style="padding: 10px;">
                                                                  <!-- enter user who updated here -->
                                                                  <strong style="padding: 0px 10px 0px 5px; opacity: 1; " >Popa Francesco</strong>
                                                                  <span class="date"><u>on <em> <!--enter date --> 26/12/2017 - 12:55</em></u></span>
                                                                </div>
                                                                <div class="row" style="padding: 0px 10px 0px 10px;">
                                                                  <u><em>removed</em></u>
                                                                  <span class="contentlog" style="padding:0px 25px 0px 5px;" >Biscuits from the list</span>
                                                                </div>
                                                              </div>
                                                              <hr>
                                                              </div>
                                                              <!-- inizio sezione aggiungi commento -->
                                                              <div class="addcoomentbox row">
                                                                <div class="col-sm-1" style="padding:0px 45px 0px 5px;">
                                                                  <div class="thumbnail">
                                                                    <img class="img-responsive user-photo img-rounded" src="images/userimages/default2gray.png">
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
                                                              </div> <!-- fine wrapper -->
                                                            </body>
                                                          </html>
