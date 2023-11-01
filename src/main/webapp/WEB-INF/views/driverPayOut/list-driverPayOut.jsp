<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách khoản chi tài xế</title>
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
									<h4 class="card-title">DANH SÁCH KHOẢN CHI TÀI XÊ</h4>
									<p class="card-category">List of driver pay-outs</p>
									<p class="card-category text-danger">Tổng chi: <c:out value="${totalDriverPayOut}" /></p>
									<a class="btn bg-info" href="${pageContext.request.contextPath}/add-driverPayOut">Thêm khoản chi</a>
									<a class="btn bg-info"
										href="${pageContext.request.contextPath}/search-driverPayOut">Khoản chi theo tháng</a>
										
									<br> <br>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover" id="driverPayOut">
										<thead>
											<th>Tên tài xế</th>
											<th>Ngày tạo</th>
											<th>Số tiền</th>
											<th>Lý do</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="driverPayOut" items="${listDriverPayOut}" varStatus="loop">
												<tr>
													
														<td>${driverPayOut.name_driver}</td>
														<td>${driverPayOut.date}</td>
														<td>${driverPayOut.money}</td>
														<td>${driverPayOut.reason}</td>
														<td><a href="${pageContext.request.contextPath}/edit-driverPayOut/${driverPayOut.id}">Edit</a></td>
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
