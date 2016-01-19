<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>TEDwsms</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le styles -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        background-color: #fcfcfc;
      }
	 
	 .contact-section {
		margin-left: 0px !important;
	  }
     
	 form {
		overflow: hidden;
        max-width: 800px;
        padding: 19px 29px 29px;
        margin: 40px auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      form .form-contact-heading{
        margin-bottom: 10px;
      }

    </style>
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/custom-css.css" rel="stylesheet">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/ico/favicon.png">
  </head>

  <body>

	<div id="wrap">
		<div class="space-top"></div>
    	
    	<div class="container">
	
			<div class="navbar navbar-inverse navbar-fixed-top">
	      		<div class="navbar-inner">
	        		<div class="container">
	          			<span class="brand">TEDwsms</span>
	          			<div class="nav-collapse collapse">
	            			<c:choose>
								<c:when test="${sessionScope.userLogin != null}">
									<ul class="nav">
										<li><a href="<%= response.encodeURL(request.getContextPath() + "/welcome") %>">Home</a></li>
										<li><a href="<%= response.encodeURL(request.getContextPath() + "/about") %>">About</a></li>
										<li class="active"><a href="<%= response.encodeURL(request.getContextPath() + "/contact") %>">Contact</a></li>
									</ul>
									<ul class="nav pull-right">
									  	<li class="dropdown">
									    	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									          Account
									          <b class="caret"></b>
									    	</a>
									    	<ul class="dropdown-menu">
												<li><a href="<%= response.encodeURL(request.getContextPath() + "/profile") %>">Profile</a></li>
												<li><a href="<%= response.encodeURL(request.getContextPath() + "/logout") %>">Logout</a></li>
									    	</ul>
									    </li>
									</ul>
								</c:when>
								<c:otherwise>
									<ul class="nav">
										<li><a href="${pageContext.request.contextPath}/">Home</a></li>
										<li><a href="${pageContext.request.contextPath}/about">About</a></li>
										<li class="active"><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
									</ul>						
								</c:otherwise>
							</c:choose>
	          			</div><!--/.nav-collapse -->
	        		</div>
	      		</div>
    		</div>

			<c:if test="${message != null}">
				<c:choose>
					<c:when test="${type == 'success'}">
						<div class="alert alert-success"> 	
							<a class="close" data-dismiss="alert" href="#">x</a>
							${message}
						</div> 											
					</c:when>
					<c:otherwise>
						<div class="alert alert-error"> 
							<a class="close" data-dismiss="alert" href="#">x</a>
							${message}
						</div> 	
					</c:otherwise>																			
				</c:choose>
			</c:if>
								
			<form method="post" action="<%= response.encodeURL(request.getContextPath() + "/contact")%>">
				<fieldset>
					<legend class="">Contact us</legend>
				
					<div class="span3 contact-section">
						<label>First Name</label>
						<input type="text" class="span3" name="name" value="${sessionScope.userLogin.getName()}">
						<label>Last Name</label>
						<input type="text" class="span3" name="surname" value="${sessionScope.userLogin.getSurname()}">
						<label>Email Address</label>
						<input type="text" class="span3" name="email" value="${sessionScope.userLogin.getEmail()}">
						<label>Subject</label>
						<select id="subject" name="subject" class="span3" name="subject">
							<option value="general">General Customer Service</option>
							<option value="suggestions">Suggestions</option>
							<option value="support">Product Support</option>
						</select>
					</div>
					<div class="pull-right">
						<label>Message</label>
						<textarea name="message" id="message" class="input-xlarge span5" rows="10"></textarea>
					</div>
					<div class="span8">
						<button type="submit" class="btn btn-large btn-primary pull-right">Send</button>
					</div>
				</fieldset>			
			</form>
			<div id="push"></div>
    	</div> <!-- /container -->
    </div><!--/#wrap-->	

	<jsp:include page="../components/footer.jsp" />	
	
	<jsp:include page="../components/bootstrapjs.jsp" />

  </body>
</html>

