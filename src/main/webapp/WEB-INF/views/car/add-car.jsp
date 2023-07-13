<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Thêm xe</title>
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


							<h4 class="card-title">Thêm xe</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/add-car-post"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên</label> <input type="text" class="form-control"
												placeholder="Nhập tên xe" name="name" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Biển số</label> <input type="text"
												class="form-control" placeholder="Nhập biển số"
												name="license_plate" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Loại xe</label> <input type="text"
												class="form-control" placeholder="Nhập loại xe" name="brand"
												required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Số ghế</label> <input type="number"
												class="form-control" placeholder="Nhập số ghế"
												name="number_of_seats" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Số km đã đi</label> <input type="number"
												class="form-control" placeholder="Nhập số km"
												name="kilometer" required>
										</div>
									</div>
									<div class="col-md-6 px-1">
										<div class="form-group">
											<label for="file">Image</label> <input class="form-control"
												type="file" id="file" name="file" required />
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-md-12 pr-1">
										<div class="form-group">
											<label>Thông tin cơ bản</label> <input type="text"
												class="form-control" placeholder="Nhập thông tin"
												name="basic_infor" required>
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

