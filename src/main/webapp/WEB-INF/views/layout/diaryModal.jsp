<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- MOVIESEAT MODAL START -->
<div class="modal movieseat fade" id="wrap" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<!-- 컨텐츠 -->
	<div id="container">
		<!-- 빠른예매 -->
		<div id="ticket" class="ticket ko guest">
			<!-- 메인컨텐츠 -->
			<div class="steps">
				<!-- step2 -->
				<div class="step step2" style="display: block;">
					<!-- SEAT 섹션 -->
					<div class="section section-seat three_line">
						<div class="col-head" id="skip_seat_list">
							<a href="#" class="skip_to_something"
								onclick="skipToSomething('tnb_step_btn_right');return false;">인원/좌석선택
								건너뛰기</a> <a href="javascript:void(0)" id="reservarionDiscountInfo"
								style="position: absolute; top: 3px; left: 15px; color: rgb(255, 255, 255); font-weight: bold;"></a>
						</div>
						<div class="col-body">
							<!-- THEATER -->
							<div class="theater_minimap">
								<div class="theater nano has-scrollbar" id="seat_minimap_nano">
									<div class="content" id="seat" tabindex="-1"
										style="right: -17px; bottom: -17px;">
										<div class="screen" title="SCREEN" style="width: 652px;">
											<span class="text"></span>
										</div>
										<div id="test"></div>
									</div>
									<div class="pane pane-y"
										style="display: none; opacity: 1; visibility: visible;">
										<div class="slider slider-y" style="height: 50px;"></div>
									</div>
									<div class="pane pane-x"
										style="display: none; opacity: 1; visibility: visible;">
										<div class="slider slider-x" style="width: 50px;"></div>
									</div>
								</div>
								<div class="minimap opened" id="minimap" style="display: none;">
									<div class="mini_header"
										onclick="ftSeatMinimapToggle();event.preventDefault();">
										Minimap<span></span>
									</div>
									<div class="mini_container" style="width: 94px; height: 92px;">
										<div class="mini_screen">SCREEN</div>
										<div class="mini_seats">
											<div class="mini_seat" style="left: 0px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 4px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat handicap" style="left: 44px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat handicap" style="left: 48px; top: 0px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 0px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 4px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 4px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 0px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 4px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 8px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 0px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 4px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 12px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 0px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 4px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 8px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 12px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 16px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 20px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 24px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 28px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 32px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 36px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 40px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 44px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 48px; top: 16px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 0px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 4px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 20px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 24px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 28px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 32px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 16px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat reserved" style="left: 20px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 36px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 56px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 60px; top: 40px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 8px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 12px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 16px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 20px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 24px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 28px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 32px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 36px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 40px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 44px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat" style="left: 48px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 56px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 60px; top: 44px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 8px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 12px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 16px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 20px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 24px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 28px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 32px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 36px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 40px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 44px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 56px; top: 48px;">
												<span></span>
											</div>
											<div class="mini_seat sweet" style="left: 60px; top: 48px;">
												<span></span>
											</div>
										</div>
										<div class="mini_exits">
											<div class="mini_exit tr"></div>
										</div>
									</div>
									<div class="mini_region"
										style="height: 92px; width: 94px; top: 25px; left: 5px;">
										<span></span>
									</div>
								</div>
								<div class="legend" style="width: 110px;">
									<div class="buttons"></div>
									<div class="seat-icon-desc">
										<span class="icon selected"><span class="icon"></span>선택</span> <span
											class="icon reserved"><span class="icon"></span>예매완료</span> <span
											class="icon notavail"><span class="icon"></span>선택불가</span>
									</div>
									<div class="seat-type">
										<span class="radiobutton type-rating_prime" title="Prime 석"
											style="display: block;">Prime Zone<span class="icon"></span></span><span
											class="radiobutton type-rating_comfort" title="Standard 석"
											style="display: block;">Standard Zone<span class="icon"></span></span><span
											class="radiobutton type-rating_economy" title="Economy 석"
											style="display: block;">Economy Zone<span class="icon"></span></span> <span
											class="radiobutton type-normal" style="display: none;"><span
											class="icon"></span>일반석</span> <span class="radiobutton type-couple"
											title="연인, 가족, 친구를 위한 둘만의 좌석" style="display: none;"><span
											class="icon"></span>커플석</span> <span class="radiobutton type-handicap"
											style="display: block;"><span class="icon"></span>장애인석</span> <span
											class="radiobutton type-sweetbox" title="국내 최대 넓이의 프리미엄 커플좌석"
											style="display: block;"><span class="icon"></span>SWEETBOX</span> <span
											class="radiobutton type-veatbox" title="음향 진동 시스템이 적용된 특별좌석"
											style="display: none;"><span class="icon"></span>VEATBOX</span> <span
											class="radiobutton type-4d" title="바람, 진동 등 오감으로 영화 관람, 4DX"
											style="display: none;"><span class="icon"></span>4DX</span> <span
											class="radiobutton type-widebox" title="일반석보다 더 넓고 편안한 좌석"
											style="display: none;"><span class="icon"></span>WIDEBOX</span> <span
											class="radiobutton type-cinekids last" title="365일 어린이 전용 상영관"
											style="display: none;"><span class="icon"></span>CINEKIDS</span>
									</div>

								</div>

							</div>
							<div class="mouse_block"></div>
						</div>
					</div>
				</div>
				<!-- //step2 -->
				<!-- //빠른예매 -->
			</div>
		</div>
	</div>
