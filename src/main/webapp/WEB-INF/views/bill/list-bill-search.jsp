<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Danh sách hóa đơn</title>

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
									<h4 class="card-title">DANH SÁCH HÓA ĐƠN</h4>
									<p class="card-category text-danger">Tổng doanh thu: <c:out value="${totalPriceSearch}" /></p>

								</div>
								<div class="card-body table-full-width table-responsive">
									<table class="table table-hover " id="bill">
										<thead>
											<th>Tên khách hàng</th>
											<th>SĐT</th>
											<th>Tên dịch vụ</th>
											<th>Mã số thuế</th>
											<th>Địa chỉ</th>
											<th>thanh toán</th>
											<th>STK</th>
											<th>Tiền trước thuế</th>
											<th>Tổng tiền</th>
											<th>Ngày tạo</th>
											<th>Detail</th>
										</thead>
										<tbody>
											<c:forEach var="bill" items="${listBillSearch}" varStatus="loop">
												<tr>

													<td>${bill.name_client}</td>
													<td>${bill.phone_client}</td>
													<td>${bill.name_service}</td>
													<td>${bill.tax_code}</td>
													<td>${bill.address}</td>
													<td>${bill.payment}</td>
													<td>${bill.bank_account_number}</td>
													<td>${bill.price_bf_tax}</td>
													<td>${bill.total_price}</td>
													<td>${bill.date}</td>
													<td> 
															<a
																href="${pageContext.request.contextPath}/edit-bill/${bill.id}">
																Edit</a>
															<a
																href="${pageContext.request.contextPath}/details-bill/${bill.id}">
																	|	Detail</a>

														</td>

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

	</div>
</body>
<%@ include file="/link/script.jsp"%>
</html>
