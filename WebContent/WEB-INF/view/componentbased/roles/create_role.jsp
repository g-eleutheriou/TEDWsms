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
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/roles/create")%>">
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
											<input class="span5" type="text" name="name">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="description">Description</label>
										<div class="controls">
											<textarea name="description" id="description" class="span5" rows="4"></textarea>
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
																<option value="read">Read</option>
																<option value="write">Write</option>
															</select>
														</td>
														<td>									
															<select id="products" name="products" class="span6">
																<option value="no_access">No access</option>
																<option value="read">Read</option>
																<option value="write">Write</option>
															</select>
														</td>
														<td>									
															<select id="suppliers" name="suppliers" class="span6">
																<option value="no_access">No access</option>
																<option value="read">Read</option>
																<option value="write">Write</option>
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
																<option value="read">Read</option>
																<option value="write">Write</option>
															</select>
														</td>
														<td>									
															<select id="roles" name="roles" class="span6">
																<option value="no_access">No access</option>
																<option value="read">Read</option>
																<option value="write">Write</option>
															</select>
														</td>
														<td>									
															<select id="users" name="users" class="span6">
																<option value="no_access">No access</option>
																<option value="read">Read</option>
																<option value="write">Write</option>
															</select>
														</td>												
													</tr>									
												</tbody>
											</table>
										</div>
									</div>							
								</div>
							</div>
							<div class="modal-footer">
								<input name="create" type="submit" class="btn btn-primary" value="Save changes" />
							</div>
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