</div>
<!-- MOVIESEAT MODAL END -->
<!-- MOVIE RESERVATIONCONFRIM MODAL START -->
<div class="modal fade" id="checkMvModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<p class="modal-title" id="myModalLabel"><img src='resources/img/icon/success.png' width='30px' height='30px'></p>
			</div>
			<div class="modal-body">
				<form>
				
					<div class="mvdate">
						<div id="scdate1" class="mvinfoMenu">TIME</div>
						<div id="scdate" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="mvname">				
						<div id="mvname1" class="mvinfoMenu">TITLE</div> 
						<div id="mvname" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="mvarea">
						<div id="mvarea1" class="mvinfoMenu">AREA</div>
						<div id="mvarea" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="mvinfo">
						<div id="mvinfo1" class="mvinfoMenu">INFORMATION</div>
						<div id="mvinfo" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="people">
						<div id="people1" class="mvinfoMenu">THE NUMBER OF PEOPLE</div>
						<div id="people" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="seatinfo">
						<div id="seatinfo1" class="mvinfoMenu">SEAT INFORMATION</div>
						<div id="seatinfo" class="mvinfoSet"></div>
					</div>
					<br>
					<div class="price">
						<div id="price1" class="mvinfoMenu">PRICE</div>
						<div id="price" class="mvinfoSet"></div>
					</div>
					<br>
					
					<div class="confirm" >
						<input type="button" class="form-control" onclick="return check_movieform()" value="CONFIRM">
					</div>					
				</form>
			</div>
		</div>
	</div>
</div>
<!-- MOVIE RESERVATIONCONFRIM MODAL END -->
<!-- MOVIE PAYMENT MODAL START -->
<div class="modal fade" id="MoviePayModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/credit-card.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>						
				<div class="form-group">
						<label for="normal">CREDIT CARD TYPE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <select id="lp_card_type_mv"><option selected="selected">카드를 선택하세요</option><option>BC카드</option><option>국민카드</option><option>삼성카드(올앳포함)</option><option>신한카드</option><option>현대카드</option><option>KEB하나카드(구,외환)</option><option>우리(평화)카드</option><option>롯데/아멕스카드</option><option>시티카드(구,한미)</option><option>신세계카드</option><option>NH카드</option><option>하나카드(구,하나SK)</option><option>광주VISA카드</option><option>산은캐피탈</option><option>수협카드</option><option>KDB산업은행카드</option><option>전북은행카드</option><option>제주은행카드</option><option>우체국카드</option><option>스탠다드차타드은행카드</option><option>MG체크카드</option><option>현대증권카드</option><option>기업은행카드</option></select> 
					</div>
					<div class="form-group">
						<label for="cardno">CREDIT CARD NUMBER</label> <input type="password" class="form-control" id="cardnomv"
							name="cardnomv" placeholder="숫자만 입력하세요. 예)0000111122223333">
					</div>
					<div class="form-group">
						<label for="validdate">PASSWORD (FORNT 2 DIGITS)</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="password" class="form-control" id="pwmv"
							name="pwmv">

					</div>
					<div class="form-group">
						<label for="validdate">EXPIRATION DATE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<select id="validyearmv" name="validyear">
							<option value='17'>2017년</option> <option value='18'>2018년</option> <option value='19'>2019년</option>
							<option value='20'>2020년</option> <option value='21'>2021년</option> <option value='22'>2022년</option>
							<option value='23'>2023년</option> <option value='24'>2024년</option> <option value='25'>2025년</option>
							<option value='26'>2026년</option> <option value='27'>2027년</option> <option value='28'>2028년</option>
							<option value='29'>2029년</option> <option value='30'>2030년</option> <option value='31'>2031년</option>
							<option value='32'>2032년</option> <option value='33'>2033년</option> <option value='34'>2034년</option>
							<option value='35'>2035년</option> <option value='36'>2036년</option>
							</select>
							<select id="validmonthmv" name="validmonth">
							<option value='01'>1월</option> <option value='02'>2월</option> <option value='03'>3월</option>
							<option value='04'>4월</option> <option value='05'>5월</option> <option value='06'>6월</option>
							<option value='07'>7월</option> <option value='08'>8월</option> <option value='09'>9월</option>
							<option value='10'>10월</option> <option value='11'>11월</option> <option value='12'>12월</option>
							</select>
					</div>
					<div class="form-group">
						<label for="cardno">BIRTH</label> <input type="password" class="form-control" id="birthmv"
							name="bitrhmv" placeholder="예)990201">
					</div>
					<div class="form-group">
						<input type="button" class="form-control" id='mvpaymentbtn' onclick="return payment();" value="예매">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- MOVIE PAYMENT MODAL END -->
