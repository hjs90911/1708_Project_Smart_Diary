
/* Drop Tables */

DROP TABLE ID_SCFRNO_TB CASCADE CONSTRAINTS;
DROP TABLE ID_SC_TB CASCADE CONSTRAINTS;
DROP TABLE ID_USERFRNO_TB CASCADE CONSTRAINTS;
DROP TABLE ID_USER_TB CASCADE CONSTRAINTS;


/* Drop Sequences */

DROP SEQUENCE SEQ_ID_USER_TB_user_no_pk;
DROP SEQUENCE SEQ_ID_SC_TB_sc_no_pk;


/* Create Sequences */

CREATE SEQUENCE SEQ_ID_SC_TB_sc_no_pk INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_ID_USER_TB_user_no_pk INCREMENT BY 1 START WITH 1;


/* Create Tables */

CREATE TABLE ID_SCFRNO_TB      -- 일정에 동행하는 친구정보 테이블
(	
	sc_no_fk number NOT NULL,    -- 일정의 일정번호
	user_no_fk number NOT NULL,  -- 회원번호
	sc_frno number 			     -- 일정에 동행하는 친구 idno 정보
);


CREATE TABLE ID_SC_TB						 	 -- 일정 테이블
(
	sc_no_pk number NOT NULL,        		 	 -- 일정의 일정번호
	user_no_fk number NOT NULL,      		 	 -- 회원의 회원번호   
	sc_stdt date NOT NULL,             		 	 -- 일정의 날짜  YYMMDD HH24MISS
	sc_con varchar2(4000) NOT NULL,  		 	 -- 일정 내용 정보 (장소, 시간, 목적 등)
	sc_wt varchar2(20) NOT NULL,     	         -- 날씨 정보  SU:맑음, CL:흐림, RA:비, SN:눈, DU:미세먼지
	sc_fin varchar2(20) DEFAULT 'N' NOT NULL,    -- 결제완료 정보 컬럼  완료시 Y 아니면 N
	sc_insdt date DEFAULT SYSDATE NOT NULL,      -- 최조 등록일자  YYMMDD HH24MISS
	sc_dflag varchar2(20) DEFAULT 'Y' NOT NULL,  -- 삭제플래그 삭제는 N 아니면 Y
	sc_updt date DEFAULT SYSDATE, 				 -- 수정날짜  YYMMDD HH24MISS
	sc_ddt date,  				 			     -- 일정 삭제 날짜  YYMMDD HH24MISS
	PRIMARY KEY (sc_no_pk, user_no_fk)
);


CREATE TABLE ID_USERFRNO_TB		 -- 회원의 친구정보 테이블
(
	user_no_fk number NOT NULL,  -- 회원의 회원번호
	user_frno number NOT NULL,   -- 친구 아이디 번호
	fr_flag varchar2(1) default 'N' not null, -- 친구 플래그 서로친구면 Y 아니면 N
	PRIMARY KEY (user_no_fk, user_frno)
);


CREATE TABLE ID_USER_TB								-- 회원 테이블
(
	user_no_pk number NOT NULL,  			 		-- 회원의 회원번호
	user_id varchar2(50) NOT NULL UNIQUE,  			-- 회원의 아이디 아이디는 현존하는 이메일주소로 한다.(50자 미만)
	user_pw varchar2(16) NOT NULL,  				-- 비밀번호는 대소문자 및 특수문자 포함이다.(최소 6자 이상 16자 이하)
	user_nm varchar2(20) NOT NULL,  				-- 회원의 이름
	user_birth varchar2(6) NOT NULL,  				-- 회원의 생년월일 정보 (형식은 YYMMDD 6자)
	user_phone varchar2(20) NOT NULL,  	 			-- 핸드폰 번호 ex)01011111111 (only number)
	user_mvseat varchar2(20),  						-- 영화관 좌석 설정정보 L:왼쪽 R:오른쪽 M:가운데
	user_jodt date DEFAULT SYSDATE NOT NULL,  		-- 가입날짜  YYMMDD HH24MISS
	user_aflag number DEFAULT '0' NOT NULL,			-- 메일인증 플래그 인증은 1 아니면 0
	user_dflag varchar2(20) DEFAULT 'Y' NOT NULL,   -- 탈퇴플래그 탈퇴회원은 N 현재 회원은 Y
	user_add1 varchar2(120) NOT NULL,  				-- 첫번째 주소 정보(시,군,구)
	user_add2 varchar2(120),  					    -- 2번째 주소정보(시,군,구)
	user_add3 varchar2(120),  					    -- 3번째 주소정보(시,군,구)
	user_ddt date,  			   					-- 회원 탈퇴 날짜  YYMMDD HH24MISS
	PRIMARY KEY (user_no_pk)
);


/* Create Foreign Keys */

ALTER TABLE ID_SCFRNO_TB ADD
CONSTRAINT ID_SCFRNO_TB_FK FOREIGN KEY (sc_no_fk, user_no_fk)
REFERENCES ID_SC_TB (sc_no_pk, user_no_fk);

ALTER TABLE ID_SC_TB
	ADD FOREIGN KEY (user_no_fk)
	REFERENCES ID_USER_TB (user_no_pk)
;

ALTER TABLE ID_USERFRNO_TB
	ADD CONSTRAINT ID_USERFRNO_TB_FK FOREIGN KEY (user_no_fk)
REFERENCES ID_USER_TB (user_no_pk)
;

