<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Chi tiết hóa đơn</title>
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


							<h4 class="card-title">Chi tiết hóa đơn</h4>


							<form method="" action="" enctype="multipart/form-data">

								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID hóa đơn</label> <input type="text"
												class="form-control" value="${bill.id}" readonly
												name="id">
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID hợp đồng</label> <input type="text"
												class="form-control" value="${bill.id_contract}" readonly
												name="id_contract">
										</div>
									</div>
									
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>ID khách hàng</label> <input type="text"
												class="form-control" value="${bill.id_client}" readonly
												name="id_client">
										</div>
									</div>
									
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Hình ảnh</label><a href="${bill.image}"> <input type="text"
												class="form-control" value="${bill.image}" readonly
												name="id"> </a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày tạo hóa đơn</label> <input type="text" id=""
												class="form-control" placeholder="" name="date"
												value="${bill.date}" readonly>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên khách hàng (doanh nghiệp)</label> <input type="text"
												class="form-control" value="${bill.name_client}"
												name="name_client" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên dịch vụ</label> <input type="text"
												class="form-control" placeholder="Nhập tên dịch vụ"
												value="${bill.name_service}" name="name_service" readonly>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Số điện thoại KH</label> <input type="number"
												class="form-control" value="${bill.phone_client}"
												name="phone_client" readonly>
										</div>
									</div>
								</div>
								<div class="row"></div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Mã số thuế</label> <input type="number"
												class="form-control" placeholder="" value="${bill.tax_code}"
												name="tax_code" readonly>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Địa chỉ KH</label> <input type="text" id=""
												class="form-control" placeholder="Nhập địa chỉ"
												value="${bill.address}" name="address" readonly>
										</div>
									</div>
								</div>


								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Hình thức thanh toán</label> <select
												class="form-control" readonly name="payment" id="payment">
												<option value="${bill.payment}" selected>${bill.payment}</option>
												<option value="Tiền mặt">Tiền mặt</option>
												<option value="Chuyển khoản">Chuyển khoản</option>
											</select>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Số tài khoản</label> <input type="number"
												class="form-control" placeholder="Nhập số tài khoản"
												value="${bill.bank_account_number}"
												name="bank_account_number" readonly>
										</div>
									</div>
								</div>

								<div class="row">

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Đơn giá trước chiết khấu</label> <input type="number"
												class="form-control" placeholder=""
												value="${bill.price_bf_discount}" name="price_bf_discount"
												readonly>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tiền chiết khấu</label> <input type="number"
												class="form-control" placeholder="Nhập tiền chiết khấu"
												value="${bill.discount}" name="discount" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Thuế suất</label> <input type="number"
												class="form-control" placeholder="Nhập thuế suất"
												value="${bill.tax_percent}" name="tax_percent" readonly>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tiền thuế</label> <input type="text"
												class="form-control" placeholder="Nhập tiền thuế"
												value="${bill.tax_money}" name="tax_money" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Thành tiền trước thuế</label> <input type="number"
												class="form-control"
												placeholder="Nhập thành tiền trước thuế" name="price_bf_tax"
												value="${bill.price_bf_tax}" readonly>
										</div>
									</div>

									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tổng tiền thanh toán</label> <input type="number"
												class="form-control" placeholder="Nhập tổng tiền thanh toán"
												name="total_price" value="${bill.total_price}" readonly>
										</div>
									</div>
								</div>
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

