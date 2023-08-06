<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách khách hàng</title>
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
									<h4 class="card-title">DANH SÁCH KHÁCH HÀNG</h4>
									<p class="card-category">List of clients</p>
									
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover" id="client">
										<thead>
											<th>Tên khách hàng</th>
											<th>Email</th>
											<th>Địa chỉ</th>
											<th>Số điện thoại</th>
											<th>Mã số thuế</th>
											<th>Trạng thái</th>
											<th>Detail</th>
										</thead>
										
										<tbody>
											<c:forEach var="client" items="${listClient}"
												varStatus="loop">
												<tr>
													
														<td>${client.name}</td>
														<td>${client.email}</td>
														<td>${client.address}</td>
														<td>${client.phone}</td>
														<td>${client.tax_code}</td>
														<td>${client.status}</td>
														<td><a
															href="${pageContext.request.contextPath}/edit-client/${client.id}">Edit</a></td>
													
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
