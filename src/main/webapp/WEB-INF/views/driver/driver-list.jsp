<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách tài xế</title>
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
									<h4 class="card-title">DANH SÁCH TÀI XÊ</h4>
									<p class="card-category">List of drivers</p>
									<a class="btn bg-info" href="${pageContext.request.contextPath}/add-driver">Thêm tài xế</a>
									<br> <br>
								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover" id="driver">
										<thead>
											<th>Tên tài xế</th>
											<th>Tuổi</th>
											<th>Địa chỉ</th>
											<th>Số điện thoại</th>
											<th>Ảnh</th>
											<th>Thông tin</th>
											<th>Trạng thái</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="driver" items="${listDriver}" varStatus="loop">
												<tr>
													
														<td>${driver.name}</td>
														<td>${driver.age}</td>
														<td>${driver.address}</td>
														<td>${driver.phone}</td>
														<td><img class="img" height="60px" width="60px"
														src="${driver.image}"/>
													</td>
														<td>${driver.infor}</td>
														<td>${driver.status}</td>
														<td><a href="${pageContext.request.contextPath}/edit-driver/${driver.id}">Edit</a></td>
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
