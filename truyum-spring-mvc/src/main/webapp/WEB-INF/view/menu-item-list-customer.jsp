<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation-customer.jspf"%>

		<!-- Menu Table Started -->
            
			<div class="container">
			<h3>Menu Items</h3>
	<table class="table table-striped">
		
		<thead>
			<tr>
				<th>Name</th>
				<th>Free Delivery</th>
				<th>Price</th>
				<th>Category</th>
				<th>Action</th>
			</tr>
		</thead>
		<tr>
		<tbody>
		
		 <c:if test="${addCartStatus}">Item added to Cart Successfully</c:if> 
			<c:forEach items="${menuItemList}" var="menuItem">
				<tr>
					<td>${menuItem.name}</td>
					<td><c:choose><c:when test="${menuItem.freeDelivery}">Yes</c:when><c:otherwise>No</c:otherwise></c:choose></td>
					<td><fmt:formatNumber value="${menuItem.price}" type="currency" pattern="Rs #,##,##,##,###.00"/></td>
					<td>${menuItem.category}</td>
					
					<td><a type="button" class="btn btn-success"
						href="/add-to-cart?id=${menuItem.id}">Add to Cart</a></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
	<!-- Menu Table Ended-->
</div>
		<!-- Menu Table Ended-->
			
<%@ include file="common/footer.jspf"%>