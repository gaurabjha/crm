<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Reference the stylesheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/add-customer-style.css">

<title>Add Customer</title>
</head>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>
				CRM - Customer Relationship Manager
				</h1>
		</div>
	</div>
	<br>
	<div id="continer" style="left: 50%">
		<h3>Save Customer</h3>

		<div id="content">

			<form:form action="saveCustomer" modelAttribute="customer"
				method="POST">
				<!-- Embedd the Customer ID for For Updating Pupose -->

				<form:hidden path="id" />

				<table>
					<tbody>
						<tr>
							<td><label>First Name</label></td>
							<td>:</td>
							<td><form:input path="firstName" /></td>
						</tr>
						<tr>
							<td><label>Last Name</label></td>
							<td>:</td>
							<td><form:input path="lastName" /></td>
						</tr>
						<tr>
							<td><label>Email</label></td>
							<td>:</td>
							<td><form:input path="email" /></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>

					</tbody>
				</table>

			</form:form>

			<div style="clear: both;" />
			<p>
				<a href="${pageContext.request.contextPath }/customer/list">Back
					to List </a>
			</p>
		</div>

	</div>
</body>
</html>


