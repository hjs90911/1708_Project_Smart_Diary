package com.diary.smart.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
@Repository
public class ExpressBusNavigator {
	private ChromeOptions options;
	private WebDriver driver;
	private JavascriptExecutor js;
	private String handle;
	private String now;
	
	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public ExpressBusNavigator(){
		System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver.exe");
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver; // 자바스크립트를 사용하기 위한
		driver.get("http://www.kobus.co.kr/web/03_reservation/reservation01.jsp");
		this.setNow("kobus");
		this.setHandle(driver.getWindowHandle());
	}

	//현재브라우저 창 객체 가져오기
	public String getHandle() {
		return handle;
	}

	//현재 브라우저 창을 객체로 생성
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	public void setBrowserKOBUS(){
		driver.get("http://www.kobus.co.kr/web/03_reservation/reservation01.jsp");
		this.setHandle(driver.getWindowHandle());
		this.setNow("kobus");
	}
	
	public void setBrowserEASYTICKET(){
		driver.get("https://www.hticket.co.kr/booking/inform.action");
		this.setHandle(driver.getWindowHandle());
		this.setNow("easyticket");
	}
	
	public void setStartingPoint(String area){
		boolean flag = false;
		driver.switchTo().window(this.getHandle());
		this.setBrowserEASYTICKET();
		ArrayList<WebElement> terminal = (ArrayList<WebElement>) driver.findElement(By.id("searchStTermNbr")).findElements(By.tagName("option"));
		
		for (int i = 1; i < terminal.size(); i++) {
			if(terminal.get(i).getText().contains("*") || terminal.get(i).getText()==null )
				flag = true;
			else{
				if(terminal.get(i).getText().contains(area)){
					terminal.get(i).click();
					break;
				}//if
			}
			if(flag) break;
		}//for
		if(flag) this.setStartingPoint2(area);
		
	}
	

	public void setStartingPoint2(String area) {
		boolean flag=false;
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> terminal = (ArrayList<WebElement>) driver.findElement(By.id("regForm01")).findElements(By.tagName("option"));
		
		for (int i = 1; i < terminal.size(); i++) {
			if(terminal.get(i).getText().contains("*")  || terminal.get(i).getText()==null){
				flag = true;
			}
			else{
				if(terminal.get(i).getText().contains(area)){
					terminal.get(i).click();
					break;
				}//if
			}
			if(flag) break;
		}//for
		if(flag) this.setStartingPoint(area);
	}
	
	public void setDestination(String area){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> terminal = (ArrayList<WebElement>) driver.findElement(By.id("searchArTermNbr")).findElements(By.tagName("option"));
		
		for (int i = 1; i < terminal.size(); i++) {
			if(terminal.get(i).getText().contains("*") || terminal.get(i).getText()==null ){
				
			}
			else{
				if(terminal.get(i).getText().contains(area)){
					terminal.get(i).click();
					break;
				}//if
			}
		}//for
	}
	
	public void setDestination2(String area){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> terminal = (ArrayList<WebElement>) driver.findElement(By.cssSelector("select[name='TER_TO']")).findElements(By.tagName("option"));
		
		for (int i = 1; i < terminal.size(); i++) {
			if(terminal.get(i).getText().contains("*")  || terminal.get(i).getText()==null){
			}
			else{
				if(terminal.get(i).getText().contains(area)){
					terminal.get(i).click();
					break;
				}//if
			}
		}//for
	}
	
	public void setDate(String date){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> year = (ArrayList<WebElement>) driver.findElement(By.id("searchYear")).findElements(By.tagName("option"));
		ArrayList<WebElement> month = (ArrayList<WebElement>) driver.findElement(By.id("searchMonth")).findElements(By.tagName("option"));
		ArrayList<WebElement> day = (ArrayList<WebElement>) driver.findElement(By.id("searchDay")).findElements(By.tagName("option"));
		ArrayList<WebElement> time = (ArrayList<WebElement>) driver.findElement(By.id("searchTime")).findElements(By.tagName("option"));
		for (int i = 0; i < year.size(); i++) {
			if(year.get(i).getText().equals(date.substring(0, 4))){
				year.get(i).click();
				break;
			}//if
		}//for
		for (int i = 0; i < month.size(); i++) {
			if(month.get(i).getText().equals(date.substring(4, 6))){
				month.get(i).click();
				break;
			}//if
		}//for
		
		for (int i = 0; i < day.size(); i++) {
			if(day.get(i).getText().equals(date.substring(6, 8))){
				day.get(i).click();
				break;
			}//if
		}//for
		String setTime = date.substring(8,10) + ":00";
		for (int i = 0; i < time.size(); i++) {
			if(time.get(i).getText().equals(setTime)){
				time.get(i).click();
				break;
			}//if
		}//for
	}
	
