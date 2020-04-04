<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation-cart.jspf"%>
<!-- Menu Table Started -->
<div class="container">
<table class="table table-striped">
	<caption>Cart</caption>
	<thead>
		<tr>
			<th>Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${menuItemList}" var="menuItem">
			<tr>
				<td>${menuItem.name}</td>
				<td><c:choose><c:when test="${menuItem.freeDelivery}">Yes</c:when><c:otherwise>No</c:otherwise></c:choose></td>
				<td><fmt:formatNumber value="${menuItem.price}" type="currency" pattern="Rs #,##,##,##,###.00" /></td>
				<td><a type="button" class="btn btn-success"
					href="/remove-cart?menuItemId=${menuItem.id}&userId=1">Delete</a></td>
			</tr>
		</c:forEach>
		<tr>
				<td></td>
				<td>Total</td>
				  <td><fmt:formatNumber value="${total}" type="currency" pattern="Rs #,##,##,##,###.00" /></td>
				<td></td>
		</tr>
	</tbody>
</table>
<!-- Menu Table Ended-->
</div>
<%@ include file="common/footer.jspf"%>