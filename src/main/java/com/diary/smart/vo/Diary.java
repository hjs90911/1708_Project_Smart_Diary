package com.diary.smart.vo;

import java.util.ArrayList;

public class Diary {

	private int sc_no_pk; // 일정의 일정번호
	private int user_no_fk; // 회원의 회원번호
	private String sc_stdt; // 일정이 시작하는 날짜 YYMMDD HH24MISS
	private String sc_endt; // 일정이 끝나는 날짜
	private String sc_con; // 일정 내용 정보 (장소, 시간, 목적 등)
	private String sc_wt; // 날씨 정보 SU:맑음, CL:흐림, RA:비, SN:눈, DU:미세먼지
	private String sc_fin; // 결제완료 정보 컬럼 완료시 Y 아니면 N
	private String sc_insdt; // 최조 등록일자 YYMMDD HH24MISS
	private String sc_dflag;
	private String sc_updt; // 수정날짜 YYMMDD HH24MISS
	private String sc_ddt;
	private ArrayList<Integer> scfriendno; // 일정에 동행하는 친구 번호 정보.

	public Diary() {
		// TODO Auto-generated constructor stub
	}

	public Diary(int sc_no_pk, int user_no_fk, String sc_stdt, String sc_endt, String sc_con, String sc_wt,
			String sc_fin, String sc_insdt, String sc_dflag, String sc_updt, String sc_ddt,
			ArrayList<Integer> scfriendno) {
		super();
		this.sc_no_pk = sc_no_pk;
		this.user_no_fk = user_no_fk;
		this.sc_stdt = sc_stdt;
		this.sc_endt = sc_endt;
		this.sc_con = sc_con;
		this.sc_wt = sc_wt;
		this.sc_fin = sc_fin;
		this.sc_insdt = sc_insdt;
		this.sc_dflag = sc_dflag;
		this.sc_updt = sc_updt;
		this.sc_ddt = sc_ddt;
		this.scfriendno = scfriendno;
	}

	public int getSc_no_pk() {
		return sc_no_pk;
	}

	public void setSc_no_pk(int sc_no_pk) {
		this.sc_no_pk = sc_no_pk;
	}

	public int getUser_no_fk() {
		return user_no_fk;
	}

	public void setUser_no_fk(int user_no_fk) {
		this.user_no_fk = user_no_fk;
	}

	public String getSc_stdt() {
		return sc_stdt;
	}

	public void setSc_stdt(String sc_stdt) {
		this.sc_stdt = sc_stdt;
	}

	public String getSc_endt() {
		return sc_endt;
	}

	public void setSc_endt(String sc_endt) {
		this.sc_endt = sc_endt;
	}

	public String getSc_con() {
		return sc_con;
	}

	public void setSc_con(String sc_con) {
		this.sc_con = sc_con;
	}

	public String getSc_wt() {
		return sc_wt;
	}

	public void setSc_wt(String sc_wt) {
		this.sc_wt = sc_wt;
	}

	public String getSc_fin() {
		return sc_fin;
	}

	public void setSc_fin(String sc_fin) {
		this.sc_fin = sc_fin;
	}

	public String getSc_insdt() {
		return sc_insdt;
	}

	public void setSc_insdt(String sc_insdt) {
		this.sc_insdt = sc_insdt;
	}

	public String getSc_dflag() {
		return sc_dflag;
	}

	public void setSc_dflag(String sc_dflag) {
		this.sc_dflag = sc_dflag;
	}

	public String getSc_updt() {
		return sc_updt;
	}

	public void setSc_updt(String sc_updt) {
		this.sc_updt = sc_updt;
	}

	public String getSc_ddt() {
		return sc_ddt;
	}

	public void setSc_ddt(String sc_ddt) {
		this.sc_ddt = sc_ddt;
	}

	public ArrayList<Integer> getScfriendno() {
		return scfriendno;
	}

	public void setScfriendno(ArrayList<Integer> scfriendno) {
		this.scfriendno = scfriendno;
	}

	@Override
	public String toString() {
		return "Diary [sc_no_pk=" + sc_no_pk + ", user_no_fk=" + user_no_fk + ", sc_stdt=" + sc_stdt + ", sc_endt="
				+ sc_endt + ", sc_con=" + sc_con + ", sc_wt=" + sc_wt + ", sc_fin=" + sc_fin + ", sc_insdt=" + sc_insdt
				+ ", sc_dflag=" + sc_dflag + ", sc_updt=" + sc_updt + ", sc_ddt=" + sc_ddt + ", scfriendno="
				+ scfriendno + "]";
	}

}
