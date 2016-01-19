<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../components/metahead.jsp" />
	<style>
	  form {
		margin: 0 !important;
	  }
	  
	  .help-block {
		font-style: italic;
		margin-top: 2px !important;
	  }
	</style>
  	
    <link href="${pageContext.request.contextPath}/assets/select2/select2.css" rel="stylesheet">
	
  	<body>
  	
  		<jsp:include page="../components/header.jsp" />
	
		<div id="wrap">
			<div class="space-top"></div>
			
			<div class="container-fluid">
				<div class="row-fluid">
				
					<jsp:include page="../components/menu.jsp" />
				
					<div class="operations-container span10 pull-right">
						<div class="modal-header">
							<h4>Search Product</h4>
						</div>
						
						<form class="form-horizontal" method="get" action="<%= response.encodeURL(request.getContextPath() + "/products/view")%>">
							<div class="modal-body">
								
								<div class="well">
									
									<div class="control-group">
										<label class="control-label" for="type">Type</label>
										<div class="controls">
											<select id="type" name="option" class="span4">
												<option value="name">Name</option>
												<option value="serial_number">Serial Number</option>
												<option value="supplier">Supplier</option>
												<option value="description">Description</option>										
											</select>
										</div>
									</div>
																		
									<div class="control-group">
										<label class="control-label" for="input">Search value</label>
										<div class="controls">
											<input class="span4" type="hidden" id="inp" name="input" value="" />
										</div>
									</div>	
																					
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">Search</button>
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
  	<script src="${pageContext.request.contextPath}/assets/select2/select2.js"></script>
  	
  	<script>
		$(document).ready(function() {

			$('#type').change(function() { 
				$('#inp').select2('val', '');
			});
			
			$('#inp').select2({
				ajax: {
					url: document.URL + '?autocomplete=true',
					dataType: 'json',
					data: function (term, page) {
						return {
							q: term,
							option: $("#type").val()
						};
					},
					results: function (data, page) {
						return { results: data };
					}
				},
				formatNoMatches: "No results found...",
			});
		
		});  	
  	</script>
</html>
