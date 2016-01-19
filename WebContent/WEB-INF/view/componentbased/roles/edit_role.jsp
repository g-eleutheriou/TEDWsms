<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../../components/metahead.jsp" />
	<style>
		form {
			margin: 0 !important;
	  	}
	</style>
	
  	<body>
  	
  		<jsp:include page="../../components/header.jsp" />
	
		<div id="wrap">
			<div class="space-top"></div>
			
			<div class="container-fluid">
				<div class="row-fluid">
				
					<jsp:include page="../../components/menu.jsp" />
				
					<div class="operations-container span10 pull-right">
						<div class="modal-header">
							<h4>Role</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/roles/edit")%>">
							<div class="modal-body">
							
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
							
								<div class="well">
									<div class="control-group">
										<label class="control-label"  for="name">Name</label>
										<div class="controls">
											<input class="span5" type="text" name="name" value="${roleObj.getName()}" readonly>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="description">Description</label>
										<div class="controls">
											<textarea name="description" id="description" class="span5" rows="4">${roleObj.getDescription()}</textarea>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="application">
											Application Privileges
										</label>
										<div class="controls">
											<table style="background-color: white; clear:none" class="table table-bordered table-striped" id="application">
												<thead>
													<tr>
														<th>Warehouses</th>
														<th>Products</th>
														<th>Suppliers</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>									
															<select id="warehouses" name="warehouses" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getWarehousesPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getWarehousesPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>
														<td>									
															<select id="products" name="products" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getProductsPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getProductsPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>
														<td>									
															<select id="suppliers" name="suppliers" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getSuppliersPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getSuppliersPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>												
													</tr>									
												</tbody>
											</table>
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label" for="administrative">Administrative Privileges</label>
										<div class="controls" id="administrative">
											<table style="background-color: white; clear:none" class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Messages</th>
														<th>Roles</th>
														<th>Users</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>									
															<select id="messages" name="messages" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getMessagesPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getMessagesPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>	
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>
														<td>									
															<select id="roles" name="roles" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getRolesPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getRolesPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>
														<td>									
															<select id="users" name="users" class="span6">
																<option value="no_access">No access</option>
																<c:choose>
																	<c:when test="${roleObj.getUsersPermissions() == 'read'}">
																		<option value="read" selected>Read</option>
																		<option value="write">Write</option>
																	</c:when>
																	<c:when test="${roleObj.getUsersPermissions() == 'write'}">
																		<option value="read">Read</option>
																		<option value="write" selected>Write</option>
																	</c:when>
																	<c:otherwise>
																		<option value="read">Read</option>
																		<option value="write">Write</option>
																	</c:otherwise>																		
																</c:choose>	
															</select>
														</td>												
													</tr>									
												</tbody>
											</table>
										</div>
									</div>							
								</div>
							</div>
							<c:if test="${sessionScope.userLogin.getRole().getRolesPermissions() == 'write'}">	
								<div class="modal-footer">
									<input name="save" type="submit" class="btn btn-primary" value="Save changes" />
									<input name="delete" type="submit" class="btn btn-danger pull-left" value="Delete" />
								</div>
							</c:if>		
						</form>	
					</div>

				</div><!--/row-->
			
				<div id="push"></div>
		
			</div><!--/.fluid-container-->
		</div><!--/#wrap-->
	
		<jsp:include page="../../components/footer.jsp" />	
	
		<jsp:include page="../../components/bootstrapjs.jsp" />	
  	</body>
</html>
