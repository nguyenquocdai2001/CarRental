<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Home Page</title>
<%@ include file="/link/link.jsp"%>

</head>
<body>
	<div class="wrapper">
		<%@ include file="/partial/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/partial/header.jsp"%>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="card card-plain table-plain-bg">
								<div class="card-header ">
									<h4 class="card-title">USER LIST</h4>
									<p class="card-category">List of client registered</p>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover">
										<thead>
											<th>ID</th>
											<th>Name</th>
											<th>Email</th>
											<!-- <th>Phone</th>
									<th>Address</th> -->
											<th>Role</th>
											<th>State</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="user" items="${listUser}" varStatus="loop">
												<tr>
													<c:if test="${user.role != 'admin'}">
														<td>${user.id}</td>
														<td>${user.name}</td>
														<td>${user.email}</td>
														<%-- <td>${user.phone}</td>
												<td>${user.address}</td> --%>
														<td>${user.role}</td>
														<td>${user.state}</td>
														<td><a href="./detailUser/${user.id}">Detail</a></td>
													</c:if>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/partial/footer.jsp"%>
	</div>
</body>
<%@ include file="/link/script.jsp"%>
</html>
