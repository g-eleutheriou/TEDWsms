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
							<h4>Supply</h4>
						</div>
						
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/supplies/create")%>">
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
										<label class="control-label" for="product">Product</label>
										<div class="controls">
											<select id="product" name="product" class="span4">
												<c:forEach items="${productObjList}" var="productObj">
													<option>${productObj.getName()} - ${productObj.getSerialNumber()}</option>
												</c:forEach>		
											</select>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label" for="price">Price</label>
										<div class="controls">
											<input class="span3" type="text" name="price" id="price">
										</div>
									</div>
											
									<input style="display:none;" class="span5" type="text" name="ssn" value="${param.ssn}">													
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
			$(document).ready(function() {
				
				$( 'form' ).submit(function() {
					
					var price = $('#price').val();
					
					if( isNaN(parseFloat(price)) || price < 0 ) {
						
						$('.modal-body').prepend('<div class="alert alert-error"><a class="close" data-dismiss="alert" href="#">x</a>Check your price input. No negative values allowed</div>');
						
						return false;
					}
					
				});
			
			});
		</script>
  	</body>
</html>
