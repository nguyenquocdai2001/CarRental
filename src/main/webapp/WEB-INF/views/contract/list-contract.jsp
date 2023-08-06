<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách hợp đồng</title>
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
									<h4 class="card-title">DANH SÁCH HỢP ĐỒNG</h4>
									<p class="card-category">List of contracts</p>

								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover" id="contract">
										<thead>
											<th>Tên khách hàng</th>
											<th>SĐT</th>
											<th>Điểm đón</th>
											<th>Điểm trả</th>
											<th>Tuyến đường</th>
											<th>Ngày giờ đi</th>
											<th>Ngày giờ về</th>
											<th>Thành tiền</th>
											<th>Trạng thái</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="con" items="${listContract}" varStatus="loop">
												<tr>

													<td>${con.name_client}</td>
													<td>${con.phone}</td>
													<td>${con.pick_up_at}</td>
													<td>${con.comback_at}</td>
													<td>${con.route}</td>
													<td>${con.date_going}-${con.time_going}</td>
													<td>${con.date_comback}-${con.time_comback}</td>
													<td>${con.total_price}</td>
													<td>${con.status}</td>
													<td><c:if test="${con.status == 'Hết hiệu lực'}">
															<a
																href="${pageContext.request.contextPath}/details-contract/${con.id}">Details</a>
														</c:if> <c:if test="${con.status == 'Còn hiệu lực'}">
															<a
																href="${pageContext.request.contextPath}/edit-contract/${con.id}">
																Edit</a>
															<a
																href="${pageContext.request.contextPath}/add-bill/${con.id}">
																	|	Xuất hóa đơn</a>

														</c:if></td>

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
