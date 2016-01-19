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
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/suppliers/create")%>">
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
										<label class="control-label" for="name">Name</label>
										<div class="controls">
											<input class="span5" type="text" name="name">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="surname">Surname</label>
										<div class="controls">
											<input class="span5" type="text" name="surname">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="address">Address</label>
										<div class="controls">
											<input class="span5" type="text" name="address">
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label" for="ssn">Serial Security Number</label>
										<div class="controls">
											<input class="span5" type="text" name="ssn">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="phone">Phone</label>
										<div class="controls">
											<input class="span5" type="text" name="phone">
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="email">Email</label>
										<div class="controls">
											<input class="span5" type="text" name="email">
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
