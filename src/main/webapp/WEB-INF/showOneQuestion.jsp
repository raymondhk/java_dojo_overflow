<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dojo Overflow</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>${question.question}</h1>
					<h3>Tags: 
					<c:forEach items="${question.tags}" var="tag">
						<button class="btn btn-default">${tag.subject}</button>
					</c:forEach>
					</h3>
					<p><a href="/questions">Back to Questions Dashboard</a></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Answers</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${question.ans}" var="a">
									<tr>
										<td>${a.answer}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
				<div class="col-md-4">
					<p>${errors}</p>
					<form action="/questions/${question.id}" method="post">
						<div class="form-group">
							Answer:<textarea name="answer" rows="5" class="form-control"></textarea>
						</div>
						<input type="submit" class="btn btn-success pull-right">
					</form>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</body>
</html>