	public void setDate2(String date){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> year = (ArrayList<WebElement>) driver.findElement(By.name("Tim_date_Year")).findElements(By.tagName("option"));
		ArrayList<WebElement> month = (ArrayList<WebElement>) driver.findElement(By.name("Tim_date_Month")).findElements(By.tagName("option"));
		ArrayList<WebElement> day = (ArrayList<WebElement>) driver.findElement(By.name("Tim_date_Day")).findElements(By.tagName("option"));
		ArrayList<WebElement> time = (ArrayList<WebElement>) driver.findElement(By.name("TIM_TIM_I")).findElements(By.tagName("option"));
		for (int i = 1; i < year.size(); i++) {
				if(year.get(i).getText().contains(date.substring(0, 4))){
					year.get(i).click();
					break;
				}//if
		}//for
		
		for (int i = 1; i < month.size(); i++) {
			if(month.get(i).getAttribute("value").contains(date.substring(4, 6))){
				month.get(i).click();
				break;
			}//if
		}//for
		
		for (int i = 1; i < day.size(); i++) {
			if(day.get(i).getAttribute("value").contains(date.substring(6, 8))){
				day.get(i).click();
				break;
			}//if
		}//for
		String setTime = date.substring(8,10) + ":00";
		for (int i = 1; i < time.size(); i++) {
			if(time.get(i).getText().contains(setTime)){
				time.get(i).click();
				break;
			}//if
		}//for
		
	}
	
	public void setSeatGrade2(String grade){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> seat = (ArrayList<WebElement>) driver.findElement(By.cssSelector("select[name='BUS_GRA_I']")).findElements(By.tagName("option"));
		for (int i = 1; i < seat.size(); i++) {
			if(seat.get(i).getText().contains(grade)){
				seat.get(i).click();
				break;
			}//if
		}//for
	}
	
	public void setSeatGrade(String grade){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> seat = (ArrayList<WebElement>) driver.findElement(By.id("searchGrade")).findElements(By.tagName("option"));
		for (int i = 1; i < seat.size(); i++) {
			if(seat.get(i).getText().contains(grade)){
				seat.get(i).click();
				break;
			}//if
		}//for
		ArrayList<WebElement> aTag = (ArrayList<WebElement>) driver.findElement(By.id("Table_3")).findElements(By.tagName("a"));
		aTag.get(1).click();
	}
	
	public void setHuman2(String count){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> human = (ArrayList<WebElement>) driver.findElement(By.cssSelector("select[name='pCnt_100']")).findElements(By.tagName("option"));
		for (int i = 1; i < human.size(); i++) {
			if(human.get(i).getAttribute("value").equals(count)){
				human.get(i).click();
				break;
			}//if
		}//for
		driver.findElement(By.cssSelector("dd.btn input")).click();
		
		boolean flag = true;
	
		Alert alert=null;
			while(flag){
				try{
					alert = driver.switchTo().alert();
					if(alert!=null){
						alert.accept();
					}else{
						flag=false;
					}
				}catch(WebDriverException e){
					flag=false;
				}
			}//while
	}
	
	public void setHuman(String count){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> human = (ArrayList<WebElement>) driver.findElement(By.id("selectTkt01")).findElements(By.tagName("option"));
		for (int i = 1; i < human.size(); i++) {
			if(human.get(i).getAttribute("value").equals(count)){
				human.get(i).click();
				break;
			}//if
		}//for
	}
	
	public ArrayList<String> getStartTime2(){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> table = (ArrayList<WebElement>) driver.findElement(By.cssSelector("table.ticket")).findElements(By.cssSelector("tbody tr"));
		ArrayList<String> resultTime = new ArrayList<String>();
		
		for (WebElement webElement : table) {
			resultTime.add(webElement.findElement(By.cssSelector("td:nth-child(0)")).getText());
		}
		
		return resultTime;		
	}
	
