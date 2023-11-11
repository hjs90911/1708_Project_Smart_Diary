package com.diary.smart.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diary.smart.dao.DiaryDAO;
import com.diary.smart.dao.MemberDAO;
import com.diary.smart.util.CGVNavigator;
import com.diary.smart.util.SearchUtil;
import com.diary.smart.vo.Diary;
import com.diary.smart.vo.Member;

@Controller
public class WriteController {

	@Autowired
	private DiaryDAO diaryDAO;
	
	@Autowired
	private MemberDAO memberDAO;	
	
	@Autowired
	private CGVNavigator cn;	
	
	@Autowired
	private SearchUtil scutil;
	
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public void write(@RequestBody HashMap<String, Object> object, HttpSession session) {
		String sc_stdt = (String) object.get("date");
		String sc_con = (String) object.get("sc_con");
		ArrayList<String> data = (ArrayList<String>) object.get("selectedFriendList");
		int user_no_fk = memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk();
		
		ArrayList<Integer> frnoList = new ArrayList<>();
		
		for (String friend : data) {
			frnoList.add(memberDAO.selectMember(friend).getUser_no_pk());
		}
		
		Diary diary = new Diary();
		diary.setUser_no_fk(user_no_fk);
		diary.setSc_stdt(sc_stdt);
		diary.setSc_con(sc_con);
		diary.setSc_wt("SU");
		diary.setSc_fin("Y");
		
		// diary 객체의 sc_no_pk에 스케쥴 번호 넣어주기
		diaryDAO.insertDiary(diary);
		
		for (Integer frno : frnoList) {
			diaryDAO.insertCompanions(diary.getSc_no_pk(), user_no_fk, frno);
		}
	}

