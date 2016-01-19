<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../../components/metahead.jsp" />
	<style>
	  form {
		margin: 0 !important;
	  }
	  
	  .help-block {
		font-style: italic;
		margin-top: 2px !important;
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
							<h4>Modal header</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/users/edit")%>">
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
								
									<c:if test="${state == 'inactive'}">
										<div class="control-group">
											<!-- Role -->
											<label class="control-label" for="role">Status</label>
											<div class="controls">
												<select id="status" name="state" class="span4">
													<option value="inactive">Inactive</option>
													<option value="active">Active</option>
												</select>
											</div>
										</div>
									</c:if>
									
									<div class="control-group">
										<!-- Role -->
										<label class="control-label"  for="role">Role</label>
										<div class="controls">
											<select id="role" name="role" class="span4">
												
												<option value="false">Choose a role...</option>
												
												<c:forEach items="${roleObjList}" var="roleObj">
													<c:choose>
														<c:when test="${roleObj.getName() == userObj.getRole().getName()}">
															<option selected value="${roleObj.getName()}">${roleObj.getName()}</option>										
														</c:when>
														<c:otherwise>
															<option value="${roleObj.getName()}">${roleObj.getName()}</option>
														</c:otherwise>																			
													</c:choose>
												</c:forEach>

											</select>
										</div>
									</div>	
									
									<div class="control-group">
										<!-- Name -->
										<label class="control-label"  for="name">Name</label>
										<div class="controls">
											<input class="span4" type="text" name="name" value="${userObj.getName()}" readonly>
										</div>
									</div>
		
									<div class="control-group">
										<!-- Surname -->
										<label class="control-label"  for="surname">Surname</label>
										<div class="controls">
											<input class="span4" type="text" name="surname" value="${userObj.getSurname()}" readonly>
										</div>
									</div>
									
									<div class="control-group">
										<!-- Username -->
										<label class="control-label"  for="username">Username</label>
										<div class="controls">
											<input class="span4" type="text" name="username" value="${userObj.getUsername()}" readonly>
										</div>
									</div>
		
									<div class="control-group">
										<!-- E-mail -->
										<label class="control-label" for="email">E-mail</label>
										<div class="controls">
											<input class="span4" type="text" name="email" value="${userObj.getEmail()}" readonly>
										</div>
									</div>
						
								</div>
							</div>
							<c:if test="${sessionScope.userLogin.getRole().getUsersPermissions() == 'write' && userObj.getRole().getName() != 'Manager'}">
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
