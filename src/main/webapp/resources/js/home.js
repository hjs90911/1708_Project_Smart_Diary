$(document).ready(function () {
	getMovieList();
});

function getMovieList() {
	var html = "";
	$.ajax({
		type : "get",
		url : "getmovie",
		data : {	
		},
		success : function(data) {
			html += '<form>';
			html += '<select multiple id="movieselect">';
			 $.each(data, function (i,item) {
	             html += '<option>' + item + '</option>';
	         });
			 html +='</select>';
			 html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="setmovie();return false;">';
			 html +='</form>';
			 $("#loginModal #movieList").val(html);
			 console.log(html);
		},
		error : function(request,status,error) {
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}


function loginCheck() {
	var id = $("#id").val();
	var pw = $("#pw").val();
	
	if (id == "") {
		alert("ID를 입력하세요");
	} else if (pw == "") {
		alert("비밀번호를 입력하세요")
	} else {
		$.ajax({
			type : "post",
			url : "login",
			data : {
				"id" : id,
				"pw" : pw,
				movieList : $("#movieList").val()
			},
			success : function (data) {
				switch (data) {
				case 0:
					location.href = "/smart/diary";
					break;
				case 1:
					alert("존재하지 않는 회원입니다.");
					break;
				case 2:
					alert("비밀 번호가 일치하지 않습니다.");
					break;
				case 3:
					alert("메일 인증이 되지 않는 회원입니다.");
					break;
				}
			},
			error : function (e) {
				console.log(e);
			}
		});
	}
	
	return false;
}