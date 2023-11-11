package com.diary.smart.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Repository;

@Repository
public class SearchUtil {

	public String searchlocation(String query) {
		String clientId = "5qyUQQCUkpuFBmtQSCtF";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "viimeDhdPr";// 애플리케이션 클라이언트 시크릿값";

		try {
			query = URLEncoder.encode(query + " 맛집", "UTF-8"); // 한글화

			String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + query + "&display=20"; // json

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {// br.readLine()가 널이
															// 아닐때만 inputLine에
															// br.readLine()값
															// 집어넣고 while문 실행
				sb.append(inputLine);

			}
			br.close();
			String result = sb.toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String movielocationList(String query) {

		String clientId = "5qyUQQCUkpuFBmtQSCtF";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "viimeDhdPr";// 애플리케이션 클라이언트 시크릿값";
		try {
			query = URLEncoder.encode(query + " 영화관", "UTF-8"); // 한글화
			String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + query + "&display=20"; // json
																												// 결과
			// String apiURL =
			// "https://openapi.naver.com/v1/search/blog.xml?query="+ text; //
			// xml 결과
			try {
				byte[] b = query.getBytes("utf-8");
				if (b.length < 45) {
					return "blank";
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {// br.readLine()가 널이
															// 아닐때만 inputLine에
															// br.readLine()값
															// 집어넣고 while문 실행
				sb.append(inputLine);

			}
			br.close();
			String result = sb.toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String buslocationList(String query) {
		String clientId = "5qyUQQCUkpuFBmtQSCtF";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "viimeDhdPr";// 애플리케이션 클라이언트 시크릿값";

		try {
			query = URLEncoder.encode(query + " 역 ktx", "UTF-8"); // 한글화
			String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + query + "&display=3"; // json

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {// br.readLine()가 널이
															// 아닐때만 inputLine에
															// br.readLine()값
															// 집어넣고 while문 실행
				sb.append(inputLine);

			}
			br.close();
			String result = sb.toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String blogInfo(String query) {
		String clientId = "5qyUQQCUkpuFBmtQSCtF";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "viimeDhdPr";// 애플리케이션 클라이언트 시크릿값";

		try {
			query = URLEncoder.encode(query, "UTF-8"); // 한글화
			String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + query; // json

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {// br.readLine()가 널이
															// 아닐때만 inputLine에
															// br.readLine()값
															// 집어넣고 while문 실행
				sb.append(inputLine);

			}
			br.close();
			String result = sb.toString();

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String terminalCode(String query) {

		try {
			byte[] b = query.getBytes("utf-8");
			if (b.length < 5) {
				return "blank";
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.tago.go.kr/openapi/service/ExpBusInfoService/getExpBusTrminlList"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=aQGLnJwHUAMMxCOF1qruja6mZoGKZ%2FJrwAFjmoH%2FON8SyyEPvZki1zkSA8lXX37dTOwPLCXv0c9CpeUtUxYZqQ%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
			urlBuilder.append("&" + URLEncoder.encode("terminalNm", "UTF-8") + "="
					+ URLEncoder.encode(query, "UTF-8")); /* 파라미터설명 */

			urlBuilder.append("&_type=json");

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String busInfo(String depart, String arrive, String date) {

		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.tago.go.kr/openapi/service/ExpBusInfoService/getStrtpntAlocFndExpbusInfo"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=aQGLnJwHUAMMxCOF1qruja6mZoGKZ%2FJrwAFjmoH%2FON8SyyEPvZki1zkSA8lXX37dTOwPLCXv0c9CpeUtUxYZqQ%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
			urlBuilder.append("&" + URLEncoder.encode("depTerminalId", "UTF-8") + "="
					+ URLEncoder.encode(depart, "UTF-8")); /* 파라미터설명 */
			urlBuilder.append(
					"&" + URLEncoder.encode("arrTerminalId", "UTF-8") + "=" + URLEncoder.encode(arrive, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("150", "UTF-8"));
			urlBuilder.append("&_type=json");

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String ktxInfo(String depart, String arrive, String date) {

		try {
			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=aQGLnJwHUAMMxCOF1qruja6mZoGKZ%2FJrwAFjmoH%2FON8SyyEPvZki1zkSA8lXX37dTOwPLCXv0c9CpeUtUxYZqQ%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
			urlBuilder.append("&" + URLEncoder.encode("depPlaceId", "UTF-8") + "="
					+ URLEncoder.encode(depart, "UTF-8")); /* 파라미터설명 */
			urlBuilder
					.append("&" + URLEncoder.encode("arrPlaceId", "UTF-8") + "=" + URLEncoder.encode(arrive, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("depPlandTime", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8"));
			urlBuilder.append("&_type=json");

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
