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
							<h4>Products</h4>
						</div>
						
						<div class="modal-body">
							<table class="table table-bordered table-striped display-info" id="example">
    							<thead>
        							<tr>
							            <th><strong>#</strong></th>
							            <th><strong>Name</strong></th>
							            <th><strong>Serial Number</strong></th>
							            <th><strong>Quantity</strong></th>
							            <th><strong>Type</strong></th>
							            <th><strong>View</strong></th>
							            <c:if test="${sessionScope.userLogin.getRole().getProductsPermissions() == 'write'}">		
							            	<th><strong>Create</strong></th>
							            </c:if>
							            <th><strong>Edit</strong></th>
							        </tr>
    							</thead>
    							<tbody>
    								
  									<c:forEach items="${productsListObj}" var="productObj" varStatus="status">
							            <tr>
							                <td>${status.count}</td>
							                <td>${productObj.getName()}</td>
							                <td>${productObj.getSerialNumber()}</td>
							                <td>${productObj.getQuantity()}</td>
							                <td>${productObj.getType().getName()}</td>
							                <td><a href="<%= response.encodeURL(request.getContextPath() + "/warehouses/view") %>?name=${productObj.getName()}&serial_number=${productObj.getSerialNumber()}">Warehouses</a></td>
							                <c:if test="${sessionScope.userLogin.getRole().getProductsPermissions() == 'write'}">	
							                	<td><a href="<%= response.encodeURL(request.getContextPath() + "/transactions/create") %>?name=${productObj.getName()}&serial_number=${productObj.getSerialNumber()} ${warehouse}">Transaction</a></td>
							                </c:if>
							                <td><a href="<%= response.encodeURL(request.getContextPath() + "/products/edit") %>?name=${productObj.getName()}&serial_number=${productObj.getSerialNumber()}">Product</a></td>
							            </tr>
							        </c:forEach>
							        
							        <c:if test="${productObjList.size() == 0}">
										<td valign="top" colspan="8" class="dataTables_empty">No data available in table</td>
									</c:if>
									
							    </tbody>
							</table>						
						</div>
						<c:if test="${sessionScope.userLogin.getRole().getProductsPermissions() == 'write'}">	
							<div class="modal-footer">
								<a href="<%= response.encodeURL(request.getContextPath() + "/products/create") %>" class="btn btn-primary pull-left">Create product</a>
							</div>
						</c:if>	
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
						{ "bSortable": false, "aTargets": [ 0 , 5 , 6 , 7 ] }
					],
					"bLengthChange": false,
					"bInfo": false
				});
			});
		</script>
  	</body>
</html>