	public void selectTicket(String time){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> table2 = (ArrayList<WebElement>)driver.findElement(By.id("Table_4")).findElements(By.cssSelector("table"));
		ArrayList<WebElement> tr = (ArrayList<WebElement>)table2.get(4).findElements(By.cssSelector("tr"));
		ArrayList<WebElement> td = null;
		for (WebElement webElement : tr) {
			td = (ArrayList<WebElement>) webElement.findElements(By.tagName("td"));
			if(td.get(0).getText().contains(time.substring(0,2)+":"+time.substring(2, 4))){
				td.get(td.size()-1).findElement(By.tagName("a")).click();
				break;
			}
			td = new ArrayList<WebElement>();
		}
	}
	
	
	public ArrayList<String> selectTicket2(String time){
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> table = (ArrayList<WebElement>) driver.findElement(By.cssSelector("table.ticket")).findElements(By.cssSelector("tbody tr"));
		ArrayList<String> result = new ArrayList<>();
		for (WebElement webElement : table) {
			if(webElement.findElements(By.tagName("td")).get(0).getText().equals(time)){
				result.add(webElement.findElements(By.tagName("td")).get(1).getText());
				if(result.get(0).contains("일반")) result.add(driver.findElements(By.cssSelector("table.bus_fare tbody > tr > td")).get(1).getText());
				else result.add(driver.findElements(By.cssSelector("table.bus_fare tbody > tr > td")).get(2).getText());
				
				webElement.findElements(By.tagName("td")).get(webElement.findElements(By.tagName("td")).size()-1).findElement(By.tagName("input")).click();
				break;
			}
		}
		Alert alert = driver.switchTo().alert();
		if(alert!=null){
			alert.accept();
		}
		
		result.add(driver.findElement(By.cssSelector("p.sel_title")).getText().substring(0, 25));
		result.add(driver.findElement(By.cssSelector("p.sel_title")).getText().trim());
		result.add(driver.findElement(By.cssSelector("p.sel_title span")).getText().trim());
		
		return result;
	}
	
	public HashMap<String,Object> showBusSeat2(){
		driver.switchTo().window(this.getHandle());
		HashMap<String,Object> result = new HashMap<String, Object>();
		String count = (String) js.executeScript("return document.getElementsByClassName('text02')[0].outerHTML;");
		result.put("form", js.executeScript("return document.getElementsByClassName('tbl_seat01')[0].outerHTML;"));
		result.put("count", count.charAt(41));
		result.put("flag", 0);
		return result;
	}
	
	public HashMap<String,Object> showBusSeat(String count){
		driver.switchTo().window(this.getHandle());
		HashMap<String,Object> result = new HashMap<String, Object>();
		result.put("form", js.executeScript("return document.getElementById('seatView').outerHTML;"));
		result.put("count", count);
		result.put("flag", 1);
		return result;
	}

	public ArrayList<String> selectSeats2(ArrayList<String> arrayList) {
		driver.switchTo().window(this.getHandle());
		int flag = 0;
		ArrayList<WebElement> td = (ArrayList<WebElement>) driver.findElement(By.className("tbl_seat01")).findElements(By.cssSelector("label"));
		ArrayList<WebElement> input = (ArrayList<WebElement>) driver.findElement(By.className("tbl_seat01")).findElements(By.cssSelector("input"));
		String seatNum="";
		for (int i=0; i<td.size(); i++){
			for(int j=0; j<arrayList.size()-1; j++){
				if(td.get(i).getText().equals(arrayList.get(j))){
					input.get(i).click();
					seatNum+=arrayList.get(j)+" ";
					flag++;
					break;
				}
			}
			if(flag==arrayList.size()){ 
				break;
			}
		}
		if ( !driver.findElement(By.name("checkbox1")).isSelected() ){
			driver.findElement(By.name("checkbox1")).click();
		}
		ArrayList<String> imsi = new ArrayList<>(Arrays.asList(arrayList.get((arrayList.size()-1)).split(",")));
		ArrayList<String> result = new ArrayList<>();
		System.out.println(imsi);
		result.add(imsi.get(2).substring(0, 4)+"."+imsi.get(2).substring(6, 8)+"."+imsi.get(2).substring(10, 12)+" "+imsi.get(2).substring(18, 23));
		result.add(imsi.get(0)+" → "+imsi.get(1));
		result.add(imsi.get(3));
		result.add(seatNum);
		result.add(imsi.get(4));
		result.add("0");
		
		return result;
	}
	
