<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../components/metahead.jsp" />

	<style>
	  form {
		margin: 0 !important;
	  }
	</style>
	
  	<body>
  	
  		<jsp:include page="../components/header.jsp" />
	
		<div id="wrap">
			<div class="space-top"></div>
			
			<div class="container-fluid">
				<div class="row-fluid">
				
					<jsp:include page="../components/menu.jsp" />
				
					<div class="operations-container span10 pull-right">
						<div class="modal-header">
							<h3>Profile</h3>
						</div>
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/profile") %>">
							<div class="modal-body">
								<div class="well">
									<div class="control-group">
										<!-- Name -->
										<label class="control-label"  for="name">Name</label>
										<div class="controls">
											<input type="text" id="name" name="name" class="input-xlarge" value="${userObj.getName()}">
										</div>
									</div>
		
									<div class="control-group">
										<!-- Surname -->
										<label class="control-label"  for="username">Surname</label>
										<div class="controls">
											<input type="text" id="surname" name="surname" class="input-xlarge" value="${userObj.getSurname()}">
										</div>
									</div>
									
									<div class="control-group">
										<!-- Username -->
										<label class="control-label"  for="username">Username</label>
										<div class="controls">
											<input type="text" id="username" name="username" class="input-xlarge" readonly value="${userObj.getUsername()}">
										</div>
									</div>
		
									<div class="control-group">
										<!-- E-mail -->
										<label class="control-label" for="email">E-mail</label>
										<div class="controls">
											<input type="text" id="email" name="email" class="input-xlarge" value="${userObj.getEmail()}">
										</div>
									</div>
		
									<div class="control-group">
										<!-- Password-->
										<label class="control-label" for="password">Password</label>
										<div class="controls">
											<input type="password" id="password" name="password" class="input-xlarge">
											<p class="help-block">Password should be at least 4 characters</p>
										</div>
									</div>
		
									<div class="control-group">
										<!-- Password -->
										<label class="control-label"  for="password_confirm">Password (Confirm)</label>
										<div class="controls">
											<input type="password" id="password_confirm" name="password_confirm" class="input-xlarge">
											<p class="help-block">Please confirm password</p>
										</div>
									</div>							
								</div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-primary">Save changes</button>
							</div>
						</form>	
					</div>

				</div><!--/row-->
			
				<div id="push"></div>
		
			</div><!--/.fluid-container-->
		</div><!--/#wrap-->
	
		<jsp:include page="../components/footer.jsp" />	
	
		<jsp:include page="../components/bootstrapjs.jsp" />	
  	</body>
</html>
