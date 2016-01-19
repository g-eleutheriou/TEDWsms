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
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/products/create")%>">
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
													<option value="${typeObj.getName()}">${typeObj.getName()}</option>
												</c:forEach>

											</select>
										</div>
									</div>
																		
									<div class="control-group">
										<label class="control-label"  for="name">Name</label>
										<div class="controls">
											<input class="span4" type="text" name="name">
										</div>
									</div>	
									
									<div class="control-group">
										<label class="control-label"  for="serial_number">Serial Number</label>
										<div class="controls">
											<input class="span4" type="text" name="serial_number">
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label"  for="description">Description</label>
										<div class="controls">
											<input class="span4" type="text" name="description">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="weight">Weight</label>
										<div class="controls">
											<input class="span4" type="text" name="weight">
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label" for="volume">Volume</label>
										<div class="controls">
											<input class="span4" type="text" name="volume">
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="dimensions">Dimensions</label>
										<div class="controls">
											<input class="span4" type="text" name="dimensions">
										</div>
									</div>	
									
									<div><i>Fill all of them if you want to create a transaction</i></div>
									<br />
									
									<div class="control-group">
										<label class="control-label" for="dimensions">Supplier *</label>
										<div class="controls">
											<select id="supplier" name="supplier" class="span4">
												<option value="no_input">No input</option>
												<c:forEach items="${supplierObjList}" var="supplierObj" varStatus="status">
													<option value="${supplierObj.getSsn()}">${supplierObj.getName()} ${supplierObj.getSurname()}</option>
												</c:forEach>
											</select>
										</div>
									</div>	
									
									<div class="control-group">
										<label class="control-label" for="dimensions">Warehouse *</label>
										<div class="controls">
											<select id="warehouse" name="warehouse" class="span4">
												<option value="no_input">No input</option>
												<c:forEach items="${warehouseObjList}" var="warehouseObj" varStatus="status">
													<option>${warehouseObj.getName()}</option>
												</c:forEach>
											</select>
										</div>
									</div>	
									
									<div class="control-group">
										<label class="control-label" for="quantity">Quantity *</label>
										<div class="controls">
											<input class="span4" type="text" name="quantity">
										</div>
									</div>	
																					
								</div>
							</div>
							<div class="modal-footer">
								<a href="<%= response.encodeURL(request.getContextPath() + "/type/create") %>"  class="btn btn-info pull-left">Create product type</a>
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