	@ResponseBody
	@RequestMapping(value = "directWrite", method=RequestMethod.POST)
	public HashMap<String, Object> directWrite(@RequestBody HashMap<String, Object> object, HttpSession session) {
		String date = (String) object.get("date");
		String query = (String) object.get("query");
		ArrayList<String> frList = (ArrayList<String>) object.get("selectedFriendList");
		ArrayList<Integer> frnoList = new ArrayList<>();
		
		for (String friend : frList) {
			frnoList.add(memberDAO.selectMember(friend).getUser_no_pk());
		}
		
		ArrayList<String> result = new ArrayList<>();
		HashMap<String, Object> result2 = new HashMap<String, Object>();
		HashMap<String, Object> fail = new HashMap<String, Object>();
		ArrayList<String> times = null;
		boolean flag = true;
		boolean mvflag = false;
		String mvnm = "";
		String theater = "";
		String time = "";
		result2.put("FLAG", "");
		result2.put("MVLIST", "");
		result2.put("MVTIME", "");
		result2.put("TASTY", "");
		result2.put("BUS", "");
		result2.put("KTX", "");
		result2.put("COMMON", "");
		fail.put("fail", "fail");
		ArrayList<String> mvList = cn.getMovie();
		ArrayList<String> thList = cn.getTheater();

		String[] parse = query
				.split(" |는|에서|을|를|이|가|랑|쯤|하다가|먹다가|놀다가|은|와|함께|같이|도|에|서|보고|먹고|하고|놀고|타고|먹기|보기|놀기|하기|즐기기|가서");
		for (int i = 0; i < parse.length; i++) {
			if (!flag)
				break;
			if (parse[i].contains("영화")) {
				mvflag = true;
				result2.put("FLAG", "movie");
				for (int j = 0; j < parse.length; j++) {
					if (mvnm.equals("")) {
						for (String string : mvList) {
							if (!parse[j].equals("") && string.contains(parse[j])) {
								mvnm = string;
								break;
							}
						}
					}
					if (theater.equals("")) {
						for (String string : thList) {
							if (!parse[j].equals("") && string.contains(parse[j])) {
								theater = string;
								break;
							}
						}
					}
					if (time.equals("") && (parse[j].contains("1시") || parse[j].contains("2시")
							|| parse[j].contains("3시") || parse[j].contains("4시") || parse[j].contains("5시")
							|| parse[j].contains("6시") || parse[j].contains("7시") || parse[j].contains("8시")
							|| parse[j].contains("9시") || parse[j].contains("10시") || parse[j].contains("11시")
							|| parse[j].contains("12시"))) {
						time = parse[j];
					}
					if (!mvnm.equals("") && !theater.equals("") && !time.equals("")) {
						flag = false;
						break;
					}
				} // inner for
			} // if

			if (!mvflag) {
				for (int j = 0; j < parse.length; j++) {
					if (mvnm.equals("")) {
						for (String string : mvList) {
							if (!parse[j].equals("") && string.contains(parse[j])) {
								result2.put("FLAG", "movie");
								mvnm = string;
								break;
							}
						}//inner for
					}
					if (theater.equals("")) {
						for (String string : thList) {
							if (!parse[j].equals("") && string.contains(parse[j])) {
								theater = string;
								break;
							}
						}
					}
					if (time.equals("") && (parse[j].contains("1시") || parse[j].contains("2시")
							|| parse[j].contains("3시") || parse[j].contains("4시") || parse[j].contains("5시")
							|| parse[j].contains("6시") || parse[j].contains("7시") || parse[j].contains("8시")
							|| parse[j].contains("9시") || parse[j].contains("10시") || parse[j].contains("11시")
							|| parse[j].contains("12시"))) {
						time = parse[j];
					}
					if (!mvnm.equals("") && !theater.equals("") && !time.equals("")) {
						flag = false;
						break;
					}
				} // for
			} // if

			if (!theater.equals("") && result2.get("TASTY").equals("")) {
				if (parse[i].contains("밥") || parse[i].contains("삽겹살") || parse[i].contains("국수")
						|| parse[i].contains("피자") || parse[i].contains("치킨") || parse[i].contains("맥주")
						|| parse[i].contains("술") || parse[i].contains("한식") || parse[i].contains("빕스")
						|| parse[i].contains("아웃백") || parse[i].contains("카페") || parse[i].contains("스파게티")
						|| parse[i].contains("파스타") || parse[i].contains("닭발") || parse[i].contains("짜장면")
						|| parse[i].contains("중국") || parse[i].contains("탕수육") || parse[i].contains("쌀국수")
						|| parse[i].contains("돈가스") || parse[i].contains("초밥") || parse[i].contains("일식")
						|| parse[i].contains("양식") || parse[i].contains("스시") || parse[i].contains("육회")
						|| parse[i].contains("곱창") || parse[i].contains("고기") || parse[i].contains("소고기")
						|| parse[i].contains("돼지고기") || parse[i].contains("오리고기") || parse[i].contains("분식")
						|| parse[i].contains("족발") || parse[i].contains("양꼬치") || parse[i].contains("보쌈")
						|| parse[i].contains("소주") || parse[i].contains("헌팅") || parse[i].contains("데이트")
						|| parse[i].contains("커피") || parse[i].contains("빙수") || parse[i].contains("디저트")
						|| parse[i].contains("케이크") || parse[i].contains("초콜릿") || parse[i].contains("초코렛")
						|| parse[i].contains("초코") || parse[i].contains("우동") || parse[i].contains("라멘")
						|| parse[i].contains("돈부리") || parse[i].contains("카레") || parse[i].contains("월남쌈")
						|| parse[i].contains("빵") || parse[i].contains("무한") || parse[i].contains("리필")
						|| parse[i].contains("냉면") || parse[i].contains("물냉") || parse[i].contains("비냉")
						|| parse[i].contains("매운족발") || parse[i].contains("쉑쉑") || parse[i].contains("수제")
						|| parse[i].contains("버거") || parse[i].contains("수제버거") || parse[i].contains("찌개")
						|| parse[i].contains("김치찜") || parse[i].contains("아구찜") || parse[i].contains("찜")
						|| parse[i].contains("탕") || parse[i].contains("해물") || parse[i].contains("만두")
						|| parse[i].contains("분위기") || parse[i].contains("레스토랑") || parse[i].contains("쉐프")) {

					String tasty = scutil.searchlocation(theater + " " + parse[i]);
					result2.put("TASTY", tasty);
				}
			}

			if (result2.get("BUS").equals("")) {
				if (parse[i].contains("출장") || parse[i].contains("여행")) {
				}
			}
			// 나머지..
			if (result2.get("KTX").equals("") && result2.get("BUS").equals("") && result2.get("TASTY").equals("")
					&& mvnm.equals("") && time.equals("") && theater.equals("")) {
				result2.put("FLAG", "DEFAULT");
				break;
			}
		} // outer for

		if (result2.get("FLAG").equals("movie")) {
			if (mvnm.equals("")) {
				if (!cn.movieSelectHelper(theater)) {
					return fail;
				}
				cn.selectDate(date);
				result2.put("MVLIST", cn.getMovie2());
			} else if (theater.equals("")) {

			} else if (time.equals("")) {
				cn.setMovie(mvnm);
				if (!cn.movieSelectHelper(theater)) {
					return fail;
				}
				cn.selectDate(date);
				result2.put("MVTIME", cn.getMovieTimes());
			}
		}
		
		// DB 들어가는 부분
		if (result2.get("FLAG").equals("DEFAULT")) {
			int user_no_fk = memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk();
			
			Diary diary = new Diary();
			diary.setUser_no_fk(user_no_fk);
			diary.setSc_stdt(date);
			diary.setSc_con(query);
			diary.setSc_wt("SU");
			diary.setSc_fin("Y");
			
			// diary 객체의 sc_no_pk에 스케쥴 번호 넣어주기
			diaryDAO.insertDiary(diary);
			
			for (Integer frno : frnoList) {
				diaryDAO.insertCompanions(diary.getSc_no_pk(), user_no_fk, frno);
			}
		}
		return result2;
	}// method end

