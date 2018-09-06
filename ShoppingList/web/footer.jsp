<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/v4-shims.css">
<!-- footer section -->
  <footer id="myFooter">
      <div class="container">
          <div class="row">
              <div class="col-sm-3">
                  <div class="logo">
                    <a href="home.jsp"> <img src="${initParam['WEBSERVER_LOCATION']}/images/app/logo_v2.svg" width="80%"> </a>
                  </div>
              </div>
              <div class="col-sm-2">
                  <h5>Get started</h5>
                  <ul>
                      <li><a href="home.jsp">Home</a></li>
                      <li><a href="signup.jsp">Sign up</a></li>
                      <li><a href="home.jsp">My shopping lists</a></li>
                  </ul>
              </div>
              <div class="col-sm-2">
                  <h5>About us</h5>
                  <ul>
                      <li><a href="#">Company Information</a></li>
                      <li><a href="mailto:francesco.gava@studenti.unitn.it?Subject=Shopping%20Lists" target="_top">Contact us</a></li>
                  </ul>
              </div>
              <div class="col-sm-2">
                  <h5>Support</h5>
                  <ul>
                      <li><a href="#">FAQ</a></li>
                      <li><a href="mailto:francesco.gava@studenti.unitn.it?Subject=Shopping%20Lists">Help desk</a></li>
                      <li><a href="tel:+3934545858596">Call us</a></li>
                  </ul>
              </div>
              <div class="col-sm-3">
                  <div class="social-networks">
                      <a href="#" class="twitter"><i class="fas fa-envelope"></i></a>
                      <a href="#" class="facebook"><i class="fas fa-shopping-bag"></i></a>
                      <a href="#" class="google"><i class="fas fa-at"></i></a>
                  </div>
                  <button onclick="tel:+3934545858596" type="button" class="btn btn-default">Contact us</button>
              </div>
          </div>
      </div>
      <div class="footer-copyright">
          <p>© 2018 ShoppingLists LTD </p>
      </div>
  </footer>