	public ArrayList<String> selectSeats(ArrayList<String> arrayList) {
		driver.switchTo().window(this.getHandle());
		int flag = 0;
		ArrayList<WebElement> ckbox = (ArrayList<WebElement>) driver.findElement(By.id("seatView")).findElements(By.cssSelector("input[type='checkbox']"));
		ArrayList<String> result = new ArrayList<>();
		for (WebElement webElement : ckbox) {
			for (String seat : arrayList) {
				if(webElement.getAttribute("value").equals(seat)){
					webElement.click();
					flag++;
					break;
				}
			}
			if(flag==arrayList.size()) break;
		}
		driver.findElement(By.id("Table_10")).findElement(By.cssSelector("a")).click();
		
		ArrayList<WebElement> td1 = (ArrayList<WebElement>)driver.findElements(By.id("Table_9"));
		ArrayList<WebElement> td2 = (ArrayList<WebElement>)driver.findElements(By.id("Table_10"));
		ArrayList<WebElement> td3 = (ArrayList<WebElement>)driver.findElements(By.id("Table_11"));
		ArrayList<WebElement> td4 = (ArrayList<WebElement>)driver.findElements(By.id("Table_12"));
		ArrayList<WebElement> td5 = (ArrayList<WebElement>)driver.findElements(By.id("Table_14"));
		ArrayList<WebElement> td6 = (ArrayList<WebElement>)driver.findElements(By.id("Table_15"));
		
		result.add(td1.get(1).getText()+" "+td1.get(3).getText());
		result.add(td2.get(1).getText()+" → "+td2.get(3).getText());
		result.add(td3.get(1).getText());
		result.add(td5.get(1).getText());
		result.add(td6.get(1).getText()+" ("+td4.get(1).getText()+")");
		result.add("1");
		return result;
	}
	
	public Object showPayWindow(){
		driver.switchTo().window(this.getHandle());
		driver.findElement(By.id(""));
		
		return js.executeScript("return document.getElementById('payment01').outerHTML;");
	}
	
	public Object showPayWindow2(){
		driver.switchTo().window(this.getHandle());
		return js.executeScript("return document.getElementById('payment01').outerHTML;");
	}
	
	
	public boolean writeCardInfo2(ArrayList<String> cardInfo){
		driver.switchTo().window(this.getHandle());
		driver.findElement(By.name("pCAD_NO")).clear();
		driver.findElement(By.name("pCAD_NO")).sendKeys(cardInfo.get(0));
		ArrayList<WebElement> year = (ArrayList<WebElement>) driver.findElement(By.name("pVIL_YEAR")).findElements(By.cssSelector("option"));
		ArrayList<WebElement> month = (ArrayList<WebElement>) driver.findElement(By.name("pVIL_MONTH")).findElements(By.cssSelector("option"));
		for (WebElement webElement : year) {
			if(webElement.getAttribute("value").equals(cardInfo.get(1))){
				webElement.click();
			}
		}
		for (WebElement webElement : month) {
			if(webElement.getAttribute("value").equals(cardInfo.get(2))){
				webElement.click();
			}
		}
		
		driver.findElement(By.name("pUSR_JUMIN")).clear();
		driver.findElement(By.name("pUSR_JUMIN")).sendKeys(cardInfo.get(3));
		driver.findElement(By.id("p111")).click();
				
			Alert alert = driver.switchTo().alert();
			if(alert!=null){
				if(alert.getText().contains("예약실패사유")||alert.getText().contains("좌석을 선택")||alert.getText().contains("유효기간")||alert.getText().contains("카드번호")||alert.getText().contains("주민")){
					alert.accept();
					return false;
				}else{
					alert.accept();
					this.setBrowserKOBUS();		
					return true;
				}
			}
		return true;
	}
	
	public void writeCardInfo(ArrayList<String> cardInfo){
		driver.switchTo().window(this.getHandle());
		
		driver.findElement(By.id("chkagree1y")).click();
		driver.findElement(By.id("chkagreey")).click();
		
		driver.findElement(By.id("textfield1")).clear();
		driver.findElement(By.id("textfield1")).sendKeys(cardInfo.get(0));
		ArrayList<WebElement> year = (ArrayList<WebElement>) driver.findElement(By.name("cardYear")).findElements(By.cssSelector("option"));
		ArrayList<WebElement> month = (ArrayList<WebElement>) driver.findElement(By.name("cardMonth")).findElements(By.cssSelector("option"));
		for (WebElement webElement : year) {
			if(webElement.getAttribute("value").equals(cardInfo.get(1))){
				webElement.click();
			}
		}
		for (WebElement webElement : month) {
			if(webElement.getAttribute("value").equals(cardInfo.get(2))){
				webElement.click();
			}
		}
		driver.findElement(By.name("cardJumin")).clear();
		driver.findElement(By.name("cardJumin")).sendKeys(cardInfo.get(3));
		driver.findElement(By.name("rsvSMS_Phone")).clear();
		driver.findElement(By.name("rsvSMS_Phone")).sendKeys(cardInfo.get(4));
		driver.findElement(By.id("tktGoBtn")).findElement(By.tagName("a")).click();
		
	}