<!-- BUS SEAT MODAL START -->
<div id="seatModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/seat.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body2">
			
			</div>
		</div>
	</div>
	
</div>
<!-- BUS SEAT MODAL END -->
<!-- BUS RESERVATIONCONFIRM MODAL START -->
<div class="modal fade" id="checkModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/success.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<label class = "businfoMenu" for="normal">THE TIME OF THE DEPARTURE</label>
						<h3 class = "businfoSet" id="busdate"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="cardno">DEPARTURE / ARRIVAL</label> 
						<h3 class = "businfoSet" id="busarea"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">BUS GRADE</label> 
						<h3 class = "businfoSet" id="busgrade"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">SEAT</label> 
						<h3 class = "businfoSet" id="busseat"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">PRICE</label> 
						<h3 class = "businfoSet" id="busprice"></h3>
					</div>
					<div class="form-group">
						<input type="hidden" id="busflag" value=''/>
						<input type="button" class="form-control" onclick="return check_form()" value="CONFIRM">
					</div>					
				</form>
			</div>
		</div>
	</div>
</div>
<!-- BUS RESERVATIONCONFIRM MODAL END -->
<!-- BUS TIME SELECT START -->
<div id="BusTimeModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/menu.png'  width='30px' height='30px'></h4>
			</div>
			<div class="modal-bodybus">
			
			</div>
		</div>
	</div>
</div>
<!-- BUS TIME SELECT END -->
<!-- BUS PAYMENT MODAL START -->
<div class="modal fade" id="payModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/credit-card.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<label for="normal">CREDIT CARD TYPE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="radio" id="normal" value="0" name="card" checked="checked"> COMMON
							&nbsp;&nbsp;&nbsp;
							<input type="radio" id="company"
							name="card"> BUSINESS
					</div>
					<div class="form-group">
						<label for="cardno">CREDIT CARD NUMBER</label> <input type="password" class="form-control" id="cardno"
							name="cardno" placeholder="숫자만 입력하세요. 예)0000111122223333">
					</div>
					<div class="form-group">
						<label for="validdate">EXPIRATION DATE</label> <select id="validyear" name="validyear">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<option value='2017'>2017년</option> <option value='2018'>2018년</option> <option value='2019'>2019년</option>
							<option value='2020'>2020년</option> <option value='2021'>2021년</option> <option value='2022'>2022년</option>
							<option value='2023'>2023년</option> <option value='2024'>2024년</option> <option value='2025'>2025년</option>
							<option value='2026'>2026년</option> <option value='2027'>2027년</option> <option value='2028'>2028년</option>
							<option value='2029'>2029년</option> <option value='2030'>2030년</option> <option value='2031'>2031년</option>
							<option value='2032'>2032년</option> <option value='2033'>2033년</option> <option value='2034'>2034년</option>
							<option value='2035'>2035년</option> <option value='2036'>2036년</option>
							</select>
							
							<select id="validmonth" name="validmonth">
							<option value='01'>1월</option> <option value='02'>2월</option> <option value='03'>3월</option>
							<option value='04'>4월</option> <option value='05'>5월</option> <option value='06'>6월</option>
							<option value='07'>7월</option> <option value='08'>8월</option> <option value='09'>9월</option>
							<option value='10'>10월</option> <option value='11'>11월</option> <option value='12'>12월</option>
							</select> 
					</div>
					<div class="form-group">
						<label for="cardno">BIRTH</label> <input type="password" class="form-control" id="birth"
							name="bitrh" placeholder="예)990201">
					</div>
					<div class="form-group">
						<input type="button" class="form-control" onclick="return writeCardInfo();" value="CONFIRM">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- BUS PAYMENT MODAL END -->
