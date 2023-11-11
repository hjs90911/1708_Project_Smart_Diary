<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header class="header" id="header">
	<section class="container">
		<!-- LOGO START -->
		<a href="/smart" class="site-logo"><img alt="Logo"	src="resources/img/logo.png"></a>
		<!-- LOGO END -->

		<!-- NAV MENU START -->
		<nav class="navbar">
			<!-- MOBILE MENU START -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<!-- MOBILE MENU END -->
			<!-- MENU LIST START -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				<c:if test="${sessionScope.user_id == null}">
					<li><a href="#" id="loginBtn" data-toggle="modal" data-target="#loginModal">Login</a></li>
					<li><a href="#" data-toggle="modal" data-target="#signUpModal">Sign Up</a></li>
				</c:if>
				<c:if test="${sessionScope.user_id != null}">
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
				</c:if>
					<li><a href="howTo">How To</a></li>
					<li><a href="aboutUS">About Us</a></li>
				</ul>
			</div>
			<!-- MENU LIST END -->
		</nav>
		<!-- NAV MENU END -->
	</section>
</header>
