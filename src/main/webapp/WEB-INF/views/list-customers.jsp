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

<title>List Customer</title>
</head>
<body>
	<center>

		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		<br>
		<div id="continer">
			<div id="content">
				<div id="mySearchBox">
					<!--  add a search box -->
					<form:form action="search" method="POST">
                Search customer :
                <select name="searchCriteria" class="my-button">
							<option value="firstName">First Name</option>
							<option value="lastName" selected="selected">Last Name</option>
							<option value="email">Email Addresss</option>
						</select>
						<input type="text" name="searchText" placeholder="Search..">
						<input type="submit" value="Search" class="my-button" />
					</form:form>
				</div>
				<br>
				<!-- Add Customer Button -->

				<input type="button" value="Add Customer"
					onclick="window.location.href='showFormForAdd'; return false"
					class="my-button" />


				<!-- HTML table for listing customer -->
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>

					<!-- loop to print all the customer -->

					<c:forEach var="tempCustomers" items="${customer }">

						<!-- Create a url for the update link for the Customer -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomers.id }"></c:param>
						</c:url>

						<!-- Create a url for the Delete link for the Customer -->

						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomers.id }"></c:param>
						</c:url>

						<tr>
							<td>${tempCustomers.firstName }</td>
							<td>${tempCustomers.lastName }</td>
							<td><a href="mailto:${tempCustomers.email } ">${tempCustomers.email }</a>
							</td>
							<td>
								<!-- Display the Update Link --> <a href="${updateLink }">Update</a>
								| <!-- Display the Update Link --> <a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete ${tempCustomers.firstName }\'s Details ?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</center>
</body>
</html>


