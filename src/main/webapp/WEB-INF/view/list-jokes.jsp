<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html>
<head>
	<title>Vicevi</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Vicevi</h2>
		</div>
	</div>
	<div id="container">	
		<div id="content">
			<table>
				<tr>
					<th>#</th>
					<th>Kategorija</th>
					<th>Vic</th>
					<th>Likes</th>
					<th>Dislikes</th>
					<th>Razlika</th>
					<th>FeedBack</th>
				</tr>
				<input type="button" value="Dodaj Vic"
				   onclick="window.location.href='showJokeForm'; return false;"
				   class="add-button"
				/>
				<!-- loop over and print jokes -->
				<c:forEach  var="tempJoke" items="${jokes}" varStatus="i">
						
					<!-- construct a "like" link with joke id -->
					<c:url var="likeLink" value="/toJokes/likeJoke">
						<c:param name="jokeId" value="${tempJoke.id}" />
					</c:url>
					
					<!-- construct a "dislike" link with joke id -->
					<c:url var="dislikeLink" value="/toJokes/dislikeJoke">
						<c:param name="jokeId" value="${tempJoke.id}" />
					</c:url>
					
					<tr>
						<td> ${i.index+1} </td>
						<td> ${tempJoke.category.getName()} </td>
						<td> ${tempJoke.content} </td>
						<td> ${tempJoke.likes} </td>
						<td> ${tempJoke.dislikes} </td>
						<td> ${tempJoke.getDifference()} </td>
						<td>
							<!-- display the update link -->
							<a href="${likeLink}">Like</a>
							|
							<a href="${dislikeLink}">Dislike</a>
						</td>
					</tr>
				</c:forEach>	
			</table>	
		</div>
	</div>
</body>
</html>









