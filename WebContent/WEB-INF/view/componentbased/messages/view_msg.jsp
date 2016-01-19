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
							<h4>Message</h4>
						</div>
						<form class="form-horizontal" method="post" action="<%= response.encodeURL(request.getContextPath() + "/messages/delete")%>">
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
								
								<div class="well form-horizontal">

									<input name="id" style="display:none" value="${ messageObj.getId() }">
						
									<div class="control-group">
										<label class="control-label" for="name">Name</label>
										<div class="controls">
											<input id="name" class="input-xlarge uneditable-input" value="${ messageObj.getName() }" readonly>
										</div>
									</div>	
										
									<div class="control-group">
										<label class="control-label" for="surname">Surname</label>
										<div class="controls">
											<span id="surname" class="input-xlarge uneditable-input">${ messageObj.getSurname() }</span>
										</div>
									</div>
		
									<div class="control-group">
										<label class="control-label" for="email">Email</label>
										<div class="controls">
											<span id="email" class="input-xlarge uneditable-input">${ messageObj.getEmail() }</span>
										</div>
									</div>
										
									<div class="control-group">
										<label class="control-label" for="subject">Subject</label>
										<div class="controls">
											<span id="subject" class="input-xlarge uneditable-input">${ messageObj.getSubject() }</span>
										</div>
									</div>
			
									<div class="control-group">
										<label class="control-label" for="message">Message</label>
										<div class="controls">
											<textarea id="message" rows="4" class="input-large" readonly>${ messageObj.getMessage() }</textarea>
										</div>
									</div>
								</div>		
							</div>
							<c:if test="${sessionScope.userLogin.getRole().getMessagesPermissions() == 'write'}">	
								<div class="modal-footer">
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
		<jsp:include page="../../components/tablesjs.jsp" />
	
		<script>/* Table initialisation */
			$(document).ready(function() {
				$('#example').dataTable( {
					"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
					"sPaginationType": "bootstrap",
					"oLanguage": {
						"sLengthMenu": "_MENU_ records per page"
					},
					"aoColumnDefs": [
						{ "bSortable": false, "aTargets": [ 0 ] }
					],
					"bLengthChange": false,
					"bInfo": false
				});
			});
		</script>
  	</body>
</html>
