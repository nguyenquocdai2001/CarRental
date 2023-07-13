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
			<li class="active"><a href=""> <i class="pe-7s-graph"></i>
					<p>Trang chủ</p>
			</a></li>
			<li><a href="${pageContext.request.contextPath}/list-car"> <i
					class="pe-7s-user"></i>
					<p>Xe</p>
			</a></li>
			<li><a href="table.html"> <i class="pe-7s-note2"></i>
					<p>Table List</p>
			</a></li>
			<li><a href="typography.html"> <i class="pe-7s-news-paper"></i>
					<p>Typography</p>
			</a></li>
			<li><a href="icons.html"> <i class="pe-7s-science"></i>
					<p>Icons</p>
			</a></li>
			<li><a href="maps.html"> <i class="pe-7s-map-marker"></i>
					<p>Maps</p>
			</a></li>
			<li><a href="notifications.html"> <i class="pe-7s-bell"></i>
					<p>Notifications</p>
			</a></li>
			<li class="active-pro"><a href="upgrade.html"> <i
					class="pe-7s-rocket"></i>
					<p>Upgrade to PRO</p>
			</a></li>
		</ul>
	</div>
</div>