	@ResponseBody
	@RequestMapping(value = "commonsc", method = RequestMethod.POST)
	public void commonsc(@RequestBody HashMap<String, Object> object, HttpSession session) {
		int user_no_fk = memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk();
		
		String text = (String) object.get("text");
		String link = (String) object.get("link");
		String telephone = (String) object.get("telephone");
		String date = (String) object.get("date");
		String flag = (String) object.get("flag");
		String time = (String) object.get("time");
		String original = (String) object.get("original");
		ArrayList<String> frList = (ArrayList<String>) object.get("selectedFriendList");
		ArrayList<Integer> frnoList = new ArrayList<>();
		
		for (String friend : frList) {
			frnoList.add(memberDAO.selectMember(friend).getUser_no_pk());
		}
		
		Diary diary = new Diary();
		diary.setUser_no_fk(memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk());
		if (link == null || link.equals("")) {
			diary.setSc_con(" " + "_" + text + "_" + " " + "_" + telephone + "_" + flag + "_" + time + "_" + original );
		} else if (telephone == null || telephone.equals("")) {
			diary.setSc_con(" " + "_" + text + "_" + link + "_" + " " + "_" + flag + "_" + time + "_" + original);
		} else if (link.equals("") && telephone.equals("")) {
			diary.setSc_con(" " + "_" + text + "_" + " " + "_" + " " + "_" + flag + "_" + time + "_" + original);
		} else {
			diary.setSc_con(" " + "_" + text + "_" + link + "_" + telephone + "_" + flag + "_" + time + "_" + original);
		}
		diary.setSc_wt("SU");
		diary.setSc_stdt(date);
		diary.setSc_fin("Y");
		diaryDAO.insertDiary(diary);

		for (Integer frno : frnoList) {
			diaryDAO.insertCompanions(diary.getSc_no_pk(), user_no_fk, frno);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "cancelCommonSC", method = RequestMethod.GET)
	public boolean cancelCommonSC(int scno, HttpSession session) {
		if (diaryDAO.deleteDiary(scno) == 1)
			return true;
		else {
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "defaultCancel", method = RequestMethod.GET)
	public boolean defaultCancel(int scno, HttpSession session) {
		if (diaryDAO.deleteDiary(scno) == 1)
			return true;
		else {
			return false;
		}
	}
}
