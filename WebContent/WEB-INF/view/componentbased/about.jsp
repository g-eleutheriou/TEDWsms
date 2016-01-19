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
										<li class="active"><a href="<%= response.encodeURL(request.getContextPath() + "/about") %>">About</a></li>
										<li><a href="<%= response.encodeURL(request.getContextPath() + "/contact") %>">Contact</a></li>
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
										<li class="active"><a href="${pageContext.request.contextPath}/about">About</a></li>
										<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
									</ul>						
								</c:otherwise>
							</c:choose>
	          			</div><!--/.nav-collapse -->
	        		</div>
	      		</div>
    		</div>
		
			<form>
				<fieldset>
					<legend class="">About us</legend>
					<p>
						TEDwsms was created by George Eleftheriou as a university coursework. The creator is undergraduate student in Department of Informatics and Telecommunications of Athens University. The course was "Technology and Web Applications" and supervisors were Mr.Martakos Drakoulis and Mr.Xamodrakas Ioannis.
					</p>
				</fieldset>			
			</form>
			<div id="push"></div>
    	</div> <!-- /container -->
    </div><!--/#wrap-->	

	<jsp:include page="../components/footer.jsp" />	
	
	<jsp:include page="../components/bootstrapjs.jsp" />

  </body>
</html>

