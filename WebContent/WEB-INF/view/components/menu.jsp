<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

	<div class="span2 affix">
		<div class="well sidebar-nav">
			<ul class="nav nav-list">
				<c:if test="${sessionScope.userLogin.getRole().getUsersPermissions() != 'no_access'}">
					<li class="nav-header">Users</li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/users/view?state=inactive") %>">Activate users</a></li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/users/view?state=active") %>">View users</a></li>
				</c:if>
				<c:if test="${sessionScope.userLogin.getRole().getRolesPermissions() != 'no_access'}">
					<li class="nav-header">Roles</li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/roles/view") %>">View roles</a></li>
				</c:if>
				<c:if test="${sessionScope.userLogin.getRole().getSuppliersPermissions() != 'no_access'}">
					<li class="nav-header">Suppliers</li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/suppliers/view") %>">View suppliers</a></li>
				</c:if>
				<c:if test="${sessionScope.userLogin.getRole().getWarehousesPermissions() != 'no_access'}">
					<li class="nav-header">Warehouses</li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/warehouses/view") %>">View warehouses</a></li>
				</c:if>
				<c:if test="${sessionScope.userLogin.getRole().getProductsPermissions() != 'no_access'}">	
					<li class="nav-header">Products</li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/products/view") %>">View products</a></li>					
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/products/search") %>">Advanced search</a></li>						
				</c:if>
				<c:if test="${sessionScope.userLogin.getRole().getMessagesPermissions() != 'no_access'}">
					<li class="divider"></li>
					<li><a href="<%= response.encodeURL(request.getContextPath() + "/messages/view") %>">Messages</a></li>
				</c:if>
			</ul>
		</div><!--/.well -->
	</div><!--/span--> 