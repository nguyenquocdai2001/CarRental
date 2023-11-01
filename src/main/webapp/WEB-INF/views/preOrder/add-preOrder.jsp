<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Thêm đơn đặt trước</title>
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


							<h4 class="card-title">Thêm đơn đặt trước</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/add-preOrder-post"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-12 pr-1">
										<div class="form-group">
											<label>ID khách hàng</label> <input type="text"
												class="form-control" placeholder="Nhập ID khách hàng"
												name="id_client" >
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên khách hàng (doanh nghiệp)*</label> <input type="text"
												class="form-control" placeholder="Nhập tên khách hàng"
												name="name_client" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Số điện thoại KH*</label> <input type="number"
												class="form-control" placeholder="Nhập số điện thoại"
												name="phone_client" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Tên xe*</label> <select class="form-control" required
												name="name_car_license_plate_brand_number_of_seats"
												id="name_car_license_plate_brand_number_of_seats">
												<option value="" selected>Tên xe - biển số - loại
													xe - số ghế</option>
												<c:forEach var="car" items="${listCar}">
													<c:choose>
														<c:when test="${car.status == 'Activated'}">
															<option
																value="${car.name} - ${car.license_plate} - ${car.brand} - ${car.number_of_seats}">${car.name}
																- ${car.license_plate} - ${car.brand} -
																${car.number_of_seats}</option>
														</c:when>
													</c:choose>

												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Tên tài xế*</label> <select class="form-control"
												required name="name_driver" id="name_driver">
												<option value="" selected>Tên tài xế</option>
												<c:forEach var="driver" items="${listDriver}">
													<c:choose>
														<c:when test="${driver.status == 'Activated'}">
															<option value="${driver.name}">${driver.name}</option>
														</c:when>
													</c:choose>

												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày xuất phát*</label> <input type="date" id=""
												class="form-control" placeholder="" name="date_going"
												required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giờ xuất phát*</label> <input type="time" id=""
												class="form-control" placeholder="" name="time_going"
												required>
										</div>
									</div>
								</div>
								<div class="row">


									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày về*</label> <input type="date" id=""
												class="form-control" placeholder="" name="date_comback"
												required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giờ về*</label> <input type="time" id=""
												class="form-control" placeholder="" name="time_comback"
												required>
										</div>
									</div>

								</div>



								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tuyến hành trình*</label> <input type="text"
												class="form-control" placeholder="Nhập nơi đi - đến"
												name="route" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Trạng thái*</label> <select class="form-control"
												required name="status" id="status">
												<option value="" selected>Chọn trạng thái</option>
												<option value="Khởi tạo">Khởi tạo đơn</option>
											</select>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Điểm đón*</label> <input type="text"
												class="form-control" placeholder="Nhập địa chỉ"
												name="pick_up_at" required>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Điểm trả*</label> <input type="text"
												class="form-control" placeholder="Nhập địa chỉ"
												name="comback_at" required>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Địa chỉ KH*</label> <input type="text"
												class="form-control" placeholder="Nhập địa chỉ"
												name="address" required>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giá tiền*</label> <input type="number"
												class="form-control" placeholder="Nhập giá tiền"
												name="total_price" required>
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

