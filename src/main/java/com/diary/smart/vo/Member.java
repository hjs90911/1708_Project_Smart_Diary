package com.diary.smart.vo;

import java.util.ArrayList;

public class Member {
	private int user_no_pk;		   //회원번호
	private int user_aflag;			//인증된 회원 구분 (인증된 회원:1)
	private String user_id;         //회원의 아이디 아이디는 현존하는 이메일주소로 한다.(50자 미만)
	private String user_pw;         //비밀번호는 대소문자 및 특수문자 포함이다.(최소 6자 이상 16자 이하)
	private String user_nm;       //회원의 이름
	private String user_birth;      //회원의 생년월일 정보 (형식은 YYMMDD 6자)
	private String user_phone;		   //핸드폰 번호 ex)01011111111 (only number)
	private String user_mvseat;     //영화관 좌석 설정정보 가운데 기준으로.. 
	                           //CC:가운데  NN:뒤쪽  SS:앞쪽  EE:오른쪽  WW:왼쪽
							   //NE:오른쪽뒤 NW:왼쪽뒤 SE:오른쪽앞 SW:오른쪽뒤  8군데
	private String user_jodt;   //가입날짜 YYMMDD HH24MISS
	private String user_dflag;
	private String user_add1;   //첫번째 주소 정보(시,군,구)
	private String user_add2;   //두번째 주소 정보(시,군,구)
	private String user_add3;   //세번째 주소 정보(시,군,구)
	private String user_ddt;
	private ArrayList<Integer> friendno;  //회원의 친구 번호 정보.
		   			
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int user_no_pk, int user_aflag, String user_id, String user_pw, String user_nm, String user_birth, String user_phone,
			String user_mvseat, String user_jodt, String user_dflag, String user_add1, String user_add2,
			String user_add3, String user_ddt, ArrayList<Integer> friendno) {
		super();
		this.user_no_pk = user_no_pk;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_nm = user_nm;
		this.user_birth = user_birth;
		this.user_phone = user_phone;
		this.user_mvseat = user_mvseat;
		this.user_jodt = user_jodt;
		this.user_add1 = user_add1;
		this.user_add2 = user_add2;
		this.user_add3 = user_add3;
		this.user_aflag = user_aflag;
		this.friendno = friendno;
	}

	public int getUser_no_pk() {
		return user_no_pk;
	}

	public void setUser_no_pk(int user_no_pk) {
		this.user_no_pk = user_no_pk;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_mvseat() {
		return user_mvseat;
	}

	public void setUser_mvseat(String user_mvseat) {
		this.user_mvseat = user_mvseat;
	}

	public String getUser_jodt() {
		return user_jodt;
	}

	public void setUser_jodt(String user_jodt) {
		this.user_jodt = user_jodt;
	}
	
	public String getUser_dflag() {
		return user_dflag;
	}
	
	public void setUser_dflag(String user_dflag) {
		this.user_dflag = user_dflag;
	}

	public String getUser_add1() {
		return user_add1;
	}

	public void setUser_add1(String user_add1) {
		this.user_add1 = user_add1;
	}

	public String getUser_add2() {
		return user_add2;
	}

	public void setUser_add2(String user_add2) {
		this.user_add2 = user_add2;
	}

	public String getUser_add3() {
		return user_add3;
	}

	public void setUser_add3(String user_add3) {
		this.user_add3 = user_add3;
	}
	
	public String getUser_ddt(){
		return user_ddt;
	}
	
	public void setUser_ddt(String user_ddt){
		this.user_ddt = user_ddt;
	}
	
	public int getUser_aflag() {
		return user_aflag;
	}

	public void setUser_aflag(int user_aflag) {
		this.user_aflag = user_aflag;
	}

	public ArrayList<Integer> getFriendno() {
		return friendno;
	}

	public void setFriendno(ArrayList<Integer> friendno) {
		this.friendno = friendno;
	}

	@Override
	public String toString() {
		return "Member [user_no_pk=" + user_no_pk + ", user_id=" + user_id + ", user_pw=" + user_pw + ", user_nm="
				+ user_nm + ", user_birth=" + user_birth + ", user_phone=" + user_phone + ", user_mvseat=" + user_mvseat
				+ ", user_jodt=" + user_jodt + ", user_dflag= " + user_dflag + ", user_transeat=" + ", user_add1=" + user_add1
				+ ", user_add2=" + user_add2 + ", user_add3=" + user_add3 + ", user_ddt=" + user_ddt + ", friendno=" + friendno + "]";
	}
	
}
