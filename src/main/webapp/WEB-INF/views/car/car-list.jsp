<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách xe</title>
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
									<h4 class="card-title">CAR LIST</h4>
									<p class="card-category">List of cars</p>
									<a class="btn" href="${pageContext.request.contextPath}/add-car">Thêm xe</a>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover">
										<thead>
											<th>Tên xe</th>
											<th>Loại xe</th>
											<th>Biển số	</th>
											<th>Số ghế</th>
											<th>Số km</th>	
											<th>Ảnh</th>
											<th>Thông tin</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="car" items="${listCar}" varStatus="loop">
												<tr>
													
														<td>${car.name}</td>
														<td>${car.brand}</td>
														<td>${car.license_plate}</td>
														<td>${car.number_of_seats}</td>
														<td>${car.kilometer}</td>
														<td><img class="img" height="60px" width="60px"
														src="${pageContext.request.contextPath}/template/admin/upload/
													<c:choose>
														<c:when test="${car.image ne null}">
															${car.image}		
														</c:when>
														<c:otherwise>
															springmvc.png
														</c:otherwise>
													</c:choose>
												" />
													</td>
														<td>${car.basic_infor}</td>
														<td><a href="${pageContext.request.contextPath}/edit-car/${car.id}">Edit</a></td>
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
