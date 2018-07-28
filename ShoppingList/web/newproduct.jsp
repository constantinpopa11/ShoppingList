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


        <script>
            $(function () {

                // We can attach the `fileselect` event to all file inputs on the page
                $(document).on('change', ':file', function () {
                    var input = $(this),
                            numFiles = input.get(0).files ? input.get(0).files.length : 1,
                            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                    input.trigger('fileselect', [numFiles, label]);
                });

                // We can watch for our custom `fileselect` event like this
                $(document).ready(function () {
                    $(':file').on('fileselect', function (event, numFiles, label) {

                        var input = $(this).parents('.input-group').find(':text'),
                                log = numFiles > 1 ? numFiles + ' files selected' : label;

                        if (input.length) {
                            input.val(log);
                        } else {
                            if (log)
                                label = input.val().replace(/\\/g, '/').replace(/.*\//, log);
                        }

                    });
                });

            });
        </script>


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
                        <form class="text-center border border-light p-5" method="POST" action="NewProduct">

                            <p class="h4 mb-4">New Product</p>

                            <!-- Shop Category -->
                            <select class="browser-default custom-select mb-4 " name="shopCategory" required autocomplete="off" onChange="window.location.href = this.value">
                                <option value="" selected disabled >Select shop category</option>
                                <option value="NewProduct?shopCat=1">shop1</option>
                                <option value="NewProduct?shopCat=2">shop2</option>
                                <option value="NewProduct?shopCat=3">shop3</option>
                                <option value="NewProduct?shopCat=4">shop4</option>
                            </select>

                            <!-- Item Category -->
                            <select class="browser-default custom-select mb-4 " name="itemCategory" required autocomplete="off ">
                                <option value="" selected disabled >Select item category</option>
                                <option value="1">Item1</option>
                                <option value="2">Item2</option>
                                <option value="3">Item3</option>
                                <option value="4">Item4</option>
                            </select>

                            <!-- Name -->
                            <input type="text" name="prodName" class="form-control mb-4" required autocomplete="off" placeholder="Product name">


                            <!-- Measure Unit -->
                            <input type="text" name="measureUnit" class="form-control mb-4" name="measureUnit"
                                   required placeholder="Measure unit" list="mus" autocomplete="off"/>
                            <datalist id="mus">
                                <option>cm</option>
                                <option>units</option>
                                <option>litre</option>
                                <option>packets</option>
                            </datalist>

                            <!-- Description -->
                            <div class="form-group">
                                <textarea class="form-control rounded-0 descr-area" name="prodDescr" rows="4" placeholder="Product description" autocomplete="off"></textarea>
                            </div>

                            <!-- File input -->


                            <div class="input-group">
                                <label class="input-group-btn" >
                                    <span class="btn custom-btn">
                                        Search <input  type="file" class="file-picker" multiple>
                                    </span>
                                </label>
                                <input type="text" placeholder="Insert logo" class="form-control file-name-label" readonly>
                            </div>

                            <!-- Send button -->
                            <br><button class="btn custom-btn btn-block" type="submit">Create product <i class="fas fa-arrow-right"></i></button>

                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
</body>

</html>
