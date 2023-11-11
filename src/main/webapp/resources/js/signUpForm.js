function validationCheck() {
	
	var id = $("#user_id");
	var user_pw = $("#user_pw");
	var re_user_pw = $("#re_user_pw");
	var name = $("#user_nm");
	var birth = $("#user_birth");
	var phone = $("#user_phone");
	var address1 = $("#user_add1");
	
	if (id.val() == "") {
		alert("아이디를 입력하세요");
		id.focus();
		return false;
	} else if (user_pw.val() == "") {
		alert("비밀번호를 입력하세요");
		user_pw.focus();
		return false;
	} else if (re_user_pw.val() == "") {
		alert("비밀번호 확인을 입력하세요");
		re_user_pw.focus();
		return false;
	} else if (user_pw.val() != re_user_pw.val()) {
		alert("비밀번호가 일치하지 않습니다. 다시 입력하세요");
		return false;
	} else if (name.val() == "") {
		alert("이름을 입력하세요");
		name.focus();
		return false;
	} else if (birth.val() == "") {
		alert("주민등록번호 앞자리를 입력하세요");
		birth.focus();
		return false;
	} else if (phone.val() == "") {
		alert("휴대전화 번호를 입력하세요");
		phone.focus();
		return false;
	} else if (address1.val() == "") {
		alert("주소를 입력하세요");
		address1.focus();
		return false;
	}
	return true;
}

$(document).ready(function() {
	
	$("#user_id").keyup(function() {

		var user_id = $("#user_id").val();

		$.ajax({
			type : "POST",
			url : "selectMember",
			data : {
				user_id : user_id
			},
			dataType : "json",
			success : function(data) {
				console.log("data");
				if (data.user_id == null) {
					$("#idLabel").text("* 사용할 수 있는 ID입니다");
				} else {
					$("#idLabel").text("* 사용할 수 없는 ID입니다");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	});
});

function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullAddr = ''; // 최종 주소 변수
			var extraAddr = ''; // 조합형 주소 변수

			// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				fullAddr = data.roadAddress;

			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				fullAddr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			if (data.userSelectedType === 'R') {
				// 법정동명이 있을 경우 추가한다.
				if (data.bname !== '') {
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if (data.buildingName !== '') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName
							: data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample6_postcode').value = data.zonecode; // 5자리
			// 새우편번호
			// 사용
			document.getElementById('user_add1').value = fullAddr;

		}
	}).open();
}
