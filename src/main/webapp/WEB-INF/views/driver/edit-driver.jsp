<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Chỉnh sửa tài xế</title>
<%@ include file="/link/link.jsp"%>
</head>
<body>
	<div class="wrapper">
		<%@ include file="/partial/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/partial/header.jsp"%>

			<div class="content">
				<div class="container-fluid">
					<div class="row mt-5">
						<div class="col-md-8">


							<h4 class="card-title">Chỉnh sửa tài xế</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/edit-driver/${driver.id}/edit"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên tài xế</label> <input type="text"
												class="form-control" value="${driver.name}"
												placeholder="Nhập tên tài xế" name="name" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tuổi</label> <input type="number" class="form-control"
												placeholder="Nhập tuổi" value="${driver.age}" name="age"
												required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Địa chỉ</label> <input type="text"
												class="form-control" placeholder="Nhập địa chỉ"
												name="address" value="${driver.address}" required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Số điện thoại</label> <input type="number"
												class="form-control" placeholder="Nhập số điện thoại"
												value="${driver.phone}" name="phone" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Trạng thái</label> <select class="form-control"
												required name="status" id="status">
												<option value="${driver.status}" selected>${driver.status}</option>
												<option value="Activated">Activated</option>
												<option value="Canceled">Canceled</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 px-1">
										<div class="form-group">
											<label for="file">Hình ảnh</label> <input type="hidden"
												name="image" value="${driver.image}" /> <input
												class="form-control" type="file" id="file" name="file"
												value="${pageContext.request.contextPath}/template/admin/upload/${driver.image}" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 pr-1">
										<div class="form-group">
											<label>Thông tin cơ bản</label> <input type="text"
												class="form-control" placeholder="Nhập thông tin"
												value="${driver.infor}" name="infor" required>
										</div>
									</div>
								</div>


								<input type="submit" class="btn btn-info btn-fill pull-right">
								<div class="clearfix"></div>
							</form>

						</div>

					</div>
				</div>
			</div>

			<%@ include file="/partial/footer.jsp"%>
		</div>
	</div>
</body>
<%@ include file="/link/script.jsp"%>
</html>

