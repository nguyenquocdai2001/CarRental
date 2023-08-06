<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Chỉnh sửa khách hàng</title>
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


							<h4 class="card-title">Chỉnh sửa khách hàng</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/edit-client/${client.id}/edit"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Tên khách hàng</label> <input type="text"
												class="form-control" value="${client.name}"
												placeholder="" name="name" readonly>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Email</label> <input type="text" class="form-control"
												placeholder="" value="${client.email}" name="email"
												readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Địa chỉ</label> <input type="text"
												class="form-control" placeholder=""
												name="address" value="${client.address}" readonly>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Số điện thoại</label> <input type="number"
												class="form-control" placeholder=""
												value="${client.phone}" name="phone" readonly>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Trạng thái</label> <select class="form-control"
												required name="status" id="status">
												<option value="${client.status}" selected>${client.status}</option>
												<option value="Activated">Activated</option>
												<option value="Canceled">Canceled</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Mã số thuế</label> <input type="number"
												class="form-control" placeholder=""
												value="${client.tax_code}" name="tax_code" readonly>
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

