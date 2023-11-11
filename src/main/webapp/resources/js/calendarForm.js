var dt = new Date(); // Date 객체
var today = new Date(); // 오늘 날짜 객체
var tooltiptext; // tooltiptext 배열
var process; // 선택된 icon의 절차 배열
var i = 0;
var selectMemberList; // 전체 유저 목록
var selectedFriendList = new Array(); // 선택된 친구 목록
var scheduleList; // 전체 스케쥴 목록
var writeInput = "";  //직접입력시 원본텍스트
var movieflag = 0; 
var dateNow = "";
var start = "";
var dest = "";
var timeflag=false;
var map = new Map();
var busGrade;
var full_seat = 0;
var check_seat = 0;
var seatList = new Array();
var flag = 0;
var busReserveInfo;
var ktxinfos;
var starttime;
var selectBusInfo = "";
var mvnamecancel = new Array();
var mvtimecancel = new Array();
var kobuscancel = new Array();
var traincancel = new Array();
var mvscno = new Array();
var ccscno = new Array();
var kobusscno = new Array();
var easybusscno = new Array();
var trainscno = new Array();
var defaultscno = new Array();
var foodInput = new Array();
var returnData;
var tasteInfo = "";
var mvPaymentInfo = "";
var busPaymentInfo = "";
var ktxPaymentInfo = "";
var inputparam2="";

// 회원 정보 수정 처리
function updateInfo() {
	if ($("#user_pw").val() == "") {
		alert("비밀번호를 입력하세요");
	} else if ($("#re_user_pw").val() == "") {
		alert("비밀번호 확인을 입력하세요");
	} else if ($("#user_pw").val() != $("#re_user_pw").val()) {
		alert("비밀번호가 일치하지 않습니다. 다시 입력하세요");
	} else if ($("#user_nm").val() == "") {
		alert("이름을 입력하세요");
	} else if ($("#user_phone").val() == "") {
		alert("전화번호를 입력하세요");
	} else if ($("#user_add1").val() == "") {
		alert("주소를 입력하세요");
	} else {
		$.ajax({
			type : "post",
			url : "updateInfo",
			data : {
				"user_pw" : $("#user_pw").val(),
				"user_nm" : $("#user_nm").val(),
				"user_phone" : $("#user_phone").val(),
				"user_add1" : $("#user_add1").val()
			},
			success : function (data) {
				alert("회원 정보가 수정되었습니다.");
				$("#myPageModal").modal("hide");
			},
			error : function (e) {
				console.log(e);
			}
		});
	}
	
	return false;
}

$(document).ready(function () {
	// diary 접속 시 필요한 서버 요청
	sendRequest();
	// 달력 출력
	$("#calendar").html(calendarHtml(dt));
	// 달력 아이디 바꿔주기
	$("#calendar").attr("id", today.getFullYear() + "-" + (today.getMonth() + 1));
	// 일자에 popover 설정하기
	makePopover();
	// 각 event 활성화
	eventActive();
	
	// 아무데나 클릭 시 header 숨기기
	$(".slider-area").on("click", function () {
		hideHeader();
	});
	
	// slider-down mouseenter 시 bounce 추가
	$(".slider-down").on("mouseenter", function () {
		$(".slider-down>i").removeClass().addClass("fa fa-angle-down bounce");
	});

	// slider-down mouseleave 시 bounce 제거
	$(".slider-down").on("mouseleave", function () {
		$(".slider-down>i").removeClass().addClass("fa fa-angle-down");
	});
	
	// sliderButton 활성화
	$("a[data-slide=prev]").on("click", function () {
		lastMonth();
		dateInitialize();
	});

	$("a[data-slide=next]").on("click", function () {
		nextMonth();
		dateInitialize();
	});
});

function spinnerStart() {
	$("body").removeClass("loaded");
	$(".loader-section").css("opacity", "0");
	$(".loader-section").fadeTo("slow", "0.5");
	$(".calendar").fadeTo("slow", "0.5");
}

function spinnerEnd() {
	$(".loader-wrapper .loader-section").css("-webkit-transform", "none");
	$(".loader-wrapper .loader-section").css("-ms-transform", "none");
	$(".loader-wrapper .loader-section").css("transform", "none");
	$(".loader-section").fadeTo("slow", "1");
	$(".calendar").fadeTo("slow", "1");
	$("body").addClass("loaded");
}

function sendRequest() {
	$.ajax({
		type : "get",
		url : "getFriendList",
		async : false,
		success : function (data) {
			// 아이콘의 친구 목록 생성
			makeFriendList(data);
			
			// 처음 켰을 때 myfriend modal의 친구 목록 띄우기
			var html = "";
			$.each(data, function(key, value) {
				if (value.USER_FRNO && value.FR_FLAG == 'Y') {
					html += "<tr>";
					html += "<td>" + value.USER_NM + "</td>";
					html += "<td>" + value.USER_BIRTH + "</td>";
					html += "<td>";
					html += "<a href='javascript:deleteFriend(" + value.USER_NO_PK + ");'><i class='fa fa-user-times'></i></a>";
				}
			});
			html += "</td>";
			html += "</tr>";
			
			$(".friendListDiv>.table").html(html);
		},
		error : function (e) {
			console.log(e);
		}
	});
	
	// myfriend modal 목록 만들기
	$.ajax({
		type : "get",
		url : "getFriendRequestList",
		async : false,
		success : function (data) {
			makeRequestList(data);
			
			// 친구 요청 메세지 개수 처리 부분
			if (data.length != 0) {
				$(".requestNum").html(data.length);
			}
		},
		error : function (e) {
			console.log(e);
		}
	});
	
	// 친구 추가를 위한 유저 검색
	$("#friend_nm").on("keyup", function() {
		searchMemberList();
	});
}

function eventActive() {
	// 스케쥴 입력창 focus
	$(".write").on("focus", function () {
		$(".tooltiptext").css("visibility", "visible");
		$(".tooltiptext").css("opacity", "1");
	});

	// 스케쥴 입력창 blur
	$(".write").on("blur", function () {
		$(".tooltiptext").css("visibility", "hidden");
		$(".tooltiptext").css("opacity", "0");
	});

	// 스케쥴 입력창 enter key 동작하게 하기
	$(".write").on("keydown", function (e) {
		if (e.keyCode == 13 || e.which == 13) {
			if ($(".active .write").val() == "") {
				alert("내용을 입력하세요");
			} else {
				inputText($(".active .write").val());
			}
		}
	});
	
	// 영화 목록 가져오기
	movieList();

	$(".iconList > a:first-child").on("click", function () {
		if ($(".iconList *").hasClass("popover")) {
			$(".iconList").css("z-index", "1");
		} else {
			$(".iconList").css("z-index", "0");
		}
	});
	
	// iconList 버튼 활성화
	for (var index = 2; index < 6; index++) {
		$(".iconList > a:nth-child(" + index + ")").on("click", function () {
			if ($(".iconList *").hasClass("popover")) {
				$(".iconList").css("z-index", "1");
			} else {
				$(".iconList").css("z-index", "0");
			}
			showTextBlock();
		});
	}
}

/** ************************** Header 보이기/숨기기 *************************** */
function showHeader() {
	$("#calHeader").css("top", "0px");
}

function hideHeader() {
	$("#calHeader").css("top", "-88px");
}

/** ************************ textBlock() 보이기/숨기기 ************************* */
function showTextBlock() {
	$(".slider-area .carousel-caption").css("top", "2.5%");
	$(".iconList").css("display", "none");
	$(".textBlock").css("display", "block");
	$(".write").focus();
}

function hideTextBlock() {
	$(".slider-area .carousel-caption").css("top", "10%");
	$(".iconList").css("display", "block");
	$(".textBlock").css("display", "none");
}

/** ******************** textBlock() 밑에 입력한 값 추가하기 ********************* */
function inputText(param) {
	if (param != null) {
		var html = param + "&nbsp;/&nbsp;";
	} else {
		var html = $(".active .write").val() + "&nbsp;/&nbsp;";
	}
	$(".written").html($(".written").html() + html);

	$(".write").val("");

	if (process.length == ++i) {
		i = 0;
		$(".written").html("");
		hideTextBlock();
	} else {
		$(".tooltiptext").html(tooltiptext[i]);
		process[i]();
	}
}

/** ******************************** 친구 목록 ********************************* */
function makeFriendList(friendList) {
	// 아이콘에 친구 목록 불러오기
	var html = "";
	
	html = "<div class='list-group'>";
	$.each(friendList, function(key, value) {
		html += "<a href='javascript:;' class='list-group-item'>" + value.USER_NM + "(" + value.USER_ID + ")" + "</a>";
	});
	
	html += "</div>";
	
	// 친구 목록 popover 만들기
	$(".friendList").attr("data-toggle", "popover");
	$(".friendList").attr("title", "<img src='resources/img/icon/friends.png'>");
	$(".friendList").attr("data-content", html);
	$(".friendList").attr("data-html", "true");
	$(".friendList").attr("data-placement", "bottom");
	$(".friendList").popover();
	
	$(".friendList").on("click", function() {
		var fl = $(".friendList + div > .popover-content a");
		$(this).next().css("overflow-y", "scroll");
		$(this).next().css("height", "154px");

		$.each(fl, function (key, value) {
			if ($.inArray($(this).html().substring($(this).html().indexOf("(") + 1, $(this).html().indexOf(")")), selectedFriendList) != -1) {
				$(this).addClass("active");
			}
					
			$(this).off().on("click", function () {
				$(this).toggleClass("active");
				if ($(this).hasClass("active")) {
					// 같이 갈 친구의 아이디
					selectedFriendList.push($(this).html().substring($(this).html().indexOf("(") + 1, $(this).html().indexOf(")")));
				} else {
					var id = $(this).html().substring($(this).html().indexOf("(") + 1, $(this).html().indexOf(")"));
					var id_index = selectedFriendList.indexOf(id);
					selectedFriendList.splice(id_index, 1);
				}
			});
		});
	});
}

function makeRequestList(requestList) {
	var html = "";
	
	$.each(requestList, function (key, value) {
		html += "<tr>";
		html += "<td>" + value.USER_NM + "(" + value.USER_ID + ")" + "님의 친구 요청이 들어왔습니다." + "</td>";
		html += "<td>" + "<button>수락</button>";
		html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		html += "<button>거절</button>" + "</td>";
		html += "</tr>";
	});
	
	
	$(".requestListDiv table").html(html);
	
	$.each(requestList, function (key, value) {
		$(".requestListDiv table tr:nth-child(" + (key + 1) + ") button:nth-child(1)").on("click", function () {
			acceptFriend(key, value);
		});
		
		$(".requestListDiv table tr:nth-child(" + (key + 1) + ") button:nth-child(2)").on("click", function () {
			rejectFriend(key, value);
		});
	});
}

