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
							<h4>Supplier</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/suppliers/edit")%>">
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
											<input class="span5" type="text" name="name" value="${supplierObj.getName()}">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="surname">Surname</label>
										<div class="controls">
											<input class="span5" type="text" name="surname" value="${supplierObj.getSurname()}">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="address">Address</label>
										<div class="controls">
											<input class="span5" type="text" name="address" value="${supplierObj.getAddress()}">
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label"  for="ssn">Serial Security Number</label>
										<div class="controls">
											<input class="span5" type="text" name="ssn" value="${supplierObj.getSsn()}" readonly>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="phone">Phone</label>
										<div class="controls">
											<input class="span5" type="text" name="phone" value="${supplierObj.getPhone()}">
										</div>
									</div>

									<div class="control-group">
										<label class="control-label"  for="email">Email</label>
										<div class="controls">
											<input class="span5" type="text" name="email" value="${supplierObj.getEmail()}">
										</div>
									</div>									
																								
								</div>
							</div>
							<c:if test="${sessionScope.userLogin.getRole().getSuppliersPermissions() == 'write'}">	
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
