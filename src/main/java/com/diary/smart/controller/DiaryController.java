package com.diary.smart.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diary.smart.dao.DiaryDAO;
import com.diary.smart.dao.MemberDAO;
import com.diary.smart.vo.Diary;
import com.diary.smart.vo.Member;
import com.google.gson.Gson;

@Controller
public class DiaryController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

	@Autowired
	private DiaryDAO dao;
	@Autowired
	private MemberDAO mdao;

	@RequestMapping(value = "diary", method = RequestMethod.GET)
	public String diary(HttpSession session, Model model) {
		String id = (String) session.getAttribute("user_id");
		Member member = mdao.selectMember(id);
		model.addAttribute("member", member);
		List<HashMap<String, Object>> scheduleList = dao.selectDiaryList(member.getUser_no_pk());
		model.addAttribute("scheduleList", new Gson().toJson(scheduleList));
		return "diary";
	}

	@RequestMapping(value = "deleteDiary", method = RequestMethod.POST)
	public String deleteDiary(int sc_no_pk, Model model) {
		int result = dao.deleteDiary(sc_no_pk);
		return null;
	}

}
