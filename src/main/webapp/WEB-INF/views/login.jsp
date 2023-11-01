<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Login Page</title>
<%@ include file="/link/link.jsp"%>

</head>
<body>
	<div class="wrapper">
		<%@ include file="/partial/sidebar.jsp"%>
		<div class="main-panel">
			<%@ include file="/partial/header.jsp"%>
			<div class="container" style="margin-top: 50px; margin-bottom: 50px;">
				<div class="row bg-light"
					style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: auto auto;">
					<div class="col-sm-12">
						<header class="text-center py-3 rounded ">
							<h1 class="display-5 font-weight-bold text-secondary text-center">Sign
								in</h1>
						</header>
						<form action="${pageContext.request.contextPath}/checkLogin"
							method="POST">
							<div class="row">
								<div class="form-group col-md-12">
									<label class="text-dark ">Email</label> <input type="text"
										class="form-control" name="email" placeholder="Enter Email"
										id="email" value="">
								</div>
							</div>
							

							<div class="row ">
								<div class="form-group col-md-12">
									<label class="text-dark ">Password</label> <input
										type="password" class="form-control" name="password"
										placeholder="Enter Password">
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
								<button type="submit" class="btn btn-primary">Login</button>
								<button type="reset" class="btn btn-secondary">Cancel</button>
								<p class="mt-3">
									Don't have an account? <a href="./register">Sign up</a>
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
