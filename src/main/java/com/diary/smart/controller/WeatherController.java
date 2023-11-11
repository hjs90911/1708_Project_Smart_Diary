package com.diary.smart.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class WeatherController {

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@ResponseBody
	@RequestMapping(value = "weather", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String todayWeather(String city, String county, String village) {
		
		try{
		StringBuilder urlBuilder = new StringBuilder("http://apis.skplanetx.com/weather/forecast/3days"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=8c6e9bd8-5e62-3619-a64f-167d4388f405"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=b8bd2517-e3b3-3727-8e7b-2c7dd0f219f4"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=0c6b6e45-c824-3c07-ab2d-09501bf9978b"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=46c53d39-6e46-3ce3-a147-dc995ba504e7"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=dc77d4bc-5d0f-32b2-be26-ae3fa052afa9");
		urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=1"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("city","UTF-8") + "=" + URLEncoder.encode(city, "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("county","UTF-8") + "=" + URLEncoder.encode(county, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("village","UTF-8") + "=" + URLEncoder.encode(village, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
     
        String result = sb.toString();
    	
        return result;
    	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "weather2", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String weekWeather(String city, String county, String village) {
		
		try{
		StringBuilder urlBuilder = new StringBuilder("http://apis.skplanetx.com/weather/forecast/6days"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=8c6e9bd8-5e62-3619-a64f-167d4388f405"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=b8bd2517-e3b3-3727-8e7b-2c7dd0f219f4"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=0c6b6e45-c824-3c07-ab2d-09501bf9978b"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=46c53d39-6e46-3ce3-a147-dc995ba504e7"); /*Service Key*/
//		urlBuilder.append("?" + URLEncoder.encode("appKey","UTF-8") + "=dc77d4bc-5d0f-32b2-be26-ae3fa052afa9");
		urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=1"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("city","UTF-8") + "=" + URLEncoder.encode(city, "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("county","UTF-8") + "=" + URLEncoder.encode(county, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("village","UTF-8") + "=" + URLEncoder.encode(village, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        BufferedReader rd;
      
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
       
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
    
        String result = sb.toString();
    	
        return result;
    	
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
}
