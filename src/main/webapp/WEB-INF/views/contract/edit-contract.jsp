<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Chỉnh sửa hợp đồng</title>
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


							<h4 class="card-title">Chỉnh sửa hợp đồng</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/edit-contract/${contract.id}/edit"
								enctype="multipart/form-data">
								
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID khách hàng</label> <input type="text" class="form-control"
												value="${contract.id_client}" readonly name="id_client">
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID đơn đặt trước</label> <input type="text" class="form-control"
												value="${contract.id_pre_order}" readonly name="id_pre_order">
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID hợp đồng</label> <input type="text" class="form-control"
												value="${contract.id}" readonly name="id">
										</div>
									</div>
									
									<div class="col-md-6 px-1">
										<div class="form-group">
											<label for="file">Hình ảnh</label> <input type="hidden"
												name="image" value="${contract.image}" /> <input
												class="form-control" type="file" id="file" name="file"
												value="${pageContext.request.contextPath}/template/admin/upload/${contract.image}" />
										</div>
									</div>

								</div>
								<%-- <div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên doanh nghiệp</label> <input type="text"
												class="form-control" placeholder="Nhập tên doanh nghiệp"
												value="${contract.name_agency}" name="name_agency">
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Chức vụ</label> <input type="text"
												class="form-control" placeholder="Nhập chức vụ" value="${contract.position_agency}"
												name="position_agency">
										</div>
									</div>
								</div> --%>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên khách hàng (doanh nghiệp)*</label> <input type="text"
												class="form-control" value="${contract.name_client}"
												name="name_client" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Số điện thoại KH*</label> <input type="number"
												class="form-control" value="${contract.phone}"
												name="phone" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Tên xe*</label> <select class="form-control" required
												name="name_car_license_plate_brand_number_of_seats"
												id="name_car_license_plate_brand_number_of_seats">
												<option
													value="${contract.name_car_license_plate_brand_number_of_seats}"
													selected>${contract.name_car_license_plate_brand_number_of_seats}</option>
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
												<option value="${contract.name_driver}" selected>${contract.name_driver}</option>
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
											<label>Mã số thuế</label> <input type="number"
												class="form-control" placeholder="Nhập mã số thuế"
												name="tax_code" value="${contract.tax_code}" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Địa chỉ KH*</label> <input type="text" id=""
												class="form-control" placeholder="Nhập địa chỉ"
												value="${contract.address}" name="address" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày xuất phát*</label> <input type="text" id=""
												class="form-control" placeholder="" name="date_going"
												value="${contract.date_going}" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giờ xuất phát*</label> <input type="text" id=""
												class="form-control" placeholder="" name="time_going"
												value="${contract.time_going}" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày về*</label> <input type="text" id=""
												class="form-control" placeholder="" name="date_comback"
												value="${contract.date_comback}" required>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giờ về*</label> <input type="text" class="form-control"
												placeholder="" value="${contract.time_comback}"
												name="time_comback" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tiền bồi thường</label> <input type="number" value="${contract.indemnification}"
												class="form-control" placeholder="Nhập tiền bồi thường"
												name="indemnification">
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Chi phí khác</label> <input type="number"
												class="form-control" placeholder="Nhập số tiền" value="${contract.another_fee}"
												name="another_fee">
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tuyến hành trình*</label> <input type="text"
												class="form-control" placeholder=""
												value="${contract.route}" name="route" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Điểm đón*</label> <input type="text"
												class="form-control" placeholder=""
												value="${contract.pick_up_at}" name="pick_up_at" required>
										</div>
									</div>
								</div>

								<div class="row">

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Giá tiền*</label> <input type="number"
												class="form-control" placeholder="Nhập giá tiền"
												value="${contract.total_price}" name="total_price" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tiền trả trước*</label> <input type="number"
												class="form-control" placeholder="Nhập giá tiền" value="${contract.pre_paid_price}"
												name="pre_paid_price" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Trạng thái*</label> <select class="form-control"
												required name="status" id="status">
												<option value="${contract.status}" selected>${contract.status}</option>
												<option value="Hết hiệu lực">Hết hiệu lực</option>
											</select>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Điểm trả*</label> <input type="text"
												class="form-control" placeholder=""
												value="${contract.comback_at}" name="comback_at" required>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label for="">Danh sách hành khách*</label>
											<textarea class="form-control" name="list_customer" required>${contract.list_customer}</textarea>
										</div>
									</div>
									
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Hình ảnh</label><a href="${contract.image}"> <input type="text"
												class="form-control" value="${contract.image}" readonly
												name="id"> </a>
										</div>
									</div>
								</div>
								<input type="submit" class="btn btn-info btn-fill pull-right">
								<div class="clearfix"></div>
								<div class="col-md-6 pr-1 invisible">
										<div class="form-group">
											<label>Id xe*</label> <input type="text"
												class="form-control" placeholder=""
												value="${contract.id_car}" name="id_car" required>
										</div>
									</div>
								
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

