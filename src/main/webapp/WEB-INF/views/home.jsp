<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/setting.jsp" />
<script src="resources/js/home.js"></script>
<title>SMART DIARY - Home</title>
</head>
<body class="home">
	<!-- SLIDER START -->
	<section class="slider-area">
		<div class="slider-bg-img" style="background-image: url(resources/img/home-bg.jpg);">
			<a href="diary"></a>
			<div class="title">
				<img src="resources/img/title.png" />
			</div>
			<div class="btnGroup">
				<a href="#" id="loginBtn" data-toggle="modal" data-target="#loginModal">LOGIN</a>
				&nbsp;&nbsp;&nbsp;
				<a href="#" data-toggle="modal" data-target="#signUpModal">SIGN UP</a>
			</div>
		</div>
		<a href="#header" class="slider-down"><i class="fa fa-angle-down bounce"></i></a>
	</section>
	<!-- SLIDER END -->

	<header class="header" id="header">
		<section class="container">
			<!-- LOGO START -->
			<a href="/smart" class="site-logo"> <img alt="Logo"
				src="resources/img/logo.png">
			</a>
			<!-- LOGO END -->

			<!-- NAV MENU START -->
			<nav class="navbar">
				<!-- MOBILE MENU START -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
				</div>
				<!-- MOBILE MENU END -->
				<!-- MENU LIST START -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="#" id="loginBtn" data-toggle="modal"
							data-target="#loginModal">Login</a></li>
						<li><a href="#" data-toggle="modal" data-target="#signUpModal">Sign
								Up</a></li>
						<li><a href="howTo">How To</a></li>
						<li><a href="aboutUS">About Us</a></li>
					</ul>
				</div>
				<!-- MENU LIST END -->
			</nav>
			<!-- NAV MENU END -->
		</section>
	</header>

	<!-- SITE CONTENT START -->
	<section class="site-content-wrapper">
		<!-- FULL GRID CONTENT START -->
		<section class="full-grid-content">
			<!-- BLOG LIST ITEM START -->
			<article style="background-image: url(resources/img/news1.jpg)">
				<a href="javascript:;">
					<section class="container">
						<p class="sectionIcon">
							<i class="fa fa-calendar-check-o" aria-hidden="true"></i>
						</p>
						<div class="full-grid-item">
							<div class="post-list-information">
								<span class="category">Point 1</span>
							</div>
							<div class="full-grid-title">
								<h2>Schedule that can be checked at a glance</h2>
							</div>
							<p>
								You can display all schedules on one screen via the calendar, <br>you
								can also check the detailed schedule of the day.
							</p>
						</div>
					</section>
				</a>
			</article>
			<!-- BLOG LIST ITEM END -->
			<!-- BLOG LIST ITEM START -->
			<article style="background-image: url(resources/img/news2.jpg)">
				<a href="javascript:;">
					<section class="container">
						<p class="sectionIcon">
							<i class="fa fa-commenting-o" aria-hidden="true"></i>
						</p>
						<div class="full-grid-item">
							<div class="post-list-information">
								<span class="category">Point 2</span>
							</div>
							<div class="full-grid-title">
								<h2>Provide by entering simple text</h2>
							</div>
							<p>
								After entering the schedule, we provide various information and services<br>
								about schedule contents.
							</p>
						</div>
					</section>
				</a>
			</article>
			<!-- BLOG LIST ITEM END -->
			<!-- BLOG LIST ITEM START -->
			<article style="background-image: url(resources/img/news3.jpg)">
				<a href="javascript:;">
					<section class="container">
						<p class="sectionIcon">
							<i class="fa fa-share-alt" aria-hidden="true"></i>
						</p>
						<div class="full-grid-item">
							<div class="post-list-information">
								<span class="category">Point 3</span>
							</div>
							<div class="full-grid-title">
								<h2>Sharing schedule</h2>
							</div>
							<p>Share your schedule with friends, family and colleagues.</p>
						</div>
					</section>
				</a>
			</article>
			<!-- BLOG LIST ITEM END -->
		</section>
		<!-- FULL GRID CONTENT END -->
	</section>
	<!-- SITE CONTENT END -->
	<jsp:include page="layout/memberModal.jsp" />
	<jsp:include page="layout/footer.jsp" />
</body>
</html>
