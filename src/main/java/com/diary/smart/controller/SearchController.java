package com.diary.smart.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diary.smart.util.SearchUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchUtil scutil;

	@ResponseBody
	@RequestMapping(value = "searchlocation", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String searchlocation(String query) {
		return scutil.searchlocation(query);
	}

	@ResponseBody
	@RequestMapping(value = "movielocationList", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String movielocationList(String query) {
		return scutil.movielocationList(query);
	}

	@ResponseBody
	@RequestMapping(value = "stationLocation", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String buslocationList(String query) {
		return scutil.buslocationList(query);
	}

	@ResponseBody
	@RequestMapping(value = "blogInfo", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String blogInfo(String query) {
		return scutil.blogInfo(query);
	}

	@ResponseBody
	@RequestMapping(value = "terminalCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String terminalCode(String query) {
		return scutil.terminalCode(query);
	}

	@ResponseBody
	@RequestMapping(value = "busInfo", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String busInfo(String depart, String arrive, String date) {
		return scutil.busInfo(depart, arrive, date);
	}

	@ResponseBody
	@RequestMapping(value = "ktxInfo", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String ktxInfo(String depart, String arrive, String date) {
		return scutil.ktxInfo(depart, arrive, date);

	}
}