<!-- BUS PAYMENT CANCEL MODAL START -->
<div class="modal fade" id="busCancelModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/refund.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
					<div class="form-group">
						<label for="id">CREDIT CARD NUMBER</label> <input type="password" class="form-control" id="buscancel_card_no"
							name="buscancel_card_no" placeholder="숫자만 입력하세요. 예)0000111122223333">
							<input type="hidden" id="buscancelFlag" name="buscancelFlag" value=''/>
					</div>
					<div class="form-group">
						<label for="validdate">EXPIRATION DATE</label> <select id="cvalidyear" name="validyear">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<option value='2017'>2017년</option> <option value='2018'>2018년</option> <option value='2019'>2019년</option>
							<option value='2020'>2020년</option> <option value='2021'>2021년</option> <option value='2022'>2022년</option>
							<option value='2023'>2023년</option> <option value='2024'>2024년</option> <option value='2025'>2025년</option>
							<option value='2026'>2026년</option> <option value='2027'>2027년</option> <option value='2028'>2028년</option>
							<option value='2029'>2029년</option> <option value='2030'>2030년</option> <option value='2031'>2031년</option>
							<option value='2032'>2032년</option> <option value='2033'>2033년</option> <option value='2034'>2034년</option>
							<option value='2035'>2035년</option> <option value='2036'>2036년</option>
							</select>
							<select id="cvalidmonth" name="validmonth">
							<option value='01'>1월</option> <option value='02'>2월</option> <option value='03'>3월</option>
							<option value='04'>4월</option> <option value='05'>5월</option> <option value='06'>6월</option>
							<option value='07'>7월</option> <option value='08'>8월</option> <option value='09'>9월</option>
							<option value='10'>10월</option> <option value='11'>11월</option> <option value='12'>12월</option>
							</select>
					</div>
					<div class="form-group">
						<input type="button" class="form-control" onclick="return kobusCancel();" value="CONFIRM">
					</div>
			</div>
		</div>
	</div>
</div>
<!-- BUS PAYMENT CANCEL MODAL END -->
<!-- OISHIII SELECT MODAL START -->
<div id="tastyModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/menu.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body3">
			
			</div>
		</div>
	</div>
</div>
<!-- OISHIII SELECT MODAL END -->
<!-- KTX TIME SELECT MODAL START -->
<div id="TrainTimeModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/menu.png'  width='30px' height='30px'></h4>
			</div>
			<div class="modal-body4">
			
			</div>
		</div>
	</div>
</div>
<!-- KTX TIME SELECT MODAL END -->
<!-- KTX LOGIN MODAL START -->
<div id="trainLoginModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/login.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<label for="normal">ID TYPE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="radio" id="normal" value="1" name="logintype" checked="checked"> KORAIL
							&nbsp;&nbsp;&nbsp;
							<input type="radio" value="2" id="company"
							name="logintype"> TELEPHONE
					</div>
						<div class="form-group">
							<label for="id">ID</label> <input type="text" class="form-control"
								id="idtrain" name="idtrain"> 
						</div>
						<div class="form-group">
							<label for="id">PASSWORD</label> <input type="password"
								class="form-control" id="pwtrain" name="pwtrain">
						</div>
						<div class="form-group">
							<input type="button" class="form-control" onclick="loginTrain()" value="LOGIN">
<!-- 							<input type="image" img src="resources/img/icon/right-arrow.png" class="form-control" value="선택" onclick="loginTrain();return false;"> -->
						</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- KTX LOGIN MODAL END -->
