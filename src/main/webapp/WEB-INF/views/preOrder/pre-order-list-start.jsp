<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách đơn đặt trước</title>
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
									<h4 class="card-title">DANH SÁCH ĐƠN ĐẶT TRƯỚC</h4>
									<p class="card-category">List of pre-orders</p>
									<a class="btn"
										href="${pageContext.request.contextPath}/add-preOrder">Thêm
										đơn</a>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover">
										<thead>
											<th>Tên khách hàng</th>
											<th>SĐT</th>
											<th>Điểm đón</th>
											<th>Tuyến đường</th>
											<th>Ngày giờ đi</th>
											<th>Ngày giờ về</th>
											<th>Thành tiền</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="pre" items="${listpreOrder}" varStatus="loop">
												<tr>
													<c:if test="${pre.status == 'Khởi tạo'}">
														<td>${pre.name_client}</td>
														<td>${pre.phone_client}</td>
														<td>${pre.pick_up_at}</td>
														<td>${pre.route}</td>
														<td>${pre.date_going}- ${pre.time_going}</td>
														<td>${pre.date_comback}- ${pre.time_comback}</td>
														<td>${pre.total_price}</td>
														<td><a
															<c:if test="${pre.status != 'Đã xong'}">
																<a
																	href="${pageContext.request.contextPath}/edit-preOrder/${pre.id}">	Edit</a>
																<a
																	href="${pageContext.request.contextPath}/add-contract/${pre.id}">	|	Add
																	contract</a>
															</c:if></td>
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

	</div>
</body>
<%@ include file="/link/script.jsp"%>
</html>
