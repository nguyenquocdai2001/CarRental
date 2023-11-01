<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Chỉnh sửa khoản chi xe</title>
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


							<h4 class="card-title">Chỉnh sửa khoản chi xe</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/edit-carPayOut/${carPayOut.id}/edit"
								enctype="multipart/form-data">
								<div class="row">
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Ngày tạo*</label> <input type="text" id=""
												class="form-control" placeholder="" name="date" value="${carPayOut.date}"
												required>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label>Tên xe*</label> <select class="form-control"
												required name="name_car" id="name_car">
												<option value="${carPayOut.name_car}" selected>${carPayOut.name_car}</option>
												<c:forEach var="car" items="${listCar}">
													<c:choose>
														<c:when test="${car.status == 'Activated'}">
															<option value="${car.name}">${car.name}</option>
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
											<label>Số tiền*</label> <input type="number"
												class="form-control" placeholder="" value="${carPayOut.money}"
												name="money" required>
										</div>
									</div>
									<div class="col-md-6 pr-1">
										<div class="form-group">
											<label>Lý do*</label> <input type="text"
												class="form-control" placeholder="" value="${carPayOut.reason}"
												name="reason" required>
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