<!-- KTX CANCEL MODAL START -->
<div class="modal fade" id="ktxCancelModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/login.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<input type="hidden" id="ktxcancelFlag" name="ktxcancelFlag" value=''/>
						<label for="normal">ID TYPE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="radio" value="1" name="logintypeCancel" checked="checked"> KORAIL
							&nbsp;&nbsp;&nbsp;
							<input type="radio" value="2" name="logintypeCancel"> Telephone
					</div>
						<div class="form-group">
							<label for="id">ID</label> <input type="text" class="form-control"
								id="idtrainCancel" name="idtrainCancel"> 
						</div>
						<div class="form-group">
							<label for="id">Password</label> <input type="password"
								class="form-control" id="pwtrainCancel" name="pwtrainCancel">
						</div>
						<div class="form-group cancle">
							<input type="button" class="form-control canclebtn" onclick="trainCancelFnc()" value="LOGIN">
						</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- KTX CANCEL MODAL END -->

<!-- KTX CONFIRM CHECK START -->
<div class="modal fade" id="trainCheckModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/success.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<label class = "businfoMenu" for="normal">THE TIME OF THE DEPARTURE</label>
						<h3 class = "businfoSet" id="ktxdate"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="cardno">DEPARTURE / ARRIVAL</label> 
						<h3 class = "businfoSet" id="ktxarea"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">KTX GRADE</label> 
						<h3 class = "businfoSet" id="ktxgrade"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">SEAT</label> 
						<h3 class = "businfoSet" id="ktxseat"></h3>
					</div>
					<div class="form-group">
						<label class = "businfoMenu" for="validdate">PRICE</label> 
						<h3 class = "businfoSet" id="ktxprice"></h3>
					</div>
					<div class="form-group">
						<input type="hidden" id="busflag" value=''/>
						<input type="button" class="form-control" onclick="return checkKtx_form()" value="CONFIRM">
					</div>					
				</form>
			</div>
		</div>
	</div>
</div>
<!-- KTX CONFIRM CHECK END -->
<!-- KTX PAYMENT MODAL START -->
<div class="modal fade" id="KTXpayModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><img src='resources/img/icon/credit-card.png' width='30px' height='30px'></h4>
			</div>
			<div class="modal-body">
				<form>
				<div class="form-group">
						<label for="normal">CREDIT CARD TYPE</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="radio" id="normal" value="0" name="cardtype" checked="checked"> COMMON
							&nbsp;&nbsp;&nbsp;
							<input type="radio" value="1" id="company"
							name="cardtype"> BUSINESS
					</div>
					<div class="form-group">
						<label for="cardno">CREDIT CARD NUMBER</label> <input type="password" class="form-control" id="cardnoktx"
							name="cardno" placeholder="숫자만 입력하세요. 예)0000111122223333">
					</div>
					<div class="form-group">
						<label for="validdate">EXPIRATION DATE</label> <select id="validyear" class="validyear" name="validyear">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<option value='2017'>2017년</option> <option value='2018'>2018년</option> <option value='2019'>2019년</option>
							<option value='2020'>2020년</option> <option value='2021'>2021년</option> <option value='2022'>2022년</option>
							<option value='2023'>2023년</option> <option value='2024'>2024년</option> <option value='2025'>2025년</option>
							<option value='2026'>2026년</option> <option value='2027'>2027년</option> <option value='2028'>2028년</option>
							<option value='2029'>2029년</option> <option value='2030'>2030년</option> <option value='2031'>2031년</option>
							<option value='2032'>2032년</option> <option value='2033'>2033년</option> <option value='2034'>2034년</option>
							<option value='2035'>2035년</option> <option value='2036'>2036년</option>
							</select>
							
							<select id="validmonth" class="validmonth" name="validmonth">
							<option value='01'>1월</option> <option value='02'>2월</option> <option value='03'>3월</option>
							<option value='04'>4월</option> <option value='05'>5월</option> <option value='06'>6월</option>
							<option value='07'>7월</option> <option value='08'>8월</option> <option value='09'>9월</option>
							<option value='10'>10월</option> <option value='11'>11월</option> <option value='12'>12월</option>
							</select> 
					</div>
					<div class="form-group">
						<label for="validdate">PASSWORD (FORNT 2 DIGITS)</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="password" class="form-control" id="pwktx"
							name="pwktx">

					</div>
					<div class="form-group">
						<label for="cardno">BIRTH</label> <input type="password" class="form-control" id="birthktx"
							name="bitrh" placeholder="예)990201">
					</div>
					<div class="form-group">
						<input type="button" class="form-control" onclick="return KTXwriteCardInfo();" value="CONFIRM">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- KTX PAYMENT MODAL END -->
