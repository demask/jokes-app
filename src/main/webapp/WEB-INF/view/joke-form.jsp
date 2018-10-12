<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Save Joke</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />

<style>
.error {
	color: red
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Vicevi</h2>
		</div>
	</div>
	<div id="container">
		<h3>Spremi novi vic</h3>
		<form:form action="saveJoke" modelAttribute="joke" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Kategorija:</label></td>
						<td><form:input path="category" /></td>
						<td><form:errors path="category" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Sadrzaj:</label></td>
						<td><form:textarea path="content" /></td>
						<td><form:errors path="content" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Spremi" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<div style=""></div>
		<p>
			<a href="${pageContext.request.contextPath}/toJokes/listJokes">Natrag na Viceve</a>
		</p>
	</div>
</body>
</html>