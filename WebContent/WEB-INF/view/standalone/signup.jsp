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
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
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
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom-css.css" rel="stylesheet">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/favicon.png">
  </head>

  <body>
    <div class="container">
		<form class="form-signup form-horizontal" method="post" action="<%= response.encodeURL("register") %>">
			<fieldset>
				<legend class="">Please sign up</legend>
				<c:if test="${message != null}">
					<div class="alert alert-error">
						<a class="close" data-dismiss="alert" href="#">x</a>
						<c:out value="${message}" />
					</div> 
				</c:if>
				<div class="control-group">
					<!-- Name -->
					<label class="control-label"  for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="" class="input-xlarge">
						<p class="help-block">Please provide your name</p>
					</div>
				</div>

				<div class="control-group">
					<!-- Surname -->
					<label class="control-label"  for="username">Surname</label>
					<div class="controls">
						<input type="text" id="surname" name="surname" placeholder="" class="input-xlarge">
						<p class="help-block">Please provide your surname</p>
					</div>
				</div>
				
				<div class="control-group">
					<!-- Username -->
					<label class="control-label"  for="username">Username</label>
					<div class="controls">
						<input type="text" id="username" name="username" placeholder="" class="input-xlarge">
						<p class="help-block">Username can contain any letters or numbers, without spaces</p>
					</div>
				</div>

				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="email">E-mail</label>
					<div class="controls">
						<input type="text" id="email" name="email" placeholder="" class="input-xlarge">
						<p class="help-block">Please provide your E-mail</p>
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" name="password" placeholder="" class="input-xlarge">
						<p class="help-block">Password should be at least 4 characters</p>
					</div>
				</div>

				<div class="control-group">
					<!-- Password -->
					<label class="control-label"  for="password_confirm">Password (Confirm)</label>
					<div class="controls">
						<input type="password" id="password_confirm" name="password_confirm" placeholder="" class="input-xlarge">
						<p class="help-block">Please confirm password</p>
					</div>
				</div>

				<div class="control-group">
					<!-- Button -->
					<div class="controls">
						<button class="btn btn-success btn-large">Register</button>
						<a class="link-space-left" href="<%= response.encodeURL(request.getContextPath( ) + "/login") %>">Already a member? Please login</a>
					</div>
				</div>
			</fieldset>
		</form>	
    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/custom-js.js"></script>
	
  </body>
</html>
