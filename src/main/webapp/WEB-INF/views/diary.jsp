<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="layout/setting.jsp" />
<title>MY DIARY</title>
<script>
$(document).ready(function() {
	if(${mvset != 0}) {
		mvSetting();
	}
	$("#movieListValue").val('${movieList}');
});
</script>
<script src="resources/js/calendarForm.js"></script>
<script src="resources/js/signUpForm.js"></script>
</head>
<body>
	<div class="loader-wrapper">
		<div class="loader"></div>
		<div class="loader-section section-left">
		</div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- scheduleList GET -->
	<input type="hidden" id="scheduleList" value='${scheduleList}' />
	<input type="hidden" id="movieListValue" value='' />
	<header class="header fixed-header navbar-fixed-top" id="calHeader">
		<section class="container">
			<!-- LOGO START -->
			<a href="diary" class="site-logo">
				<img alt="Logo"	src="resources/img/logo.png">
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
						<li>
							<a href="#" id="myPageBtn" data-toggle="modal" data-target="#myPageModal">MY PAGE</a>
						</li>
						<li>
							<a href="#" id="myFriendListBtn" data-toggle="modal" data-target="#myFriendListModal">
								<div>
									<span class="requestNum"></span> MY FRIEND
								</div>
							</a>
						</li>
						<li><a href="logout">LOGOUT</a></li>
						<li><a href="howTo">HOW TO</a></li>
						<li><a href="aboutUS">ABOUT US</a></li>
					</ul>
				</div>
				<!-- MENU LIST END -->
			</nav>
			<!-- NAV MENU END -->
		</section>
	</header>

	<a href="javascript:showHeader()" class="slider-down"><i
		class="fa fa-angle-down"></i></a>
	<!-- calendar part -->
	<div class="container calendar">
		<section class="slider-area">
			<section id="carousel-example-generic" class="carousel slide" data-wrap="false"
				data-interval="false" data-ride="carousel">
				<!-- SLIDE LIST START -->
				<div class="carousel-inner" role="listbox">
					<!-- SLIDE LIST ITEM START -->
					<div class="item active">
						<!-- SLIDE BACKGROUND START -->
						<div class="slider-bg-img"
							style="background-image: url(resources/img/diary-bg.jpg);"></div>
						<!-- SLIDE BACKGROUND END -->
						<!-- SLIDE CAPTION START -->
						<div class="carousel-caption">
							<div class="col-xs-offset-1 col-xs-10 iconList">
								<a href="javascript:movie();">
									<i class="fa fa-film fa-3x" aria-hidden="true"></i>
								</a>
								<a href="javascript:bus();">
									<i class="fa fa-bus fa-3x" aria-hidden="true"></i>
								</a>
								<a href="javascript:train();">
									<i class="fa fa-train fa-3x" aria-hidden="true"></i>
								</a>
								<a href="javascript:restaurant();">
									<i class="fa fa-cutlery fa-3x" aria-hidden="true"></i>
								</a>
								<a href="javascript:write();">
									<i class="fa fa-keyboard-o fa-3x"	aria-hidden="true"></i>
								</a>
								<!-- <input type="text" class="form-control" id="write"><br> <span id="written"></span> -->
							</div>
							<br />
							<div class="form-group col-xs-offset-1 col-xs-10 textBlock">
								<span class="tooltiptext"></span>
								<!--<button class="btn btn-default" id="scheduleSearch">스케쥴 검색</button>-->
								<input type="text" class="form-control write">
								<br>
								<i class="fa fa-users fa-1x friendList" aria-hidden="true"></i>
								<span class="written"></span>
								<a href="javascript:dateInitialize();"><i class="fa fa-times fa-1x cancel" aria-hidden="true"></i></a>
							</div>
							<br /> <br />
							<div class="text-center">
								<h1>
									<span id="year"></span>.<span id="month"></span>
								</h1>
							</div>

							<div id="calendar" class="animated fadeInUpBig"></div>
						</div>
						<!-- SLIDE CAPTION END -->
					</div>
				</div>
				<!-- SLIDE LIST END -->
				<!-- SLIDER CONTROL START -->
				<a class="left carousel-control left-slider-arrow" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control right-slider-arrow" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
				<!-- SLIDER CONTROL END -->
			</section>
		</section>
	</div>
	<jsp:include page="layout/memberModal.jsp" />
	<jsp:include page="layout/diaryModal.jsp" />
</body>
</html>