function acceptFriend(key, value) {
	$.ajax({
		type : "get",
		url : "acceptFriend",
		data : {
			user_no_pk : value.USER_NO_PK
		},
		async: false,
		success : function (data) {
			$(".requestListDiv table tr").eq(key).html("<td colspan='2'>" + value.USER_NM + "(" + value.USER_ID + ")" + "님의 친구 요청을 수락했습니다." + "</td>");
			
			var cnt = parseInt($(".requestNum").html());
			
			if (cnt == 1) {
				$(".requestNum").html("");
			} else {
				$(".requestNum").html(cnt - 1);
			}
			
		},
		error : function (e) {
			console.log(e);
		}
	});
	searchMemberList();
}

function rejectFriend(key, value) {
	$.ajax({
		type : "get",
		url : "rejectFriend",
		data : {
			user_no_pk : value.USER_NO_PK
		},
		async: false,
		success : function (data) {
			$(".requestListDiv table tr").eq(key).html("<td colspan='2'>" + value.USER_NM + "(" + value.USER_ID + ")" + "님의 친구 요청을 거절했습니다." + "</td>");
			
			var cnt = parseInt($(".requestNum").html());
			
			if (cnt == 1) {
				$(".requestNum").html("");
			} else {
				$(".requestNum").html(cnt - 1);
			}
		},
		error : function (e) {
			console.log(e);
		}
	});
	searchMemberList();
}

