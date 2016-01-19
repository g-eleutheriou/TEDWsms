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
							<h4>Warehouse</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/transactions/create")%>">
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
										<label class="control-label" for="warehouse_source">Source warehouse</label>
										<div class="controls">
											<select id="warehouse_source" name="warehouse_source" class="span4">
												<c:forEach items="${warehouseObjListSource}" var="warehouseObj" varStatus="status">
													<option>${warehouseObj.getName()}</option>
												</c:forEach>		
											</select>
										</div>
									</div>
																		
									<div class="control-group">
										<label class="control-label" for="warehouse_target">Source warehouse</label>
										<div class="controls">
											<select id="warehouse_target" name="warehouse_target" class="span4">
												<c:forEach items="${warehouseObjListTarget}" var="warehouseObj" varStatus="status">
													<option>${warehouseObj.getName()}</option>
												</c:forEach>		
											</select>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="product">Product</label>
										<div class="controls">
											<select id="product" name="product" class="span4">
												<c:forEach items="${productObjList}" var="productObj" varStatus="status">
													<option>${productObj.getName()} - ${productObj.getSerialNumber()}</option>
												</c:forEach>		
											</select>
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label"  for="quantity">Quantity</label>
										<div class="controls">
											<input class="span4" type="text" name="quantity" id="quantity">
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label"  for="action">Action</label>
										<div class="controls">
											<select id="action" name="action" class="span4">
												<option value="transaction">Move</option>
												<option value="insertion">Insert</option>
												<option value="extraction">Delete</option>
											</select>
										</div>
									</div>
									
									<c:if test="${param.warehouse != null}">
										<input style="display:none" type="text" name="warehouse" value="${param.warehouse}">
									</c:if>								
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
	
		<script>/* Table initialisation */
		
			function setQuantity($quantity, $product, $warehouse_source) {
				$.ajax({
					   type: "POST",
					   url: '/TEDWsms/transactions/quantity',
					   data: { product: $product.val() , warehouse_name : $warehouse_source.val()},
					   success: function(quantity) {
						   $quantity.val(quantity);
					   },
				});
			} 
			
			$(document).ready(function() {
				
				var $action = $('#action'),
					$warehouse_source = $('#warehouse_source'),
					$warehouse_target = $('#warehouse_target'),
					$product = $('#product'),
					$quantity = $('#quantity');
				
				
				setQuantity($quantity, $product, $warehouse_source);
				
				$action.change(function() {
					
					var action = $(this).val();
					
					if( action == 'transaction') {
						$warehouse_source.prop('disabled', false);
						$warehouse_target.prop('disabled', false);
						$('#no_source').remove();
						$('#no_target').remove();
					} else if( action == 'insertion' ) {
						$warehouse_source.prepend('<option id="no_source" selected>No input</option>');
						$('#no_target').remove();
						$warehouse_source.prop('disabled', true);
						$warehouse_target.prop('disabled', false);
					} else {
						$warehouse_target.prepend('<option id="no_target" selected>No input</option>');
						$('#no_source').remove();
						$warehouse_target.prop('disabled', true);
						$warehouse_source.prop('disabled', false);
					}
				});
				
				$warehouse_source.change(function() {
					if($action.val() != 'insertion') {
			
						setQuantity($quantity, $product, $warehouse_source)
					}
				});
				
				
				$product.change(function() {
					if($action.val() != 'insertion') {
		
						setQuantity($quantity, $product, $warehouse_source)
					}
				});
				
				$( 'form' ).submit(function(e) {
					
					var quantity = $quantity.val();

					if( isNaN(parseFloat(quantity)) || quantity <= 0 ) {
						
						$('.modal-body').prepend('<div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">x</a>Check your quantity input. Only positive values allowed</div>');
						
						return false;
					}
					
					if($action.val() != 'insertion') {
 						$.ajax({
							   type: "POST",
							   url: '/TEDWsms/transactions/quantity',
							   data: { product: $product.val() , warehouse_name : $warehouse_source.val()},
							   async: false,
							   success: function(quantity_db) {
								   alert("3. " + quantity + " " + quantity_db);
									if( quantity > quantity_db ) {
										$('.modal-body').prepend('<div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">x</a>Check your quantity input. The max quantity is ' + quantity_db + '</div>');
										e.preventDefault();
									}
							   },
							   error: function(jqXHR, error, errorThrown) {  
							        alert("1. " + jqXHR.status);
							        alert("2. " + jqXHR.responseText);
							   }
						});
					}
				});
			});
		</script>
  	</body>
</html>
