<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation-admin.jspf"%>

<!-- Menu Table Started-->

<div class="container">
	<table class="table table-striped">
		<caption>Menu Items</caption>
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Active</th>
				<th>Date of Launch</th>
				<th>Category</th>
				<th>Free Delivery</th>
				<th>Action</th>
			</tr>
		</thead>
		<tr>
		<tbody>
			<c:forEach items="${menuItemList}" var="menuItem">
			
				<tr>
					<td>${menuItem.name}</td>
					
					<td><fmt:formatNumber value="${menuItem.price}" type="currency" pattern="Rs #,##,##,##,###.00" /></td>
					
					<td><c:choose><c:when test="${menuItem.active}">Yes</c:when><c:otherwise>No</c:otherwise></c:choose></td>
					<td><fmt:formatDate value="${menuItem.dateOfLaunch}"
							pattern="dd/MM/yyyy" /></td>
					<td>${menuItem.category}</td>
					<td><c:choose><c:when test="${menuItem.freeDelivery}">Yes</c:when><c:otherwise>No</c:otherwise></c:choose></td>
					<td><a type="button" class="btn btn-success"
						href="/edit-menu-item?id=${menuItem.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- Menu Table Ended-->
</div>
<%@ include file="common/footer.jspf"%>