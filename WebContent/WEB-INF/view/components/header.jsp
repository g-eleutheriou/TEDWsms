<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

    <div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<span class="brand">TEDwsms</span>
				<div class="nav-collapse collapse">
					<c:choose>
						<c:when test="${sessionScope.userLogin != null}">
							<ul class="nav">
								<li class="active"><a href="<%= response.encodeURL(request.getContextPath() + "/welcome") %>">Home</a></li>
								<li><a href="<%= response.encodeURL(request.getContextPath() + "/about") %>">About</a></li>
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
								<li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
								<li><a href="${pageContext.request.contextPath}/about">About</a></li>
								<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
							</ul>						
						</c:otherwise>
					</c:choose>
				</div><!--/.nav-collapse -->
			</div>
		</div>
    </div>

    	
				