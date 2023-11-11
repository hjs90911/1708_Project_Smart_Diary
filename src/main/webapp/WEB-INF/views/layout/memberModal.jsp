<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.user_id == null}">
<!-- LOGIN MODAL START -->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<img src='resources/img/icon/login.png' width='30px' height='30px'>
				</h4>
			</div>
			<div class="modal-body">
				<form onsubmit="return loginCheck();">
					<input type="hidden" id="movieList" name="movieList" value='' />
					<div class="form-group">
						<label for="id">ID</label> <input type="text" class="form-control" id="id"
							name="id">
					</div>
					<div class="form-group">
						<label for="pw">Password</label> <input type="password" class="form-control"
							id="pw" name="pw">
					</div>
					<div class="form-group">
						<input type="submit" class="form-control" value="LOGIN" />
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- LOGIN MODAL END -->
<!-- SIGNUP MODAL START -->
<div class="modal fade" id="signUpModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<img src='resources/img/icon/link.png' width='30px' height='30px'>
				</h4>
			</div>
			<div class="modal-body">
				<form action="signUp" method="post" onsubmit="return validationCheck()">
					<div class="form-group">
						<div id="idLabel"></div>
						<br> <input type="text" class="form-control" name="user_id" id="user_id"
							placeholder="emailID">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="user_pw" id="user_pw"
							placeholder="password">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="re_user_pw"
							placeholder="password confirm">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_nm" id="user_nm"
							placeholder="name">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_birth" id="user_birth"
							placeholder="birthdate(yymmdd)">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_phone" id="user_phone"
							placeholder="cellphone number">
					</div>
					<div class="form-group form-inline">
						<input type="text" class="form-control" id="sample6_postcode"
							placeholder="postcode" readonly="readonly"> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					</div>
					<div class="form-group">
						<input type="text" name="user_add1" id="user_add1" class="form-control"
							placeholder="address"><br> <input type="submit"
							class="form-control" id="joinBtn" value="SIGNUP">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- SIGNUP MODAL END -->
</c:if>
<c:if test="${sessionScope.user_id != null}">
<!-- MYPAGE MODAL START -->
<div class="modal fade" id="myPageModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<img src='resources/img/icon/menu.png' width='30px' height='30px'>
				</h4>
			</div>
			<div class="modal-body">
				<form onsubmit="return updateInfo();">
					<div class="form-group">
						<input type="text" class="form-control" name="user_id" id="user_id"
							placeholder="emailID" readonly="readonly" value="${member.user_id}">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="user_pw" id="user_pw"
							placeholder="password">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="re_user_pw"
							placeholder="password confirm">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_nm" id="user_nm"
							placeholder="name" value="${member.user_nm}">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_birth" id="user_birth"
							placeholder="birthDate(yyyymmdd)" readonly="readonly"
							value="${member.user_birth}">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" name="user_phone" id="user_phone"
							placeholder="cellphone number" value="${member.user_phone}">
					</div>
					<div class="form-group form-inline">
						<input type="text" class="form-control" id="sample6_postcode"
							placeholder="postcode" readonly="readonly"> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					</div>
					<div class="form-group">
						<input type="text" name="user_add1" id="user_add1" class="form-control"
							placeholder="address" value="${member.user_add1}"><br> <input
							type="submit" class="form-control" id="joinBtn" value="CONFIRM">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- MYPAGE MODAL END -->
<!-- MYFRIENDLIST MODAL START -->
<div class="modal fade" id="myFriendListModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<img src='resources/img/icon/friend.png' width='30px' height='30px'>
				</h4>
			</div>
			<div class="modal-body">
				<div class="requestListDiv">
					<table class="table borderless">
					</table>
				</div>
				<h4>친구의 이름을 입력하세요</h4>
				<div class="form-group">
					<input type="text" class="form-control" id="friend_nm" placeholder="name">
				</div>
				<div class="friendListDiv">
					<table class="table borderless">
						<tr>
							<th>이름</th>
							<th>생년월일</th>
							<th></th>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MYFRIENDLIST MODAL END -->
</c:if>