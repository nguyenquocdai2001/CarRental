<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Tìm kiếm khoản chi tài xế</title>
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


							<h4 class="card-title">Tìm kiếm khoản chi tài xế</h4>


							<form method="POST"
								action="${pageContext.request.contextPath}/search-driverPayOut-post"
								enctype="multipart/form-data">

								<div class="row">
									<div class="col-md-12 pr-1">
										<div class="form-group">
											<label>Tháng/Năm (MM/YYYY)</label> <input type="text"
												class="form-control" placeholder="Nhập MM/YYYY" value="" required
												name="date">
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

