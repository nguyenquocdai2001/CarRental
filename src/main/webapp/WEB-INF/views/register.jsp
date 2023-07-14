<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Register Page</title>
<%@ include file="/link/link.jsp"%>

</head>
<body>
	<div class="wrapper">
		<%@ include file="/partial/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/partial/header.jsp"%>
			<div class="container" style="margin-top: 50px; margin-bottom: 50px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Sign
								up</h1>
						</header>
						<form action="${pageContext.request.contextPath}/checkRegister"
							method="POST">
							<div class="row">
								<div class="form-group col-md-4">
									<label class="text-dark ">Email</label> <input type="text"
										class="form-control is-valid" name="email" placeholder="Enter Email"
										id="email" value="${check.email}">
								</div>
								<div class="form-group col-md-4">
									<label class="text-dark ">Password</label> <input
										type="password" class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>" name="password" placeholder="Enter password"
										value="${check.password}">
								</div>
								<div class="form-group col-md-4">
									<label class="text-dark ">Confirm password</label> <input
										type="password" class="form-control <c:if test='${empty check.password}'> ${valid} </c:if>" name="confirmPassword"
										placeholder="Enter Confirm password" id="confirmPassword"
										value="${check.password}">
								</div>
							</div>
							

							<div class="row ">
								<div class="form-group col-md-6">
									<label class="text-dark ">Tax code</label> <input type="text"
										class="form-control <c:if test='${empty check2.tax_code}'> ${valid} </c:if>" name="tax_code"
										placeholder="Enter tax code" id="address" value="${check2.tax_code}">
								</div>

								<div class="form-group col-md-6">
									<label class="text-dark ">Phone</label> <input type="number"
										class="form-control <c:if test='${empty check2.phone}'> ${valid} </c:if>" name="phone" placeholder="Enter Phone"
										id="phone" value="${check2.phone}">
								</div>

							</div>

							<div class="row">
								<div class="form-group col-md-6">
									<label class="text-dark ">Address</label> <input type="text"
										class="form-control <c:if test='${empty check2.address}'> ${valid} </c:if>" name="address"
										placeholder="Enter Address" id="address" value="${check2.address}">
								</div>
								
								<div class="form-group col-md-6">
									<label class="text-dark ">Fullname</label> <input type="text"
										class="form-control <c:if test='${empty check2.name}'> ${valid} </c:if>" name="name" placeholder="Enter Fullname"
										id="name" value="${check2.name}">
								</div>
								
							</div>
							<c:if test='${not empty message}'>
								<div class="alert alert-${message.type} alert-dismissible show"
									role="alert">
									${message.content}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							</c:if>

							<div class="form-group">
								<button type="submit" class="btn btn-success">Create</button>
								<button type="reset" class="btn btn-secondary">Cancel</button>
								<p class="mt-3">
									Already have an account? <a href="./login">Sign in</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/partial/footer.jsp"%>
	</div>
</body>
<%@ include file="/link/script.jsp"%>
</html>
