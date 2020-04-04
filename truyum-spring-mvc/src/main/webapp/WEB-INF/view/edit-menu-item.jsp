<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation-admin.jspf"%>

<!-- Edit Menu Using Table Started-->
<div class="container">
	<h3>Edit Menu Item</h3>

	<form:form method="post" modelAttribute="menuItem">
		<form:hidden path="id" />
		<div class="form-group">

			<form:label path="name" name="">Name</form:label>
			<form:input path="name" type="text" class="form-control" />
			<form:errors path="name" cssClass="text-warning" />
		</div>
		<div class="form-row">
			<div class="col-7">
				<form:label path="price">Price (Rs.)</form:label>
				<form:input path="price" type="text" class="form-control" />
				<form:errors path="price" cssClass="text-warning" />
			</div>
			<div class="col">
				<form:label path="active">Active</form:label>
				<form:radiobutton path="active" value="Yes" />
				Yes
				<form:radiobutton path="active" value="No" />
				No
				<form:errors path="active" cssClass="text-warning" />
			</div>
			<div class="col">
				<form:label path="dateOfLaunch">Date Of Launch</form:label>
				<form:input path="dateOfLaunch" class="form-control" />
				<form:errors path="dateOfLaunch" cssClass="text-warning" />
			</div>
			<div class="col">
				<form:label path="category">Category</form:label>
				<form:select path="category">
					<form:option value="Starters" label="Starters" />
					<form:option value="Main Course" label="Main Course" />
					<form:option value="Dessert" label="Dessert" />
					<form:option value="Drinks" label="Drinks" />
				</form:select>
				<form:errors path="category" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-row">
			<div class="col">
				<form:label path="freeDelivery">Free Delivery</form:label>
				<form:checkbox path="freeDelivery" />
				<form:errors path="freeDelivery" cssClass="text-warning" />
			</div>
		</div>
		<div class="form-row">
			<div class="col">
				<button type="submit" value="Save" id="submit"
					class="btn btn-success">Save</button>
			</div>
		</div>
	</form:form>
</div>
<!-- Menu Editing Using Form Ended-->
<!-- JavaScript Popup Started -->
<script>
	function validateMenuItemForm() {
		alert("Above mentioned validations needs to be done on click of this button");

	}
</script>
<!-- JavaScript Popup Started -->
<%@ include file="common/footer.jspf"%>