function searchMemberList() {
	var nm = $("#friend_nm").val();
	
	$.ajax({
		type : "get",
		url : "selectMemberList",
		data : {
			user_nm : nm
		},
		async : false,
		success : function(data) {
			var html = "";
			var friendArray = new Array();
			
			html += "<tr>";
			html += "<th>NAME</th>";
			html += "<th>BIRTH</th>";
			html += "<th></th>";
			html += "</tr>";
			if (nm.length == 0) {
				$.each(data, function(key, value) {
					if (value.USER_FRNO && value.FR_FLAG == 'Y') {
						html += "<tr>";
						html += "<td>" + value.USER_NM + "</td>";
						html += "<td>" + value.USER_BIRTH + "</td>";
						html += "<td>";
						friendArray.push(value);
						html += "<a href='javascript:deleteFriend(" + value.USER_NO_PK + ");'><i class='fa fa-user-times'></i></a>";
					}
				});
				html += "</td>";
				html += "</tr>";
			} else {
				$.each(data, function(key, value) {
					html += "<tr>";
					html += "<td>" + value.USER_NM + "</td>";
					html += "<td>" + value.USER_BIRTH + "</td>";
					html += "<td>";
					if (value.USER_FRNO && value.FR_FLAG == 'Y') {
						friendArray.push(value);
						html += "<a href='javascript:deleteFriend(" + value.USER_NO_PK + ");'><i class='fa fa-user-times'></i></a>";
					} else if (value.USER_FRNO && value.FR_FLAG == 'N') {
						html += "<i class='fa fa-hourglass-half' aria-hidden='true'></i>";
					} else {
						html += "<a href='javascript:addFriend(" + value.USER_NO_PK + ");'><i class='fa fa-user-plus'></i></a>";
					}
					html += "</td>";
					html += "</tr>";
				});
			}
			
			makeFriendList(friendArray);
			$(".friendListDiv>.table").html(html);
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function addFriend(user_no_pk) {
	$.ajax({
		type : "get",
		url : "addFriend",
		data : {
			user_no_pk : user_no_pk
		},
		async : false,
		success : function(data) {
			console.log("OK");
		},
		error : function(e) {
			console.log(e);
		}
	});
	searchMemberList();
}

function deleteFriend(user_no_pk) {
	$.ajax({
		type : "get",
		url : "deleteFriend",
		data : {
			user_no_pk : user_no_pk
		},
		async : false,
		success : function(data) {
			console.log("OK");
		},
		error : function(e) {
			console.log(e);
		}
	});
	searchMemberList();
}
/** ******************************** 영화 목록 ********************************* */
function movieList() {
//	/* 영화 목록 받는 부분 */
	var html = $("#movieListValue").val();
	 $(".iconList > a:nth-child(1) > i").attr("data-toggle", "popover");
	 $(".iconList > a:nth-child(1) > i").attr("title", "<img src='resources/img/icon/show.png'>");
	 $(".iconList > a:nth-child(1) > i").attr("data-content", html);
	 $(".iconList > a:nth-child(1) > i").attr("data-html", "true");
	 $(".iconList > a:nth-child(1) > i").attr("data-placement", "bottom");
	 $(".iconList > a:nth-child(1) > i").popover();
}

/** ******************************** 영화 예매 ********************************* */
function movie() {
	tooltiptext = ["", "영화관은?", "시간은?", "몇 명?"];
	// 영화 선택 후 처리 부분
	var movieTheater = function(){
		$(".write").on("keydown",function(){
			$(".write").popover("hide");
		});
		$(".write").on("keyup",function(){
			var cgv = "CGV";
			var mega = "메가박스";
			var lotcine = "롯데시네마";
			var html = "";
			$.ajax({
				url: "movielocationList",
				type: "post",
				dataType: "json",
				data: {
					query: $(".active .write").val()
				},
				success: function (json) {
					html += '<form>';
					html += '<select multiple class="movieAreaSelect">';
					$.each(json.items, function (i, item) {
						if (item.title.indexOf(cgv) != -1 || item.title.indexOf(mega) != -1 || item.title.indexOf(lotcine) != -1) {
							if (item == 'blank') {
								movieflag = 1;
								return false;
							}
							html += '<option>' + item.title + '</option>';
							if (i >= 2) return false;
						}
					}); //each
					html += '</select>';
					html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="settheater();return false;">';
					html += '</form>';
					if (movieflag != 1) {
						$(".write").attr("data-toggle", "popover");
						$(".write").attr("title", "<img src='resources/img/icon/theater.png'>");
						$(".write").attr("data-content", html);
						$(".write").attr("data-html", "true");
						$(".write").attr("data-placement", "bottom");
						$(".write").popover("show");
					}
				},
				error: function (err) {
					console.log(err);
				}
			}); //ajax
		}); //on. keyup
	};

	// 영화관 선택 후 처리 부분
	var movieTime = function () {
		var html = "";
		$.ajax({
			type: "get",
			url: "gettime",
			data: {},
			success: function (data) {
				html += '<form>';
				html += '<select multiple class="movieTimeSelect">';
				$.each(data, function (i, item) {
					html += '<option>' + item + '</option>';
				}); //each
				html += '</select>';
				html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="settime();return false;">';
				html += '</form>';
				if (movieflag != 1) {
					$(".write").attr("data-toggle", "popover");
					$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
					$(".write").attr("data-content", html);
					$(".write").attr("data-html", "true");
					$(".write").attr("data-placement", "bottom");
					$(".write").popover("show");
				}
			},
			error: function (request, status, error) {
				console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			}
		});
	}

	// 영화 시간 선택 후 처리 부분
	var moviePerson = function () {
		var html="";
		html += '<form>';
		html += '<select multiple class="movieCountSelect">';
		for(var i=1;i<9;i++){
			html+="<option>"+i+"</option>"
		}
		html +='</select>';
				html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="getseat();return false;">';
		html +='</form>';
		$(".write").attr("data-toggle", "popover");
		$(".write").attr("title", "<img src='resources/img/icon/people.png'>");
		$(".write").attr("data-content", html);
		$(".write").attr("data-html", "true");
		$(".write").attr("data-placement", "bottom");
		$(".write").popover("show");
	}

	var movieFinish = function () {
		console.log("moviePerson selected.");
	}

	process = ["", movieTheater, movieTime, moviePerson, movieFinish];
}


/** ******************************** 버스 예매 ********************************* */
function bus() {
	map = new Map();
	tooltiptext = ["출발지는?", "도착지는?", "출발 시간은?", "몇 명?", "시간은?", ""];
	$(".tooltiptext").html(tooltiptext[i]);
	// 버스 출발지 선택 시 처리 부분
	$(".write").on("keydown",function(){
		$(".write").popover("hide");
	});
	$(".write").on("keyup", function () {
		var html = "";

		$.ajax({
			url: "terminalCode",
			type: "post",
			dataType: "json",
			data: {
				query: $(".active .write").val()
			},
			success: function (json) {
				html += '<form>';
				html += '<select multiple class="busStartSelect">';
				if (json.response.body.items.item != null)
					if (Object.keys(json.response.body.items.item).length > 2) {
						$.each(json.response.body.items.item, function (i, item) {
							if (item == 'blank') {
								movieflag = 1;
								return false;
							}
							map.put(item.terminalNm, item.terminalId);
							html += "<option>" + item.terminalNm + "</option>"
						});
					} else {
						$.each(json.response.body.items, function (i, item) {
							if (item == 'blank') {
								movieflag = 1;
								return false;
							}
							map.put(item.terminalNm, item.terminalId);
							html += "<option>" + item.terminalNm + "</option>"
						});
					}
				html += '</select>';
				html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="selectBusArea();return false;">';
				html += '</form>';
				if (movieflag != 1) {
					$(".write").attr("data-toggle", "popover");
					$(".write").attr("title", "<img src='resources/img/icon/departure.png'>");
					$(".write").attr("data-content", html);
					$(".write").attr("data-html", "true");
					$(".write").attr("data-placement", "bottom");
					$(".write").popover("show");
				}
			},
			error: function (err) {
				console.log(err);
			}
		});
	});

	// 버스 출발지 선택 후 처리 부분
	var busDest = function () {
		$(".write").off("keyup");
		$(".write").off("keydown");
		$(".write").popover("destroy");
		$(".write").on("keydown",function(){
			$(".write").popover("hide");
		});
			$(".write").on("keyup",function(){
			var html="";
			var input = $(".active .write").val();
			$.ajax({
				url: "terminalCode",
				type: "post",
				dataType: "json",
				data: {
					query: input
				},
				success: function (json) {
					html += '<form>';
					html += '<select multiple class="busDestSelect">';
					if (json.response.body.items.item != null)
						if (Object.keys(json.response.body.items.item).length > 2) {
							$.each(json.response.body.items.item, function (i, item) {
								if (item == 'blank') {
									movieflag = 1;
									return false;
								}
								map.put(item.terminalNm, item.terminalId);
								html += "<option>" + item.terminalNm + "</option>"
							});
						} else {
							$.each(json.response.body.items, function (i, item) {
								if (item == 'blank') {
									movieflag = 1;
									return false;
								}
								map.put(item.terminalNm, item.terminalId);
								html += "<option>" + item.terminalNm + "</option>"
							});
						}
					html += '</select>';
					html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="selectBusArea2();return false;">';
					html += '</form>';
					if (movieflag != 1) {
						$(".write").attr("data-toggle", "popover");
						$(".write").attr("title", "<img src='resources/img/icon/arrival.png'>");
						$(".write").attr("data-content", html);
						$(".write").attr("data-html", "true");
						$(".write").attr("data-placement", "bottom");
						$(".write").popover("show");
					}
				},
				error: function (err) {
					console.log(err);
				}
			});
		});
	}

	// 버스 도착지 선택 후 처리 부분
	var busTime = function () {
		var html="";
		$(".write").off("keyup");
		$(".write").off("keydown");
		$(".write").popover("destroy");

		html += '<form>';
		html += '<select multiple class="busWantTimeSelect">';
		for (var i = 0; i <= 24; i++) {
			if (i < 10) {
				html += '<option value="0' + i + '00">0' + i + ':00</option>';
			} else if (i > 9) {
				html += '<option value="' + i + '00">' + i + ':00</option>';
			}
		}
		html +='</select>';
		html +='<input type="button" class="form-control" value="선택" onclick="selectBus()">';
		html +='</form>';
		
		$(".modal-bodybus").html(html);
		$("#BusTimeModal").modal();
		
	}

	// 버스 시간 선택 후 처리 부분
	var busPerson = function () {
		var html="";
		html += '<form>';
		html += '<select multiple class="busCountSelect">';
		for(var i=1;i<9;i++){
			html+="<option>"+i+"</option>"
		}
		html +='</select>';
		html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="showBusSeat();return false;">';
		html +='</form>';
		$(".write").attr("data-toggle", "popover");
		$(".write").attr("title", "<img src='resources/img/icon/people.png'>");
		$(".write").attr("data-content", html);
		$(".write").attr("data-html", "true");
		$(".write").attr("data-placement", "bottom");
		$(".write").popover("show");
	}

	var busRealTime = function () {
		$.ajax({
			type: "get",
			url: "getBusTimes",
			data: {},
			success: function (data) {
				var html = "";
				html += '<form>';
				html += '<select multiple class="realTimeBusSelect">';
				$.each(data, function (i, item) {
					html += "<option>" + item + "</option>";
				});
				html += '</select>';
				html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="busTimeSelect();return false;">';
				html += '</form>';
				$(".write").attr("data-toggle", "popover");
				$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
				$(".write").attr("data-content", html);
				$(".write").attr("data-html", "true");
				$(".write").attr("data-placement", "bottom");
				$(".write").popover("show");
			},
			error: function (e) {
				console.log(e);
			}
		});
	}
	var busFinish = function () {
		showBusSeat2();
	}

	process = ["", busDest, busTime, busPerson, busRealTime, busFinish];
}

/** ******************************** 기차 예매 ********************************* */
function train() {
	var placeholderArray = ["영등포", "부산", "", ""];
	tooltiptext = ["출발지는?", "도착지는?", "몇 명?", "출발 시간은?"];
	$(".write").attr("placeholder", placeholderArray[i]);
	$(".tooltiptext").html(tooltiptext[i]);
	
	//기차 출발지 선택 시 처리 부분
	trainAreaSelect('trainStartSelect');
	
	// 기차 도착지 선택 시 처리 부분
	var trainDest = function () {
		$(".write").attr("placeholder", placeholderArray[i]);
		trainAreaSelect('trainDestSelect');
	}
	
	// 기차 인원 선택 시 처리 부분
	var trainPerson = function () {
		$(".write").attr("placeholder", placeholderArray[i]);
		var html = "";
		html += '<form>';
		html += '<select multiple class="trainCountSelect">';
		for (var i = 1; i < 9; i++) {
			html += "<option>" + i + "</option>"
		}
		html +='</select>';
		html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="showTrainTime();return false;">';
		html +='</form>';
		$(".write").attr("data-toggle", "popover");
		$(".write").attr("title", "<img src='resources/img/icon/people.png'>");
		$(".write").attr("data-content", html);
		$(".write").attr("data-html", "true");
		$(".write").attr("data-placement", "bottom");
		$(".write").popover("show");
	}
	
	// 기차 시간 선택 시 처리 부분
	var trainTime = function () {
		$(".write").attr("placeholder", placeholderArray[i]);
		var html = "";
		html += '<form>';
		html += '<select multiple class="trainTimeSelect">';
		var j = 0;
		for (var i = 0; i < 24; i++) {
			if (i < 10) {
				html += '<option value="0' + i + '">' + i + '(오전0' + i + ')</option>';
			} else if (i < 12) {
				html += '<option value="' + i + '">' + i + '(오전' + i + ')</option>';
			} else if (i > 11) {
				html += '<option value="' + i + '">' + i + '(오후0' + (j++) + ')</option>';
			}
		}
		html += '</select>';
		html += '<input type="button" class="form-control" value="선택" onclick="showTimeModal()">';
		html += '</form>';

		$(".modal-body4").html(html);
		$("#TrainTimeModal").modal();
	}
	
	var trainFinish = function () {
		console.log("trainPerson selected.");
	}

	process = ["", trainDest, trainPerson, trainTime, trainFinish];
}

/** ******************************** 맛집 검색 ********************************* */
function restaurant() {
	$(".write").attr("placeholder", "강남 or 삼겹살");
	tooltiptext = ["약속 장소는?","시간은?"];
	$(".tooltiptext").html(tooltiptext[i]);
	var num = 0;
	var input = ""; //입력값
	$(".write").on("keyup", function () {
		input = $(".active .write").val();
	});
	
	var foodSelect = function () {

		foodInput.push(input);

		var html ="";
		$.ajax({
	        url : "searchlocation",
	        type : "post",
	        dataType : "json",
	        data : {
	           query : input
	        },
	        success : function(json) {
	        	html += '<div class="tastyList"><table class="table borderless">';
	           $.each(json.items,function(i,item){
	        	   
	        	   if(item.link!=''){
					   html += "<tr><td><a href='javascript:tastyListSelect("+num+")' id='tastyselect"+(num++)+"' value='"+item.title+"`"+item.link+"`"+item.telephone+"'>"+item.title+"</a></td>";
					   html += '<td><a href="'+item.link+'" target="_blank">홈페이지</a></td>';
					   if(item.telephone!='')  html += '<td>'+item.telephone+'</td></tr>';
					   else html+='<td></td></tr>';
				   }else{
					   html += "<tr><td><a href='javascript:tastyListSelect("+num+")'  id='tastyselect"+(num++)+"' value='"+item.telephone+"'>"+item.title+"</a></td><td></td>";
					   if(item.telephone!='')  html += '<td>'+item.telephone+'</td></tr>';
					   else html+='<td></td></tr>';
				   }
	           });
	           html+='</table></div>';
			   $(".modal-body3").html(html);
			   $("#tastyModal").modal();
	        },
	        error : function(err) {
	        	console.log(err);
	        }
	     });
	}

	var foodResult = function () {

	}
	
	process = ["", foodSelect, foodResult];
}

/** ******************************** 직접 작성 ********************************* */
function write() {
	tooltiptext = ["일정을 입력하세요", "", "",""];
	$(".tooltiptext").html(tooltiptext[i]);
	
	var input = ""; //입력값
	$(".write").on("keyup",function(){
		input = $(".active .write").val();
	});
	
	// 직접 일정 입력 시 처리 부분
	var scheduleWrite = function () {
		spinnerStart();
		var html="";
		var num = 0;
		inputparam2 = input;
		 $.ajax({
	           url : "directWrite",
	           type : "post",
	           contentType : "application/json",
	           data : JSON.stringify ({
	        	   "date" : dateNow,
	        	   "query" : input,
	        	   "selectedFriendList" : selectedFriendList
	           }),
	           dataType : "json",
	           success : function(data) {
	        	   spinnerEnd();
	        	   if(data.fail=='fail'){
	        		   alert("선택한 극장에 원하시는 상영스케쥴이 없습니다. 다시시도해주세요.");
	        		   location.href="diary";
	        	   }
	        	   if(data!=null){
	        		   returnData=data;
	        	   }
	        	   if(data.TASTY!=''){
	        		   timeflag=true;
	        		   html += '<div class="tastyList"><table class="table borderless">';
	        		   $.each(JSON.parse(data.TASTY).items,function(i,tasty){
	        			   if(tasty.link!=''){
	        				   html += "<tr><td><a href='javascript:directTastyListSelect("+num+")' id='tastyselect"+(num++)+"' value='"+tasty.title+"`"+tasty.link+"`"+tasty.telephone+"'>"+tasty.title+"</a></td>";
	        				   html += '<td><a href="'+tasty.link+'" target="_blank">홈페이지</a></td>';
	        				   if(tasty.telephone!='')  html += '<td>'+tasty.telephone+'</td></tr>';
	        				   else html+='<td></td></tr>';
	        			   }else{
	        				   html += "<tr><td><a href='javascript:directTastyListSelect("+num+")'  id='tastyselect"+(num++)+"' value='"+tasty.telephone+"'>"+tasty.title+"</a></td><td></td>";
	        				   if(tasty.telephone!='')  html += '<td>'+tasty.telephone+'</td></tr>';
	        				   else html+='<td></td></tr>';
	        			   }
	            	    });
	        		   html+='</table></div>';
	        		   $(".modal-body3").html(html);
	        		   $("#tastyModal").modal();
	        		   
	        	   }else if(data.FLAG=='movie'){
	        		   if(data.MVLIST!=''){
	        				html += '<select multiple class="directMvList">';
	        		            $.each(data.MVLIST,function(i,item){
	        		            	    html += "<option>"+item+"</option>";
	        		               });
	        		        	html +='</select>';
	        		        	html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="directMVListSelect();return false;">';
		        	           	
		        	    		$(".write").attr("data-toggle", "popover");
		        	    		$(".write").attr("title", "<img src='resources/img/icon/show.png'>");
		        	    		$(".write").attr("data-content", html);
		        	    		$(".write").attr("data-html", "true");
		        	    		$(".write").attr("data-placement", "bottom");
		        	    		$(".write").popover("show");  
	        	        }else{
	        	        	 if(data.MVTIME!=''){
	  	        			   html += '<select multiple class="directMvTimeList">';
	  	        			   $.each(data.MVTIME,function(i,item){
	         		            	    html += "<option>"+item+"</option>";
	         		               });
	         		        		html +='</select>';
	  	        	          html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="directMVTimeSelect();return false;">';
	  	        	    		$(".write").attr("data-toggle", "popover");
	  	        	    		$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
	  	        	    		$(".write").attr("data-content", html);
	  	        	    		$(".write").attr("data-html", "true");
	  	        	    		$(".write").attr("data-placement", "bottom");
	  	        	    		$(".write").popover("show");
	        	        	 }
	        	        }
	        	   } else if (data.FLAG=='bus') {
	        	   } else if (data.FLAG=='train') {
	        		   
	        	   } else {
	        		   location.href = "diary";
	        	   }
	           },
	           error : function(err) {
	              console.log(err);
	           }
	        });
	}
	
	var scheduleFinish = function () {
		
		console.log('scFinish');
	}

	process = ["", scheduleWrite, scheduleFinish];
}

/** ******************************** 일정 검색 ********************************* */
function search() {
	tooltiptext = ["검색할 일정을 입력하세요"];
	$(".tooltiptext").html(tooltiptext[i]);

	// 일정 검색 시 처리 부분
	var scheduleSearch = function () {
		alert("searched");
	}

	process = [scheduleSearch];
}

/** ******************************* 달력 부분 ******************************** */

function dateInitialize() {
	i = 0;
	$(".slider-area .carousel-caption").css("top", "10%");
	$(".table > tbody > tr > td").css("outline", "");
	$(".write").val("");
	$(".written").html("");
	selectedFriendList = new Array();
	$(".iconList").css("display", "none");
	$(".textBlock").css("display", "none");
}

// 지난달
function lastMonth() {
	var tempYear = dt.getFullYear();
	var tempMonth = dt.getMonth();

	dt.setMonth(dt.getMonth() - 1);
	var html = $(".item:first-child").html();
	
	// div id 연도 바꾸기
	html = html.replace('<div id="' + tempYear + '-' + (tempMonth + 1) + '"', '<div id="'
			+ dt.getFullYear() + "-" + (dt.getMonth() + 1) + '"');

	// table 부분 지난달로 바꾸기
	html = html.replace(html.slice(html.search("<table"), html.search("</table>") + 8), calendarHtml(dt));

	// 년 바꾸기
	html = html.replace('<span id="' + tempYear + '">' + tempYear + '</span>',
		'<span id="' + dt.getFullYear() + '">' + dt.getFullYear() + '</span>');

	// 월 바꾸기
	html = html.replace('<span id="' + padDigits((tempMonth + 1), 2) + '">' + padDigits((tempMonth + 1), 2) + '</span>',
		'<span id="' + padDigits((dt.getMonth() + 1), 2) + '">' + padDigits((dt.getMonth() + 1), 2) + '</span>');

	html = "<div class='item'>" + html + "</div>";

	// 현재 item에서 앞에 item이 존재하지 않는 경우
	if ($(".item.active").index(".item") == 0) {
		$(".item:first-child").before(html);
		makePopover();

		eventActive();
	}

	// 현재 item이 세 개인 경우 한 개 삭제
	if ($(".item").length == 3) {
		$(".item:last-child").remove();
	}
	
	$(".item>div:last-child>div:last-child").removeClass().addClass("animated fadeInLeftBig");
}

// 다음달
function nextMonth() {
	var tempYear = dt.getFullYear();
	var tempMonth = dt.getMonth();

	dt.setMonth(dt.getMonth() + 1);
	var html = $(".item:last-child").html();
	
	// div id 연도 바꾸기
	html = html.replace('<div id="' + tempYear + '-' + (tempMonth + 1) + '"', '<div id="'
			+ dt.getFullYear() + "-" + (dt.getMonth() + 1) + '"');

	// table 부분 다음달로 바꾸기
	html = html.replace(html.slice(html.search("<table"), html.search("</table>") + 8), calendarHtml(dt));

	// 년 바꾸기
	html = html.replace('<span id="' + tempYear + '">' + tempYear + '</span>',
		'<span id="' + dt.getFullYear() + '">' + dt.getFullYear() + '</span>');

	// // 월 바꾸기
	html = html.replace('<span id="' + padDigits((tempMonth + 1), 2) + '">' + padDigits((tempMonth + 1), 2) + '</span>',
		'<span id="' + padDigits((dt.getMonth() + 1), 2) + '">' + padDigits((dt.getMonth() + 1), 2) + '</span>');

	html = "<div class='item'>" + html + "</div>";

	// 현재 item에서 앞에 item이 존재하지 않는 경우
	if ($(".item.active").index(".item") == $(".item").length - 1) {
		$(".item:last-child").after(html);
		makePopover();

		eventActive();
	}

	// 현재 item이 세 개인 경우 한 개 삭제
	if ($(".item").length == 3) {
		$(".item:first-child").remove();
	}
	
	$(".item>div:last-child>div:last-child").removeClass().addClass("animated fadeInRightBig");
}

function padDigits(number, digits) {
	return Array(Math.max(digits - String(number).length + 1, 0)).join(0) +
		number;
}

function calendarHtml(date) {
	$("#year").attr("id", date.getFullYear());
	$("#month").attr("id", padDigits((date.getMonth() + 1), 2));
	$("#" + date.getFullYear()).html(date.getFullYear());
	$("#" + padDigits((date.getMonth() + 1), 2)).html(padDigits((date.getMonth() + 1), 2));

	var html = "<table class='table borderless'>";
	html += "<tr class='text-center'>";
	html += "<td>SUN</td><td>MON</td><td>TUE</td><td>WED</td><td>THU</td><td>FRI</td><td>SAT</td>";
	html += "</tr>";

	// 우선은 오늘 날짜 기준으로 설정
	var temp = date.getMonth();

	// 날짜를 현재 달의 1일로 설정
	var cnt = 1;
	date.setDate(cnt);

	html += "<tr>";
	for (var i = 0; i < date.getDay(); i++) {
		html += "<td></td>";
	}

	while (date.getMonth() == temp) {
		// 각 칸에 날짜 정보 저장
		var dateForm = date.getFullYear() + "-" + padDigits((date.getMonth() + 1), 2) + "-" +
		padDigits(date.getDate(), 2);
		html += "<td id='" + dateForm + "'>" + "<div style='height: 100%; width: 100%'><a href=javascript:selectedDate('" + dateForm + "')>" +
			date.getDate() + "</a></div></td>";

		if (date.getDay() == 6) {
			html += "</tr><tr>";
		}
		date.setDate(++cnt);
	}

	if (date.getDay() != 0) {
		for (var i = 6 - date.getDay(); i >= 0; i--) {
			html += "<td></td>";
		}
	}

	html += "</tr>";

	html += "</table>";

	date.setMonth(date.getMonth() - 1);

	return html;
}

// 날짜 선택
function selectedDate(date) {
	dateNow="";
	i = 0;
	selectedFriendList = new Array();
	$(".table > tbody > tr > td").css("outline", "");
	$("#" + date).css("outline", "lightsteelblue solid 2px");
	$(".iconList").addClass("animated fadeInDown");
	hideTextBlock();
	dateNow += date.split('-')[0];
	dateNow += date.split('-')[1];
	dateNow += date.split('-')[2];
}

function makePopover() {
	// scheduleList 가져오기
	scheduleList = JSON.parse($("#scheduleList").val());
	
	var param1 ="";
	var param2 ="";
	
	var array1 = todayWeather();
	var array2 = weekWeather();
	
	var html = new Array(); // 일정 내용 및 날씨 넣는곳.
	var weather = new Array(); // 일정 내용 및 날씨 넣는곳.
	for (var i = 0; i < $("#" + dt.getFullYear() + "-" + (dt.getMonth() + 1) + " > table > tbody > tr > td div").length; i++) {
		var p = $("#" + dt.getFullYear() + "-" + (dt.getMonth() + 1) + "> table > tbody > tr > td a").eq(i);
		var pastHtml = "<div class = 'pastDate' style = 'background-image:url(resources/img/icon/past.png); background-size: 312px;'>"+p.html()+"</div>";
		
		html[i]="";
		weather[i]="";
		/* 일정 가져오는 부분 */
		$.each(scheduleList, function(key, value) {
			if (p.parent().parent().attr("id") == value.SC_STDT) {
				if(value.SC_DFLAG == 'Y') {
					p.attr("data-toggle", "popover");
					
					markCircle(p.parent());
					
					var frList = value.SC_COMPLIST;
					var frListToString = "";
					
					$.each(frList, function (key, value) {
						frListToString += value + "&nbsp;&nbsp;";
					});
					
					if(value.SC_CON.split("_")[4]=="mv") {
						mvscno[i] = value.SC_NO_PK;
						mvtimecancel[i] = value.SC_CON.split("_")[0];
						mvnamecancel[i] = value.SC_CON.split("_")[1];
						if (value.SC_FIN == 'N') {
							frListToString = '한진수';
							html[i] += '<button class="accordion addedByFriend"><img src="resources/img/icon/cinema.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '</button>';
						} else {
							html[i] += '<button class="accordion"><img src="resources/img/icon/cinema.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '<div class="scheCancelBtn" onclick="mvCancel('+i+')">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">TITLE<br>THEATER<br>SEAT<br>WITH</div>';
						html[i] += '<div class="right-box">' + value.SC_CON.split("_")[1] + '<br>'+ value.SC_CON.split("_")[2] +'<br>'+ value.SC_CON.split("_")[3] + '<br>' + frListToString + '</div>';
						html[i] += '</div>';
						
					}
					else if(value.SC_CON.split("_")[4]=="kobus") {
						kobusscno[i] = value.SC_NO_PK;
						kobuscancel[i] = value.SC_CON.split("_")[3]+"_"+value.SC_CON.split("_")[0]+"_"+value.SC_CON.split("_")[2];
						if (value.SC_FIN == 'N') {
							frListToString = '한진수';
							html[i] += '<button class="accordion addedByFriend"><img src="resources/img/icon/bus.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '</button>';
						} else {
							html[i] += '<button class="accordion"><img src="resources/img/icon/bus.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '<div class="scheCancelBtn" onclick="kobusCancelModal('+i+')">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">AREA<br>SEAT<br>GRADE<br>WITH</div>';
						html[i] += '<div class="right-box">' + value.SC_CON.split("_")[1] + '<br>'+ value.SC_CON.split("_")[2] +'<br>'+ value.SC_CON.split("_")[5] + '<br>' + frListToString + '</div>';
						html[i] += '</div>';
					}
					else if(value.SC_CON.split("_")[4]=="easy") {
						easybusscno[i] = value.SC_NO_PK;
						if (value.SC_FIN == 'N') {
							frListToString = '한진수';
							html[i] += '<button class="accordion addedByFriend"><img src="resources/img/icon/bus.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '</button>';
						} else {
							html[i] += '<button class="accordion"><img src="resources/img/icon/bus.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '<div class="scheCancelBtn" onclick="trainCancel()">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">AREA<br>SEAT<br>GRADE<br>WITH</div>';
						html[i] += '<div class="right-box">' + value.SC_CON.split("_")[1] + '<br>'+ value.SC_CON.split("_")[2] +'<br>'+ value.SC_CON.split("_")[3] + '<br>' + frListToString + '</div>';
						html[i] += '</div>';
					}
					else if(value.SC_CON.split("_")[4]=="ktx"){
						trainscno[i] = value.SC_NO_PK; 
						traincancel[i] = value.SC_CON.split("_")[3]+" "+value.SC_CON.split("_")[0];
						if (value.SC_FIN == 'N') {
							frListToString = '한진수';
							html[i] += '<button class="accordion addedByFriend"><img src="resources/img/icon/trainIcon.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '</button>';
						} else {
							html[i] += '<button class="accordion"><img src="resources/img/icon/trainIcon.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '<div class="scheCancelBtn" onclick="trainCancelModal('+i+')">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">AREA<br>SEAT<br>GRADE<br>WITH</div>';
						html[i] += '<div class="right-box">' + value.SC_CON.split("_")[1] + '<br>'+ value.SC_CON.split("_")[2] +'<br>'+ value.SC_CON.split("_")[3] + '<br>' + frListToString + '</div>';
						html[i] += '</div>';
					}
					else if(value.SC_CON.split("_")[4]=="common"){
						ccscno[i] = value.SC_NO_PK;
						if(timeflag){
							html[i] += '<button class="accordion"><img src="resources/img/icon/restaurant.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[6] + '<div class="scheCancelBtn" onclick="ccCancel('+i+')">X</div></button>';
							timeflag=false;
						}
						else{
							html[i] += '<button class="accordion"><img src="resources/img/icon/restaurant.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[5] + '<div class="scheCancelBtn" onclick="ccCancel('+i+')">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">TITLE<br>HP<br>TEL<br>CONTENT<br>WITH</div>';
						
						if(value.SC_CON.split("_")[2]=='' || value.SC_CON.split("_")[2]=='undefined'){ //link 없을떄
							html[i] += '<div class="right-box">'+value.SC_CON.split("_")[1]+'<br>'+" "+'<br>'+value.SC_CON.split("_")[3] + '<br>' + value.SC_CON.split("_")[6] +'<br>'+frListToString + '</div>';
						}
						else if(value.SC_CON.split("_")[3]==''){
							html[i] += '<div class="right-box">'+value.SC_CON.split("_")[1]+'<br><a href="'+value.SC_CON.split("_")[2]+'" target="_blank" style="color: #666666;">홈페이지</a><br>'+" " + '<br>' + value.SC_CON.split("_")[6]+'<br>' +frListToString + '</div>';
						}
						else{
							html[i] += '<div class="right-box">'+value.SC_CON.split("_")[1]+'<br><a href="'+value.SC_CON.split("_")[2]+'" target="_blank" style="color: #666666;">홈페이지</a><br>'+value.SC_CON.split("_")[3] + '<br>' + value.SC_CON.split("_")[6]+'<br>'+frListToString + '</div>';
						}
							html[i] += '</div>';
						}					
					else {
						defaultscno[i] = value.SC_NO_PK;
						if (value.SC_FIN == 'N') {
							frListToString = '한진수';
							html[i] += '<button class="accordion addedByFriend"><img src="resources/img/icon/keyboard.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '</button>';
						} else {
							html[i] += '<button class="accordion"><img src="resources/img/icon/keyboard.png" width="20px" height="20px">&nbsp;&nbsp;' + value.SC_CON.split("_")[0] + '<div class="scheCancelBtn" onclick="defaultCancel('+i+')">X</div></button>';
						}
						html[i] += '<div class="panel">';
						html[i] += '<div class="left-box">CONTENT<br>WITH</div>';
						html[i] += '<div>' + value.SC_CON.split("_")[0] + '<br>' + frListToString +'</div>';
						html[i] += '</div>';
					}
					p.attr("data-title", pastHtml);
					p.attr("data-content", html[i]);
					
				}
			}
		});
		
		// 날씨
		
		$.each(array1, function(j,item){
			
			if (p.parent().parent().attr("id") == item.date) {
				weather[i]+=item.html;
				p.attr("data-title", weather[i]);
				p.attr("data-content", html[i]);
				
			}
		 });
		
		$.each(array2, function(j,item){
			if (p.parent().parent().attr("id") == item.date) {
				weather[i+3]="";
				weather[i+3]+=item.html;
				p.attr("data-title", weather[i+3]);
				p.attr("data-content", html[i+3]);
			}
		 });
		
		p.attr("data-html", "true");
		p.attr("data-placement", "top");
		p.attr("data-trigger", "manual");
		p.popover().on("mouseenter", function() {
			var _this = this;
			$(this).popover("show");
			$(this).siblings(".popover").on("mouseleave", function() {
				$(_this).popover("hide");
			});
		}).on("mouseleave", function() {
			var _this = this;
			setTimeout(function() {
				if (!$(".popover:hover").length) {
					$(_this).popover("hide");
				}
			}, 10);
		});
	}
	
	// accordian 이벤트 활성화
	$(document).on("click", ".accordion", function() {
		$(this).toggleClass("active");
		
		var panel = $(this).next();
		console.log(panel.css("maxHeight"));
		console.log(panel.prop("scrollHeight"));
		
		if (panel.css("maxHeight") == "0px") {
			panel.css("maxHeight", panel.prop("scrollHeight") + "px");
		} else {
			panel.css("maxHeight", "0px");
		}
	});
	
	// hover 시 날짜 숫자색 변경
	p.on("hover", function() {
		p.css("color", "red");
	});
	
	markToday();
}

function markToday() {
	var todayDate = $("#" + today.getFullYear() + "-" + padDigits((today.getMonth() + 1), 2) + "-" +
			padDigits(today.getDate(), 2) + " a");
	todayDate.parent().addClass("fa bounce");
}

function markCircle(p) {
	p.css("border-radius", "50%");
	p.css("background", "rgb(162, 189, 350)");
	p.css("width", "40px");
	p.css("height", "40px");
	p.css("margin", "auto");
}

/** ********************************** 날씨 *********************************** */

//오늘부터 +2일까지의 날씨
function todayWeather() {
	var whtml = "";
	var whtml1 = "";
	var whtml2 = "";
	var jArray = new Array();
	var jobj = new Object();
	var jobj1 = new Object();
	var jobj2 = new Object();
	
	var date = "";
	var fdate = "";
	
     $.ajax({
        url : "weather",
        type : "post",
        dataType : "json",
        data : {
        	city : "서울",
        	county : "강남구",
        	village : "삼성동"
        },
        async : false,
        success : function(json) {

        	 $.each(json.weather.forecast3days,function(i,item){
        		

        		 whtml += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.fcst3hour.sky.code4hour+"b.png); background-size: 312px;'> '<img src='resources/img/weather_icons/"+item.fcst3hour.sky.code4hour+".png' width='40px' height='40px'>"+item.fcst3hour.sky.name4hour+
        		 		  "<br><div class ='temperature'>"+item.fcst3hour.temperature.temp4hour.split('.')[0]+"°</div></div>";
        		 whtml1 +="<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.fcst3hour.sky.code25hour+"b.png); background-size: 312px;'> '<img src='resources/img/weather_icons/"+item.fcst3hour.sky.code25hour+".png' width='40px' height='40px'>"+item.fcst3hour.sky.name25hour+
 		 				  "<br><div class ='temperature'>"+item.fcst3hour.temperature.temp25hour.split('.')[0]+"°</div></div>";
        		 whtml2 +="<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.fcst3hour.sky.code49hour+"b.png); background-size: 312px;'> '<img src='resources/img/weather_icons/"+item.fcst3hour.sky.code49hour+".png' width='40px' height='40px'>"+item.fcst3hour.sky.name49hour+
 		 				  "<br><div class ='temperature'>"+item.fcst3hour.temperature.temp49hour.split('.')[0]+"°</div></div>";
        		 
        		 fdate = item.timeRelease.split(' ')[0].substr(0,8);
        		 var date_num = parseInt(item.timeRelease.split(' ')[0].split('-')[2]);
        		 
        		 jobj.date = item.timeRelease.split(' ')[0];
        		 jobj.html =  whtml;
        		 
        		 jobj1.date = fdate+padDigits((date_num+1), 2);
        		 jobj1.html =  whtml1;
        		 
        		 jobj2.date = fdate+padDigits((date_num+2), 2);
        		 jobj2.html =  whtml2;
        		 
        		 jArray .push(jobj);
        		 jArray .push(jobj1);
        		 jArray .push(jobj2);
             });   
        },
       	        
        error : function(err) {
           console.log(err);
        }
     });
     return jArray;
     
}

//+3일부터 +7일까지의 날씨
function weekWeather() {
	var whtml3 = "";
	var whtml4 = "";
	var whtml5 = "";
	var whtml6 = "";
	var whtml7 = "";
	
	var jArray = new Array();
	var jobj3 = new Object();
	var jobj4 = new Object();
	var jobj5 = new Object();
	var jobj6 = new Object();
	var jobj7 = new Object();
	
	var date = "";
	var fdate = "";
	
     $.ajax({
        url : "weather2",
        type : "post",
        dataType : "json",
        data : {
        	city : "서울",
        	county : "강남구",
        	village : "삼성동"
        },
        async : false,
        success : function(json) {
        	 $.each(json.weather.forecast6days,function(i,item){

        		 whtml3 += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.sky.pmCode3day+"b.png); background-size: 312px;'> <img src='resources/img/weather_icons/"+item.sky.pmCode3day+".png' width='40px' height='40px'>"+item.sky.pmName3day+
        		 		"<br><div class ='temperature'>"+item.temperature.tmax3day.split('.')[0]+"°</div></div>";
        		 whtml4 += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.sky.pmCode4day+"b.png); background-size: 312px;'> <img src='resources/img/weather_icons/"+item.sky.pmCode4day+".png' width='40px' height='40px'>"+item.sky.pmName4day+"<br>" +
        		 		"<div class ='temperature'>"+item.temperature.tmax4day.split('.')[0]+"°</div></div>";
        		 whtml5 += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.sky.pmCode5day+"b.png); background-size: 312px;'> <img src='resources/img/weather_icons/"+item.sky.pmCode5day+".png' width='40px' height='40px'>"+item.sky.pmName5day+"<br>" +
        		 "<div class ='temperature'>"+item.temperature.tmax5day.split('.')[0]+"°</div></div>";
        		 whtml6 += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.sky.pmCode6day+"b.png); background-size: 312px;'> <img src='resources/img/weather_icons/"+item.sky.pmCode6day+".png' width='40px' height='40px'>"+item.sky.pmName6day+"<br>" +
        		 "<div class ='temperature'>"+item.temperature.tmax6day.split('.')[0]+"°</div></div>";
        		 whtml7 += "<div class='backweather'  style = 'background-image:url(resources/img/weather_icons/"+item.sky.pmCode7day+"b.png); background-size: 312px;'> <img src='resources/img/weather_icons/"+item.sky.pmCode7day+".png' width='40px' height='40px'>"+item.sky.pmName7day+"<br>" +
        		 "<div class ='temperature'>"+item.temperature.tmax7day.split('.')[0]+"°</div></div>";
        		
        		 fdate = item.timeRelease.split(' ')[0].substr(0,8);
        		 var date_num = parseInt(item.timeRelease.split(' ')[0].split('-')[2]);
        		
        		 jobj3.date = fdate+padDigits((date_num+3), 2);;
        		 jobj3.html =  whtml3;
        		 jobj4.date = fdate+padDigits((date_num+4), 2);;
        		 jobj4.html =  whtml4;
        		 jobj5.date = fdate+padDigits((date_num+5), 2);;
        		 jobj5.html =  whtml5;
        		 jobj6.date = fdate+padDigits((date_num+6), 2);;
        		 jobj6.html =  whtml6;
        		 jobj7.date = fdate+padDigits((date_num+7), 2);;
        		 jobj7.html =  whtml7;
        		 
        		 jArray .push(jobj3);
        		 jArray .push(jobj4);
        		 jArray .push(jobj5);
        		 jArray .push(jobj6);
        		 jArray .push(jobj7);
        		 
             });   
        },
       	        
        error : function(err) {
           console.log(err);
        }
     });
     return jArray;
}

function setmovie(){
	var movieName = $("#movieselect option:selected").val();
	$(".iconList > a:nth-child(1) > i").popover("destroy");
	showTextBlock();
	inputText(movieName);
	$.ajax({
		type : "get",
		url : "setmovie",
		data : {
			movie : movieName			
		},
		error : function(e){
			console.log(e);
		}
	});
}

function settheater(){
	var areaName = $(".active .movieAreaSelect option:selected").val();
	$(".write").popover("destroy");
	$(".write").off("keyup");
	$(".write").off("keydown");
	spinnerStart();
	
	$.ajax({
		type : "get",
		url : "settheater",
		data : {
			theater : areaName.split(' ')[1]		
		},
		success : function(data){
			if(data){
				$.ajax({
					type : "get",
					url : "setdate",
					data : {
						moviedate : dateNow			
					},
					success : function(data){
						if(data){
							showTextBlock();
							spinnerEnd();
							inputText(areaName);
							return true;
						}
					},
					error : function(e){
						console.log(e);
					}
				});
			}else{
				alert("선택한 극장에 원하시는 상영스케쥴이 없습니다. 다시시도해주세요.");
				location.href="diary";
			}
		},
		error : function(request,status,error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function settime(){
	var movieTime = $(".active .movieTimeSelect option:selected").val();
	$(".write").popover("destroy");
	showTextBlock();
	
	$.ajax({
		type : "get",
		url : "settime",
		data : {
			time : movieTime			
		},
		success : function(data){
			inputText(movieTime);
		},
		error : function(e){
			console.log(e);
		}
	});
}

function getseat(){
	var count = $(".active .movieCountSelect option:selected").val();
	$(".write").popover("destroy");
	$.ajax({
		type : "get",
		url : "getseat",
		data : {
			humanCount : count
		},
		dataType : "json",
		success : function(data){
			$("#test").html(data.tag);
			$('div#wrap').modal();
			setSeat(data.count);
		},
		error : function(e){
			console.log(e);
		}
	});
}

function setSeat(num){
	var seats_span_no = $("div#seats_list span.no");
	var array = new Array();
	var check = new Array();
	var flag = 0;
	
	seats_span_no.each(function(index, item){
		array.push(0);
		if($(item).next().text() == ' Economy' || $(item).next().text() == ' comfort' ||
				$(item).next().text() == ' prime zone prime' || $(item).next().text() == ' sweetbox'
			|| $(item).next().text() == ''){
			$(this).parent().on({
				mouseenter : function(){
					$(item).css("background","#d1ca36");
				},
				mouseleave : function(){
					if(array[index] == 0){
						if($(item).next().text() == ' sweetbox'){
							if($(item).next().next().text()==' 선택불가'){
								$(item).css("background","#bbbbbb");
							}else{
								$(item).css("background","#da1b68");
							}
						}else{
							if($(item).next().next().text()==' 선택불가'){
								$(item).css("background","#bbbbbb");
							}else{
								$(item).css("background","#666666");
							}
						}
					}
				},
				click : function(){
					if(array[index]==0){
						$(item).css("background","yellow");
						array[index] = 1;
						check[flag++] = $(item).parent().parent().parent().parent().parent().index();
						check[flag++] = Number($(item).text());
						if((num*2)==check.length){
							if(confirm("예매 하시겠습니까?")){
								$('div#wrap').modal('hide');
								$(".write").css("display", "none");
								inputText(num+"명");

								$.ajax({
									type : "POST",
									url : "setseat",
									contentType : "application/json",
									data : JSON.stringify({
										seats : check			
									}),
									dataType : "json",
									success : function(data){
										
										$("#scdate").text(data[2]);
										$("#mvname").text(data[3]);
										$("#mvarea").text(data[4]);
										$("#mvinfo").text(data[5]);
										$("#people").text(data[6]);
										$("#seatinfo").text(data[0]);
										$("#price").text(data[1]);

										$('div#checkMvModal').modal();
										
									},
									error : function(e){
										console.log(e);
									}
								});
							}
						}
					}else{
						if($(item).next().text() == ' sweetbox'){
							$(item).css("background","#da1b68");
						}else{
							$(item).css("background","#666666");
						}
						array[index] = 0;
						check.splice(flag-2,2);
						flag = flag-2;
					}
				}//click
			});//on			
		} //if
	}); //each
}
function check_movieform(){
	$('div#checkMvModal').modal('hide');
	var date = $("#scdate").text();
	var mvname = $("#mvname").text();
	var mvarea = $("#mvarea").text();
	var seat = $("#seatinfo").text();
	mvPaymentInfo="";
	mvPaymentInfo += (date.split(" ")[1]+"_"+mvname+"_"+mvarea+"_"+seat+"_mv");
	
	$('div#MoviePayModal').modal();
}

function mvSetting(){
	$.ajax({
		type : "get",
		url : "mvInfoSetting",
		success : function(data){
		},
		error : function(e){
			console.log(e);
		}
	});
}

function payment() {
	var card = $("#lp_card_type_mv option:selected").text();
	var cardno = $("#cardnomv").val();
	var sno = $("#pwmv").val();
	var year = $("#validyearmv option:selected").val();
	var month = $("#validmonthmv option:selected").val();
	var birth = $("#birthmv").val();
	$('div#MoviePayModal').modal('hide');
	
	spinnerStart();
	
	$.ajax({
		type : "post",
		url : "payment",
		contentType : "application/json",
		data : JSON.stringify({
			"card" : card,
			"cardno" : cardno,
			"sno" : sno,
			"year" : year,
			"month" : month,
			"birth" : birth,
			"paymentInfo" : mvPaymentInfo,
			"date" : dateNow.substring(2,8),
			"selectedFriendList" : selectedFriendList
		}),
		dataType : "json",
		success : function(data) {
			if(data) {
				//예매완료.
				if(typeof tasteInfo == "undefined" || tasteInfo=='') {
					location.href="diary";
					spinnerEnd();
					selectedFriendList = new Array();
				}else {
					if(tasteInfo!='' || tasteInfo!=null || typeof tasteInfo != "undefined") {
						$.ajax({
							type : "POST",
							url : "commonsc",
							contentType : "application/json",
							data : JSON.stringify({
								text : tasteInfo.split("`")[0],
								link : tasteInfo.split("`")[1],
								telephone: tasteInfo.split("`")[2],
								date : dateNow.substring(2,8),
								"original" : inputparam2,
								flag : "common",
								"selectedFriendList" : selectedFriendList
							}),
							success : function(data){
								inputparam2="";
								location.href="diary";
								spinnerEnd();
								selectedFriendList = new Array();
							},
							error : function(e){
								console.log(e);
							}
						});
					}else{
						alert('카드정보 오류입니다. 다시 입력 해주세요.')
						$('div#MoviePayModal').modal();
						spinnerEnd();
					}
				}
			}//if
		},
		error : function(e){
			console.log(e);
		}
	});
}

Map = function () {
	this.map = new Object();
};
Map.prototype = {
	put: function (key, value) {
		this.map[key] = value;
	},
	get: function (key) {
		return this.map[key];
	},
	containsKey: function (key) {
		return key in this.map;
	},
	containsValue: function (value) {
		for (var prop in this.map) {
			if (this.map[prop] == value) return true;
		}
		return false;
	},
	isEmpty: function (key) {
		return (this.size() == 0);
	},
	clear: function () {
		for (var prop in this.map) {
			delete this.map[prop];
		}
	},
	remove: function (key) {
		delete this.map[key];
	},
	keys: function () {
		var keys = new Array();
		for (var prop in this.map) {
			keys.push(prop);
		}
		return keys;
	},
	values: function () {
		var values = new Array();
		for (var prop in this.map) {
			values.push(this.map[prop]);
		}
		return values;
	},
	size: function () {
		var count = 0;
		for (var prop in this.map) {
			count++;
		}
		return count;
	}
};

function selectBusArea() {
	start = "";
	start = $(".active .busStartSelect option:selected").val();
	$(".active .write").popover("destroy");
	showTextBlock();
	inputText(start);
	
}

function selectBusArea2() {
	dest = "";
	dest = $(".active .busDestSelect option:selected").val();
	$(".active .write").popover("destroy");
	$.ajax({
		type : "get",
		url : "setStartingPoint",
		data : {
			area : map.get(start).substring(4,7)
		},
		success : function(data){
			$.ajax({
				type : "get",
				url : "setDestination",
				data : {
					area : map.get(dest).substring(4,7)
				},
				error : function(e){
					console.log(e);
				}
			});
		},
		error : function(e){
			console.log(e);
		}
	});
	showTextBlock();
	inputText(dest);
}

	function selectBus(){
		$("#BusTimeModal").modal('hide');
		var time = $(".busWantTimeSelect option:selected").val();
		starttime = dateNow+time;
		$.ajax({
			type : "get",
			url : "setBusdate",
			data : {
				date : starttime
			},
			error : function(e){
				console.log(e);
			}
		});
		showTextBlock();
		inputText(time.substring(0,2)+"시쯤 출발");
	}

function showBusSeat(){
	var count = $(".active .busCountSelect option:selected").val();
	$(".write").popover("destroy");
	full_seat = count;
	$.ajax({
		type : "get",
		url : "setHuman",
		data : {
			count : count
		},
		success : function(data){
			showTextBlock();
			inputText(count+"명");
		},
		error : function(e){
			console.log(e);
		}
	});
}

function busTimeSelect(){
	var time = $(".realTimeBusSelect option:selected").val();
	$(".write").popover("destroy");
	
	$.ajax({
		type : "get",
		url : "selectTicket",
		data : {
			time : time
		},
		success : function(data){
			selectBusInfo+= data[3].substring(36,40)+","+data[3].substring(45,47)+","+data[3].substring(0,17)+" "+data[3].substring(18,23)+","+data[0]+","+data[1];
			showTextBlock();
			inputText(time);
		},
		error : function(e){
			console.log(e);
		}
	});
}

function showBusSeat2(){
	$.ajax({
		type : "get",
		url : "showBusSeat",
		data : {
			count : full_seat
		},
		dataType : "json",
		success : function(data){
			
			$("div.modal-body2").html(data.form);
			$("div#seatModal").modal();
			if(data.flag == 1){
				//추가
				flag = data.flag;
				$("input[type='checkbox']").attr("onClick","return fc_Check(this)");
				$("img").eq(0).attr("src","resources/img/enter.gif");
				$("img").eq(1).attr("src","resources/img/drive.gif");
				$("img").eq(2).remove();
			}
			
			full_seat = data.count;
			
		},
		error :function(request,status,error){
	        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); 
		}
	});
}

function fc_Check(box) {
	if(box.checked == true) {
		check_seat ++;
		if(full_seat < check_seat) {
			check_seat = check_seat -1;
			alert("예매/예약 하실 매수를 초과하였습니다. 예약/예매하고자 하시는 매수는 "+full_seat+"매입니다.");
			return false;
		}//if
		if(flag==1){
			seatList.push($(box).val());
		}else{
			seatList.push($(box).prev().text());
		}
		if(full_seat == check_seat){
			seatList.sort();
			if(confirm("좌석 "+seatList+"를 예약하시겠습니까?")){
				check_seat = 0;
				$('div#seatModal').modal('hide');
				seatList.push(selectBusInfo);
				$.ajax({
					type : "POST",
					url : "setBusSeat",
					contentType : "application/json",
					data : JSON.stringify({
						seats : seatList			
					}),
					dataType : "json",
					success : function(data){
						seatList = new Array();
						busReserveInfo = data;
						
						check_modal_on(data[data.length-1]);
					},
					error : function(e){
						console.log(e);
					}
				});
			}
		}
		return true;
	} else {
		seatList.splice(check_seat-1,1);
		check_seat = check_seat -1;
	}//if(box.checked....의 끝
	
}

function check_modal_on(flagParam){
	if(flagParam=="0" || flagParam=="1" || flagParam=="re"){
		$("#busdate").text(busReserveInfo[0]);
		$("#busarea").text(busReserveInfo[1]);
		$("#busgrade").text(busReserveInfo[2]);
		$("#busseat").text(busReserveInfo[3]);
		$("#busprice").text(busReserveInfo[4]);
		if(flagParam=="0") $("#busflag").val("kobus");
		if(flagParam=="1") $("#busflag").val("easy");
		$('div#checkModal').modal();
	}else if(flagParam=="2"){
		$("#ktxdate").text(ktxinfos[0]);
		$("#ktxarea").text(ktxinfos[1]);
		$("#ktxgrade").text(ktxinfos[2]);
		$("#ktxseat").text(ktxinfos[3]);
		$("#ktxprice").text(ktxinfos[4]);
		$('div#trainCheckModal').modal();
	}else{
		showBusSeat();
	}		
	
}

function check_form(){
	$('div#checkModal').modal('hide');
	var time = $("#busdate").text();
	var busarea = $("#busarea").text();
	var seat = $("#busseat").text();
	
	busPaymentInfo="";
	busPaymentInfo = (time.split(" ")[1]+"_"+busarea+"_"+seat+"_"+time.split(" ")[0]+"_"+$("#busflag").val()+"_"+busReserveInfo[2]);
	$('div#payModal').modal();
}

function writeCardInfo() {
	$('div#payModal').modal('hide');
	var cardno = $("#cardno").val();
	var year = $("#validyear option:selected").val();
	var month = $("#validmonth option:selected").val();
	var birth = $("#birth").val();
	spinnerStart();
	
	$.ajax({
		type : "POST",
		url : "writeCardInfo",
		contentType : "application/json",
		data : JSON.stringify({
			"cardno" : cardno,
			"year" : year,
			"month" : month,
			"birth" : birth,
			"paymentInfo" : busPaymentInfo,
			"date" : dateNow.substring(2,8),
			"selectedFriendList" : selectedFriendList
		}),
		dataType : "json",
		success : function(data) {
			if(data) {
				spinnerEnd();
				location.href="diary";
				busReserveInfo="";
				selectedFriendList = new Array();
			} else {
				alert("결제오류. 다시 입력해주세요.");
				$('div#checkModal').modal();
				spinnerEnd();
			}
		},
		error :function(request,status,error){
	        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error); 
		}
	});
	return true;
}

function trainAreaSelect(param) {
	var train = "경부";
   	var train1 = "호남";
   	var train2 = "경전";
	var train3 = "전라";
	var train4 = "경의";
	var train5 = "충북";
	
	$(".write").on("keydown",function(){
		$(".write").popover("hide");
	});
	$(".write").on("keyup", function () {
		var html = "";

		$.ajax({
			url: "stationLocation",
			type: "post",
			dataType: "json",
			data: {
				query: $(".active .write").val()
			},
			success: function (json) {
				html += '<form>';
				html += '<select multiple class="' + param + '2">';
				if (json.display == 0) {
					html += "<option>검색결과없음</option>";
				} else {
					$.each(json.items, function (i, item) {
						if (item.title.indexOf(train) != -1 || item.title.indexOf(train1) != -1 || item.title.indexOf(train2) != -1 || item.title.indexOf(train3) != -1 || item.title.indexOf(train4) != -1 || item.title.indexOf(train5) != -1) {
							html += "<option>" + item.title + "</option>";
						} //if
					});
				}
				html += '</select>';
				html += '<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="' + param + '();return false;">';
				html += '</form>';
				$(".write").attr("data-toggle", "popover");
				$(".write").attr("title", "<img src='resources/img/icon/train.png'>");
				$(".write").attr("data-content", html);
				$(".write").attr("data-html", "true");
				$(".write").attr("data-placement", "bottom");
				$(".write").popover("show");
			},
			error: function (err) {
				console.log(err);
			}
		});
	});
}

function trainStartSelect(){
	$(".write").popover("destroy");
	$(".write").off("keyup");
	$(".write").off("keydown");
	$(".write").popover("destroy");
	var startTrain = $(".trainStartSelect2 option:selected").val();
	for(var i=0; i<startTrain.length ;i++){
		if(startTrain.charAt(i)=="역"){
			start = startTrain.substring(0,i);
			break;
		}//if
	}
	showTextBlock();
	inputText(start);
}

function trainDestSelect(){
	$(".write").popover("destroy");
	$(".write").off("keyup");
	$(".write").off("keydown");
	$(".write").popover("destroy");
	var destTrain = $(".trainDestSelect2 option:selected").val();
	for(var i=0; i<destTrain.length ;i++){
		if(destTrain.charAt(i)=="역"){
			dest = destTrain.substring(0,i);
			break;
		}//if
	}
	
	showTextBlock();
	$.ajax({
		type : "GET",
		url : "setStartAndDestPoint",
		data : {
			area1 : start,
			area2 : dest
		},
		success : function(data){
			inputText(dest);
		},
		error : function(e){
			console.log(e);
		}
	});
	
}

function showTrainTime(){
	$(".write").popover("destroy");
	$(".write").off("keyup");
	$(".write").off("keydown");
	$(".write").popover("destroy");
	var count = $(".active .trainCountSelect option:selected").val();
	showTextBlock();
	$.ajax({
		type : "get",
		url : "setPeople",
		data : {
			count : count
		},
		success : function(){
			inputText(count+"명");
		},
		error : function(e){
			console.log(e);
		}
	});
}

function showTimeModal(){
	var wantTrainTime = $(".active .trainTimeSelect option:selected").val();
	var dateTime = dateNow + (wantTrainTime+"00");
	$("#TrainTimeModal").modal('hide');
	$.ajax({
		type : "get",
		url : "setDate",
		data : {
			date : dateTime
		},
		dataType : "json",
		success : function(data){
			 	var html="";
				html += '<form>';
				html += '<select multiple class="realTimeSelect">';
				 $.each(data,function(i,item){
					 for(var i=0;i<item.split("/").length ; i++){
						 html+='<option>'+item.split("/")[i++] +" "+ item.split("/")[i]+'</option>';
					 }
			      }); //each
				 
				html +='</select>';
				html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="realTrainTimeSelect();return false;">';
				html +='</form>';
				
				$(".write").attr("data-toggle", "popover");
				$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
				$(".write").attr("data-content", html);
				$(".write").attr("data-html", "true");
				$(".write").attr("data-placement", "bottom");
				$(".write").popover("show");
		},
		error : function(e){
			console.log(e);
		}
	});
}

function mvCancel(num){
	spinnerStart();
	$.ajax({
		type : "get",
		url : "cancelMovieCGV",
		data : {
			mvtime : mvtimecancel[num],
			mvname : mvnamecancel[num],
			scno : mvscno[num]
		},
		success : function(data){
			if(data){
				location.href="diary";
				spinnerEnd();
			}else{
				alert('취소도중 오류발생 다시시도 해주세요.');
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function trainCancelModal(num){
	spinnerStart();
	$.ajax({
		type : "POST",
		url : "cancelLoginCheckKTX",
		data : {
		},
		success : function(data){
			if(data){
				trainCancelFnc(num);
			}else{
				spinnerEnd();
				$("#ktxcancelFlag").val(num);
				$("#ktxCancelModal").modal();
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function trainCancelFnc(num){
	if(typeof num == "undefined" || num=='' || num==null){
		$("#ktxCancelModal").modal('hide');
		spinnerStart();
		var num2 = $("#ktxcancelFlag").val();
		var id = $("#idtrainCancel").val();
		var key = $("#pwtrainCancel").val();
		var type = $(":input:radio[name=logintypeCancel]:checked").val();
		$.ajax({
			type : "POST",
			url : "loginForCancel",
			data : {
				type : type,
				id : id,
				key : key
			},
			success : function(data){
				trainCancelFnc2(num2);
			},
			error : function(e){
				console.log(e);
			}
		});
	}else{
		trainCancelFnc2(num);
	}
}

function trainCancelFnc2(num){
	$.ajax({
		type : "POST",
		url : "cancelKTX",
		data : {
			date : traincancel[num].split(" ")[0],
			time : traincancel[num].split(" ")[1],
			scno : trainscno[num]
		},
		success : function(data){
			location.href="diary";
			spinnerEnd();
		},
		error : function(e){
			console.log(e);
		}
	});
}

function kobusCancelModal(num){
	$("#buscancelFlag").val(num);
	$("#busCancelModal").modal();
}

function kobusCancel(){
	$("#busCancelModal").modal('hide');
	spinnerStart();
	var num = $("#buscancelFlag").val();
	var cardno = $("#buscancel_card_no").val();
	var year = $("#cvalidyear option:selected").val();
	var month = $("#cvalidmonth option:selected").val();
	
	$.ajax({
		type : "get",
		url : "cancelKOBUS",
		data : {
			scno : kobusscno[num],
			cardno : cardno,
			startdate : kobuscancel[num].split("_")[0],
			time : kobuscancel[num].split("_")[1],
			area : kobuscancel[num].split("_")[2],
			year : year,
			month : month
		},
		success : function(data){
			if(data){
				location.href="diary";
				spinnerEnd();
			}else{
				alert('취소도중 오류발생 다시시도 해주세요.');
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function directMVListSelect(){
	$(".write").popover("destroy");
	var movieName = $(".active .directMvList option:selected").val();
	showTextBlock();
	inputText(movieName);
	$.ajax({
		type : "get",
		url : "setmovie",
		data : {
			movie : movieName			
		},
		success : function(data){
			var html="";
			$.ajax({
				type : "get",
				url : "gettime",
				data : {
				},
				success : function(data){
					 html += '<form>';
				     html += '<select multiple class="movieTimeSelect2">';
				     $.each(data,function(i,item){
				            html+='<option>'+item+'</option>';
				      }); //each
				       html+='</select>';
				       html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="settime2();return false;">';
				       html+='</form>';
				       if(movieflag!=1){
						 $(".write").attr("data-toggle", "popover");
						 $(".write").attr("title", "<img src='resources/img/icon/time.png'>");
						 $(".write").attr("data-content", html);
						 $(".write").attr("data-html", "true");
						 $(".write").attr("data-placement", "bottom");
						 $(".write").popover("show");
				       }	
				},
				error : function(request,status,error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
		},
		error : function(e){
			console.log(e);
		}
	});
}

function settime2(){
	var movieTime = $(".active .movieTimeSelect2 option:selected").val();
	$(".write").popover("destroy");
	showTextBlock();
	$.ajax({
		type : "get",
		url : "settime",
		data : {
			time : movieTime			
		},
		success : function(data){
			var html="";
			html += '<form>';
			html += '<select multiple class="movieCountSelect">';
			for(var i=1;i<9;i++){
				html+="<option>"+i+"</option>"
			}
			html +='</select>';
			html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="getseat();return false;">';
			html +='</form>';
			$(".write").attr("data-toggle", "popover");
			$(".write").attr("title", "<img src='resources/img/icon/people.png'>");
			$(".write").attr("data-content", html);
			$(".write").attr("data-html", "true");
			$(".write").attr("data-placement", "bottom");
			$(".write").popover("show");
		},
		error : function(e){
			console.log(e);
		}
	});
	
}

function directMVTimeSelect(){
	$(".write").popover("destroy");
	showTextBlock();
	$.ajax({
		type : "get",
		url : "settime",
		data : {
			time : $(".active .directMvTimeList option:selected").val()			
		},
		success : function(data){
			var html="";
			html += '<form>';
			html += '<select multiple class="movieCountSelect">';
			for(var i=1;i<9;i++){
				html+="<option>"+i+"</option>"
			}
			html +='</select>';
			html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="getseat();return false;">';
			html +='</form>';
			$(".write").attr("data-toggle", "popover");
			$(".write").attr("title", "<img src='resources/img/icon/people.png'>");
			$(".write").attr("data-content", html);
			$(".write").attr("data-html", "true");
			$(".write").attr("data-placement", "bottom");
			$(".write").popover("show");
		},
		error : function(e){
			console.log(e);
		}
	});
}

function directTastyListSelect(num){
	$("#tastyModal").modal('hide');
	tasteInfo = $("#tastyselect"+num).attr('value');

	var html="";
	 if(returnData.FLAG=='movie'){
		   if(returnData.MVLIST!=''){
				html += '<select multiple class="directMvList">';
		            $.each(returnData.MVLIST,function(i,item){
		            	    html += "<option>"+item+"</option>";
		               });
		        	html +='</select>';
 	           html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="directMVListSelect();return false;">';
 	           	
 	    		$(".write").attr("data-toggle", "popover");
 	    		$(".write").attr("title", "<img src='resources/img/icon/show.png'>");
 	    		$(".write").attr("data-content", html);
 	    		$(".write").attr("data-html", "true");
 	    		$(".write").attr("data-placement", "bottom");
 	    		$(".write").popover("show");  
	           	}
		   if(returnData.MVTIME!=''){
			   html += '<select multiple class="directMvTimeList">';
			   $.each(returnData.MVTIME,function(i,item){
            	    html += "<option>"+item+"</option>";
               });
        		html +='</select>';
	           	html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="directMVTimeSelect();return false;">';
	           	
	    		$(".write").attr("data-toggle", "popover");
	    		$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
	    		$(".write").attr("data-content", html);
	    		$(".write").attr("data-html", "true");
	    		$(".write").attr("data-placement", "bottom");
	    		$(".write").popover("show");
		   }
	 }
}

function ccCancel(num){
	$.ajax({
		type : "get",
		url : "cancelCommonSC",
		data : {
			scno : ccscno[num]
		},
		success : function(data){
			if(data){
				location.href="diary";
				spinnerEnd();
			}else{
				alert('취소도중 오류발생 다시시도 해주세요.');
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function defaultCancel(num) {
	$.ajax({
		type : "get",
		url : "defaultCancel",
		data : {
			scno : defaultscno[num]
		},
		success : function(data) {
			if (data) {
				location.href = "diary";
			} else {
				alert("취소 도중 오류발생 다시 시도 해주세요.");
			}
		},
		error : function (e) {
			console.log(e);
		}
	});
}


function tastyListSelect(num){
	$("#tastyModal").modal('hide');
	var html="";

	tasteInfo = $("#tastyselect"+num).attr('value');
	
	html += '<form>';
	html += '<select multiple class="tastyTimeSelect">';
	for(var i=1;i<=24;i++){
		if(i<10)
			html+="<option>0"+i+":00</option>";
		else if(i>10)
			html+="<option>"+i+":00</option>";	
	}
	html +='</select>';
			html +='<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="tastySettime();return false;">';
	html +='</form>';
	$(".write").attr("data-toggle", "popover");
	$(".write").attr("title", "<img src='resources/img/icon/time.png'>");
	$(".write").attr("data-content", html);
	$(".write").attr("data-html", "true");
	$(".write").attr("data-placement", "bottom");
	$(".write").popover("show");
		
}

function tastySettime(){
	$(".write").popover("destroy");
	spinnerStart();
	var time = $(".active .tastyTimeSelect option:selected").val();
	
	$.ajax({
		type : "post",
		url : "commonsc",
		contentType : "application/json",
		data : JSON.stringify({
			"text" : tasteInfo.split("`")[0],
			"link" : tasteInfo.split("`")[1],
			"telephone" : tasteInfo.split("`")[2],
			"date" : dateNow.substring(2,8),
			"flag" : "common",
			"time" : time,
			"original" : foodInput[0],
			"selectedFriendList" : selectedFriendList
		}),
		success : function(data) {
			location.href="diary";
			spinnerEnd();
		},
		error : function(e) {
			console.log(e);
		}
	});
		
}

function realTrainTimeSelect(){
	var time =  $(".active .realTimeSelect option:selected").val();
	hideTextBlock();

	$.ajax({
		type : "get",
		url : "setKTXSeat",
		data : {
			date : time.split(" ")[1]
		},
		success : function(data){
			if(data){
				$("#trainLoginModal").modal();
			}else{
				alert("죄송합니다. 해당 열차는 매진입니다. 다시 선택해주세요.");
			}
		},
		error : function(e){
			console.log(e);
		}
	});
}

function loginTrain(){
	spinnerStart();
	$("#trainLoginModal").modal("hide");
	var id = $("#idtrain").val();
	var pw = $("#pwtrain").val();
	var type = $(":input:radio[name=logintype]:checked").val();

	$.ajax({
		type : "POST",
		url : "loginTrain",
		data : {
			type : type,
			trid : id,
			trkey : pw
		},
		success : function(data){
			spinnerEnd();
			ktxinfos = data;
			check_modal_on(data[data.length-1]);
		},
		error : function(e){
			console.log(e);
		}
	});
}

function checkKtx_form(){
	$('div#trainCheckModal').modal('hide');
	
	var time = $("#ktxdate").text();
	var ktxarea = $("#ktxarea").text();
	var seat = $("#ktxseat").text();
	ktxPaymentInfo="";
	ktxPaymentInfo += (time.split(" ")[1]+"_"+ktxarea+"_"+seat+"_"+time.split(" ")[0]+"_ktx");
	$('div#KTXpayModal').modal();
}

function KTXwriteCardInfo(){
	$('div#KTXpayModal').modal('hide');
	var type = $(":input:radio[name=cardtype]:checked").val();
	var cardno = $("#cardnoktx").val();
	var year = $(".validyear option:selected").val();
	var month = $(".validmonth option:selected").val();
	var key = $("#pwktx").val();
	var birth = $("#birthktx").val();
	spinnerStart();
	
	$.ajax({
		type : "POST",
		url : "KTXwriteCardInfo",
		contentType : "application/json",
		data : JSON.stringify({
			"type" : type, 
			"cardno" : cardno,
			"year" : year,
			"month" : month,
			"key" : key,
			"birth" : birth,
			"paymentInfo" : ktxPaymentInfo,
			"date" : dateNow.substring(2,8),
			"selectedFriendList" : selectedFriendList
		}),
		dataType : "json",
		success : function(data) {
			if(data) {
				spinnerEnd();
				selectedFriendList = new Array();
				location.href="diary";
			} else {
				alert("결제오류. 다시 입력해주세요.");
				$('div#KTXpayModal').modal();
				spinnerEnd();
			}
		},
		error: function (request, status, error) {
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
		}
	});
	return true;
}
