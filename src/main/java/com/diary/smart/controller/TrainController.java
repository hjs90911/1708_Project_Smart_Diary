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
import com.diary.smart.util.TrainNavigator;
import com.diary.smart.vo.Diary;

@Controller
public class TrainController {
	@Autowired
	private DiaryDAO diaryDAO;

	@Autowired
	private TrainNavigator tn;

	@Autowired
	private MemberDAO memberDAO;

	@ResponseBody
	@RequestMapping(value = "setStartAndDestPoint", method = RequestMethod.GET)
	public void setStartAndDestPoint(String area1, String area2) {
		tn.setStartAndDestPoint(area1, area2);
	}

	@ResponseBody
	@RequestMapping(value = "setDate", method = RequestMethod.GET)
	public ArrayList<String> setDate(String date) {
		return tn.setDate(date);
	}

	@ResponseBody
	@RequestMapping(value = "setKTXSeat", method = RequestMethod.GET)
	public boolean setKTXSeat(String date) {
		return tn.setKTXSeat(date);
	}

	@ResponseBody
	@RequestMapping(value = "setPeople", method = RequestMethod.GET)
	public void setPeople(String count) {
		tn.setPeople(count);
	}

	@ResponseBody
	@RequestMapping(value = "loginTrain", method = RequestMethod.POST)
	public ArrayList<String> loginTrain(String type, String trid, String trkey) {
		ArrayList<String> info = new ArrayList<>();
		info.add(type);
		info.add(trid);
		info.add(trkey);
		return tn.login(info);
	}

	@ResponseBody
	@RequestMapping(value = "KTXwriteCardInfo", method = RequestMethod.POST)
	public boolean KTXwriteCardInfo(@RequestBody HashMap<String, Object> object, HttpSession session) {
		int user_no_fk = memberDAO.selectMember((String) session.getAttribute("user_id")).getUser_no_pk();
		String type = (String) object.get("type");
		String cardno = (String) object.get("cardno");
		String year = (String) object.get("year");
		String month = (String) object.get("month");
		String key = (String) object.get("key");
		String birth = (String) object.get("birth");
		String paymentInfo = (String) object.get("paymentInfo");
		String date = (String) object.get("date");
		ArrayList<String> frList = (ArrayList<String>) object.get("selectedFriendList");
		ArrayList<Integer> frnoList = new ArrayList<>();

		for (String friend : frList) {
			frnoList.add(memberDAO.selectMember(friend).getUser_no_pk());
		}

		ArrayList<String> cardInfo = new ArrayList<String>();
		cardInfo.add(type);
		cardInfo.add(cardno);
		cardInfo.add(year);
		cardInfo.add(month);
		cardInfo.add(key);
		cardInfo.add(birth);
		if (tn.pay(cardInfo)) {
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
			return true;
		} else {
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "cancelLoginCheckKTX", method = RequestMethod.POST)
	public boolean cancelLoginCheckKTX() {
		return tn.cancelCheck();
	}

	@ResponseBody
	@RequestMapping(value = "loginForCancel", method = RequestMethod.POST)
	public boolean loginForCancel(String type, String id, String key) {
		return tn.loginForCancel(type, id, key);
	}

	@ResponseBody
	@RequestMapping(value = "cancelKTX", method = RequestMethod.POST)
	public boolean cancelKTX(String date, String time, int scno, HttpSession session) {
		if (tn.cancelKTX(date, time)) {
			if (diaryDAO.deleteDiary(scno) == 1)
				return true;
		} // outer if
		return false;
	}
}
