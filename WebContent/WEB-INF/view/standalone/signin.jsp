<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Sign in &middot; Twitter Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      form {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      
      form .checkbox {
        margin-bottom: 20px;
      }
	  
      form input[type="text"],
      form input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
	  
	  .not-visible {
		display: none;
	  }
	  
	  .form-signup {
		max-width: 600px;
	  }
	  
	  .help-block {
		font-style: italic;
		margin-top: -5px !important;
	  }
	  
	  .link-space-left {
		margin-left: 20px;
	  }
	  
    </style>
    <link href="assets/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="assets/css/custom-css.css" rel="stylesheet">
	<link rel="shortcut icon" href="assets/favicon.png">
  </head>

  <body>
    <div class="container">
		
		<form class="form-horizontal" action="login" method="post">
			<fieldset>
				<legend class="">Please sign in</legend>
				
				<c:if test="${message != null}">
					<div class="alert alert-error">
						<a class="close" data-dismiss="alert" href="#">x</a>
						<c:out value="${message}" />
					</div> 
				</c:if>

				<input name="username" type="text" class="input-block-level" placeholder="Username">
				<input name="password" type="password" class="input-block-level" placeholder="Password">
				<button class="btn btn-large btn-primary" type="submit">Sign in</button>
				<a class="link-space-left" href="<%= response.encodeURL(request.getContextPath( ) + "/register") %>">No account? Please register</a>
			</fieldset>
		</form>		
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/custom-js.js"></script>
	
  </body>
</html>
