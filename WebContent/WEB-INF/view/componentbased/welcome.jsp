<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../components/metahead.jsp" />

  	<body>
  	
  		<jsp:include page="../components/header.jsp" />
	
		<div id="wrap">
			<div class="space-top"></div>
			
			<div class="container-fluid">
				<div class="row-fluid">
				
					<jsp:include page="../components/menu.jsp" />
				
					<div class="operations-container span10 pull-right">
						<div class="modal-header">
							<h4>Welcome ${sessionScope.userLogin.getUsername()}</h4>
						</div>
						<div class="modal-body">
							<div class="well">
								Use the navigation panel on your left to begin operating with the system.
							</div>
						</div>
					</div>

				</div><!--/row-->
			
				<div id="push"></div>
		
			</div><!--/.fluid-container-->
		</div><!--/#wrap-->
	
		<jsp:include page="../components/footer.jsp" />	
	
		<jsp:include page="../components/bootstrapjs.jsp" />	
		<jsp:include page="../components/tablesjs.jsp" />
  	</body>
</html>
