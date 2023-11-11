package com.diary.smart.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diary.smart.dao.DiaryDAO;
import com.diary.smart.dao.MemberDAO;
import com.diary.smart.util.CGVNavigator;
import com.diary.smart.vo.Diary;
import com.diary.smart.vo.Member;

@Controller
public class MovieController {

	@Autowired
	private DiaryDAO diaryDAO;

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private CGVNavigator cn;

	@ResponseBody
	@RequestMapping(value = "getmovie", method = RequestMethod.GET)
	public ArrayList<String> test() {

		return cn.getMovie();
	}

	@ResponseBody
	@RequestMapping(value = "setmovie", method = RequestMethod.GET)
	public void gettheater(String movie) {
		cn.setMovie(movie);
	}

	@ResponseBody
	@RequestMapping(value = "setdate", method = RequestMethod.GET)
	public boolean setdate(String moviedate) {
		return cn.selectDate(moviedate);
	}

	@ResponseBody
	@RequestMapping(value = "settheater", method = RequestMethod.GET)
	public boolean settheater(String theater) {
		return cn.movieSelectHelper(theater);
	}

	@ResponseBody
	@RequestMapping(value = "gettime", method = RequestMethod.GET)
	public ArrayList<String> gettime() {

		return cn.getMovieTimes();
	}

	@ResponseBody
	@RequestMapping(value = "settime", method = RequestMethod.GET)
	public void settime(String time) {
		cn.selectTime(time);
	}

	@ResponseBody
	@RequestMapping(value = "mvInfoSetting", method = RequestMethod.GET)
	public void mvInfoSetting(HttpSession session) {
		Member member = memberDAO.selectMember((String) session.getAttribute("user_id"));
		session.setAttribute("mvset", 0);
		cn.movieInput(member);
	}

	@ResponseBody
	@RequestMapping(value = "getseat", method = RequestMethod.GET)
	public HashMap<String, Object> getseat2(String humanCount) {
		return cn.showMovieSeat(humanCount);
	}

	@ResponseBody
	@RequestMapping(value = "setseat", method = RequestMethod.POST)
	public ArrayList<String> setseat(@RequestBody Object obj) {
		HashMap<String, Object> map = (HashMap<String, Object>) obj;
		return cn.selectSeats((ArrayList<Integer>) map.get("seats"));
	}

	@ResponseBody
	@RequestMapping(value = "payment", method = RequestMethod.POST)
	public boolean payment(@RequestBody HashMap<String, Object> object, HttpSession session) {
		int user_no_fk = memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk();
		String card = (String) object.get("card");
		String cardno = (String) object.get("cardno");
		String sno = (String) object.get("sno");
		String year = (String) object.get("year");
		String month = (String) object.get("month");
		String birth = (String) object.get("birth");
		String paymentInfo = (String) object.get("paymentInfo");
		String date = (String) object.get("date");
		ArrayList<String> frList = (ArrayList<String>) object.get("selectedFriendList");
		ArrayList<Integer> frnoList = new ArrayList<>();

		for (String friend : frList) {
			frnoList.add(memberDAO.selectMember(friend).getUser_no_pk());
		}

		ArrayList<String> ex = new ArrayList<String>();
		boolean result = false;
		ex.add(card);
		ex.add(cardno.substring(0, 4));
		ex.add(cardno.substring(4, 8));
		ex.add(cardno.substring(8, 12));
		ex.add(cardno.substring(12, 16));
		ex.add(sno);
		ex.add(month);
		ex.add(year);
		ex.add(birth);

		result = cn.payment(ex);
		if (result) {
			Diary diary = new Diary();
			diary.setUser_no_fk(memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk());
			diary.setSc_con(paymentInfo);
			diary.setSc_wt("SU");
			diary.setSc_stdt(date);
			diary.setSc_fin("Y");
			diaryDAO.insertDiary(diary);

			for (Integer frno : frnoList) {
				diaryDAO.insertCompanions(diary.getSc_no_pk(), user_no_fk, frno);
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "updatePaymentFIN", method = RequestMethod.GET)
	public boolean updatePaymentFIN(HttpSession session) {
		diaryDAO.paymentFin((Integer) session.getAttribute("lastscno"));
		return true;
	}

	@ResponseBody
	@RequestMapping(value = "cancelMovieCGV", method = RequestMethod.GET)
	public boolean cancelMovieCGV(String mvtime, String mvname, int scno, HttpSession session) {
		Member member = memberDAO.selectMember((String) session.getAttribute("user_id"));
		if (cn.cancelMovieCGV(mvtime, mvname, member)) {
			if (diaryDAO.deleteDiary(scno) == 1)
				return true;
		}
		return false;
	}
}
