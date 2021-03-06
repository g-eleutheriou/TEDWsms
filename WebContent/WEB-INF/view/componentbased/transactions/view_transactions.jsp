<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../../components/metahead.jsp" />

  	<body>
  	
  		<jsp:include page="../../components/header.jsp" />
	
		<div id="wrap">
			<div class="space-top"></div>
			
			<div class="container-fluid">
				<div class="row-fluid">
				
					<jsp:include page="../../components/menu.jsp" />
				
					<div class="operations-container span10 pull-right">
						<div class="modal-header">
							<h4>Transactions</h4>
						</div>
						
						<div class="modal-body">
							<table class="table table-bordered table-striped display-info" id="example">
    							<thead>
        							<tr>
							            <th><strong>#</strong></th>
							            <th><strong>Product Name</strong></th>
							            <th><strong>Product serial number</strong></th>
							            <th><strong>From warehouse</strong></th>
							            <th><strong>To warehouse</strong></th>
							            <th><strong>Quantity</strong></th>
							            <th><strong>Action</strong></th>
							            <th><strong>Date</strong></th>
							        </tr>
    							</thead>
    							<tbody>
    								
  									<c:forEach items="${transactionsListObj}" var="transactionObj" varStatus="status">
							            <tr>
							                <td>${status.count}</td>
							                <td>${transactionObj.getProduct().getName()}</td>
							                <td>${transactionObj.getProduct().getSerialNumber()}</td>
							                <td>${transactionObj.getSourceWarehouse().getName()}</td>
							                <td>${transactionObj.getTargetWarehouse().getName()}</td>
							                <td>${transactionObj.getQuantity()}</td>
							                <td>${transactionObj.getAction()}</td>
							                <td>${transactionObj.getDate()}</td>
							            </tr>
							        </c:forEach>
								    <c:if test="${transactionsListObj.size() == 0}">
										<td valign="top" colspan="8" class="dataTables_empty">No data available in table</td>
									</c:if>		
							    </tbody>
							</table>						
						</div>
	
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