	public String paymentCheck() {
		driver.switchTo().window(this.getHandle());
		String reserveNo = null;
		try{
			reserveNo = driver.findElements(By.id("Table_20")).get(1).getText();
			driver.findElement(By.cssSelector("a[href='/booking/bookingSearch_card.action']")).click();
		}catch(WebDriverException e){
			driver.navigate().back();
			return null;
		}
		return reserveNo;
	}

	public boolean cancelTicket(String cardno, String reserveNo) {
		driver.switchTo().window(this.getHandle());
		driver.get("https://www.hticket.co.kr/booking/bookingSearch_card.action");
		driver.findElement(By.cssSelector("input[name='cardNbr']")).clear();
		driver.findElement(By.cssSelector("input[name='cardNbr']")).sendKeys(cardno);
		driver.findElement(By.cssSelector("a[href='javascript:bookSearchGo()']")).click();
		
		ArrayList<WebElement> reservationInfo = (ArrayList<WebElement>) driver.findElements(By.cssSelector("input[name='rsvNbrA']"));
		ArrayList<WebElement> tr = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#Table_4 > tbody > tr")).get(1).findElements(By.tagName("tr"));
		
		for(int i=0 ; i<reservationInfo.size() ; i++){
			if(reservationInfo.get(i).getAttribute("value").equals(reserveNo)){
				tr.get(i+1).findElements(By.tagName("a")).get(1).click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector("a[href='javascript:canGo();']")).click();
		Alert alert = driver.switchTo().alert();
		if(alert!=null){
			alert.accept();
			driver.findElement(By.cssSelector("a[href='bookingSearch_card.action']")).click();
			return true;
		}else{
			return false;
		}
	}

	public boolean cancelTicket2(String cardno, String startdate, String time,
			String area, String year, String month) {
		driver.switchTo().window(this.getHandle());
		driver.get("https://www.kobus.co.kr/web/04_inquiry/inquiry01.jsp");
		driver.findElement(By.cssSelector("input#regForm01")).clear();
		driver.findElement(By.cssSelector("input#regForm01")).sendKeys(cardno);
		driver.findElement(By.cssSelector("input[alt='KOBUS 예약조회']")).click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> windowIterator = handles.iterator();
		  while(windowIterator.hasNext()) { 
		    String windowHandle = windowIterator.next(); 
		    if(!windowHandle.equals(this.getHandle())){
		    	driver.switchTo().window(windowHandle);
		    	break;
		    }
		  }
		
		ArrayList<WebElement> reserveList = (ArrayList<WebElement>) driver.findElements(By.className("disabled01"));
		for (WebElement webElement : reserveList) {
			if(webElement.findElement(By.cssSelector("input[name='inTimDte']")).getAttribute("value").equals(startdate) && webElement.findElement(By.cssSelector("input[name='inTimTim']")).getAttribute("value").equals(time)){
				webElement.findElement(By.cssSelector("td.last input")).click();
				driver.findElement(By.cssSelector("p.btnBlock > a[title='예약취소']")).click();
				break;
			}
		}//for
		
		ArrayList<WebElement> yearList = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#sig02 select[name='pVIL_YEAR'] option"));
		ArrayList<WebElement> monthList = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#sig02 select[name='pVIL_MONTH'] option"));
		
		for (WebElement webElement : yearList) {
			if(webElement.getAttribute("value").equals(year)){
				webElement.click();
				break;
			}
		}
		for (WebElement webElement : monthList) {
			if(webElement.getAttribute("value").equals(month)){
				webElement.click();
				break;
			}
		}
		
		driver.findElement(By.cssSelector("div#sig02 p.btnBlock a[title='예약취소']")).click();
		
		Alert alert = driver.switchTo().alert();
		if(alert!=null){
			alert.accept();
			driver.close();
			driver.switchTo().window(this.getHandle());
			driver.get("http://www.kobus.co.kr/web/03_reservation/reservation01.jsp");
			this.setNow("kobus");
			return true;
		}
		return false;
	}

	public ArrayList<String> getBusTimes() {
		ArrayList<String> result = new ArrayList<>();
		driver.switchTo().window(this.getHandle());
		ArrayList<WebElement> trList = (ArrayList<WebElement>) driver.findElements(By.cssSelector("table.ticket tbody tr"));
		
		for (WebElement webElement : trList) {
			result.add(webElement.findElements(By.cssSelector("td")).get(0).getText());
		}
		
		return result;
	}
	
}
