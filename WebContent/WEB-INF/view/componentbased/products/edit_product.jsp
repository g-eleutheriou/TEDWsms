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
							<h4>Product</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/products/edit")%>">
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
										<label class="control-label" for="type">Type</label>
										<div class="controls">
											<select id="type" name="type" class="span4">
									
												<c:forEach items="${typeObjList}" var="typeObj">
													<c:choose>
														<c:when test="${typeObj.getName() == productObj.getType().getName()}">
															<option selected value="${typeObj.getName()}">${typeObj.getName()}</option>										
														</c:when>
														<c:otherwise>
															<option value="${typeObj.getName()}">${typeObj.getName()}</option>
														</c:otherwise>																			
													</c:choose>
												</c:forEach>

											</select>
										</div>
									</div>
																		
									<div class="control-group">
										<label class="control-label"  for="name">Name</label>
										<div class="controls">
											<input class="span4" type="text" name="name" value="${productObj.getName()}" readonly>
										</div>
									</div>	
									
									<div class="control-group">
										<label class="control-label"  for="serial_number">Serial Number</label>
										<div class="controls">
											<input class="span4" type="text" name="serial_number" value="${productObj.getSerialNumber()}" readonly>
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label"  for="description">Description</label>
										<div class="controls">
											<input class="span4" type="text" name="description" value="${productObj.getDescription()}">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="weight">Weight</label>
										<div class="controls">
											<input class="span4" type="text" name="weight" value="${productObj.getWeight()}">
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label" for="volume">Volume</label>
										<div class="controls">
											<input class="span4" type="text" name="volume" value="${productObj.getVolume()}">
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="dimensions">Dimensions</label>
										<div class="controls">
											<input class="span4" type="text" name="dimensions" value="${productObj.getDimensions()}">
										</div>
									</div>	
																					
								</div>
							</div>
							<c:if test="${sessionScope.userLogin.getRole().getProductsPermissions() == 'write'}">	
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
