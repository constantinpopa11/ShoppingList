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
        <link rel="stylesheet" href="css/createtemplates.css">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>.switch input { 
                display:none;
            }
            .switch {
                display:inline-block;
                width:60px;
                height:30px;
                margin:8px;
                transform:translateY(50%);
                position:relative;
            }

            .slider {
                position:absolute;
                top:0;
                bottom:0;
                left:0;
                right:0;
                border-radius:30px;
                box-shadow:0 0 0 2px #777, 0 0 4px #777;
                cursor:pointer;
                border:4px solid transparent;
                overflow:hidden;
                transition:.4s;
            }
            .slider:before {
                position:absolute;
                content:"";
                width:100%;
                height:100%;
                background:#777;
                border-radius:30px;
                transform:translateX(-30px);
                transition:.4s;
            }

            input:checked + .slider:before {
                transform:translateX(30px);
                background: blue;
            }
            input:checked + .slider {
                box-shadow:0 0 0 2px blue,0 0 2px blue;
            }







        </style>

    </head>

    <body>

        <div class="wrapper">
            <!-- Page Content Holder -->
            <jsp:include page="sidebar.jsp" />


            <div id="content">
                <!-- navbar Content Holder -->


                <jsp:include page="navbar.jsp" />

                <div class="container">



                    <div class="form-container">
                        <form class="border border-light p-5" method="POST" action="NewProduct">

                            <p  class="title text-center    ">New List</p>

                            <!-- List Category -->
                            <input type="text" name="list_category" class="form-control mb-4" name="list_category"
                                   required placeholder="List Category" list="mus" autocomplete="off"/>
                            <datalist id="mus">
                                <option>a</option>
                                <option>b</option>
                                <option>c</option>
                                <option>d</option>
                            </datalist>


                            <!-- Name -->
                            <input type="text" name="prodName" class="form-control mb-4" required autocomplete="off" placeholder="Name">



                            <!-- Description -->
                            <div class="form-group">
                                <textarea class="form-control rounded-0 descr-area" name="prodDescr" rows="4" placeholder="List description" autocomplete="off"></textarea>
                            </div>


                            <!-- File input -->
                            <div class="input-group">
                                <label class="input-group-btn" >
                                    <span class="btn custom-btn">
                                        Search <input  type="file" class="file-picker" single>
                                    </span>
                                </label>
                                <input type="text" placeholder="Insert logo" class="form-control file-name-label" readonly>
                            </div>


                            <!-- Check option 
                           <div class="custom-control custom-checkbox mb-1">
                           <input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
                           <label class="custom-control-label" for="customCheck">Custom checkbox</label>
                           
                            </div>     -->                  
                            <!--
                             <div class="container  ">


                                      <input type="checkbox" checked="checked">
                                      
                                    <label class="customcheck">One
                                      <span class="checkmark"></span>
                                    </label>
                                   
                                      <input type="checkbox">
                                       <label class="customcheck">Two
                                      <span class="checkmark"></span>
                                    </label>

                            </div>-->


                            <div>
                                <label class="switch">
                                    <input type="checkbox">
                                    <span class="slider"></span>
                                </label>
                                Editable
                            </div>
                            <div>
                                <label class="switch">
                                    <input type="checkbox">
                                    <span class="slider"></span>
                                </label>
                                Removable
                            </div>




                            <!-- Send button -->
                            <br><button class="btn custom-btn btn-block" type="submit">Create List <i class="fas fa-arrow-right"></i></button>





                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
</body>

</html>
