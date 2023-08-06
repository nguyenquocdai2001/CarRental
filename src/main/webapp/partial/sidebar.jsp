<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="sidebar" data-color="purple"
	data-image="assets/img/sidebar-5.jpg">

	<!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

	<div class="sidebar-wrapper">
		<div class="logo">
			<a href="http://www.creative-tim.com" class="simple-text"> Thy Ân
			</a>
		</div>

		<ul class="nav">
			<c:set var="userRole" value="${userRole}" />

			<c:if test="${userRole == 'admin'}">
				<li class=""><a
					href="${pageContext.request.contextPath}/list-client"> <i
						class="pe-7s-graph"></i>
						<p>Khách hàng</p>
				</a></li>
				<li><a href="${pageContext.request.contextPath}/list-car">
						<i class="pe-7s-user"></i>
						<p>Xe</p>
				</a></li>
				<li><a href="${pageContext.request.contextPath}/list-driver">
						<i class="pe-7s-note2"></i>
						<p>Tài xế</p>
				</a></li>
				<li><a href="${pageContext.request.contextPath}/list-preOrder">
						<i class="pe-7s-news-paper"></i>
						<p>Đơn đặt trước</p>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/list-preOrder-start">
						<i class="pe-7s-science"></i>
						<p>Đơn đặt trước (khởi tạo)</p>
				</a></li>
				<li><a href="${pageContext.request.contextPath}/list-contract">
						<i class="pe-7s-news-paper"></i>
						<p>Hợp đồng</p>
				</a></li>
				<li><a href="${pageContext.request.contextPath}/list-bill"> <i class="pe-7s-bell"></i>
						<p>Hóa đơn</p>
				</a></li>
				<!-- <li class="active-pro"><a href="upgrade.html"> <i
						class="pe-7s-rocket"></i>
						<p>Upgrade to PRO</p>
				</a></li> -->
			</c:if>
		</ul>
	</div>
</div>