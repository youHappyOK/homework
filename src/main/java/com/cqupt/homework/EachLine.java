package com.cqupt.homework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/***
 * EachLine类	用于处理字符串每行数据
 * 
 * @author Jin
 *
 */
public class EachLine {
	
	private String day;
	private String time;
	private int manNum;
	private int income;
	private int payment;
	private int profit;
	
	private int[] workDay;//存储周一到周五的价格表
	private int[] weekend;//存储周六周日的价格表
	
	public EachLine(String line){
		String[] lineArr = line.split(" ");

	    this.day = lineArr[0];
	    this.time = lineArr[1];
	    this.manNum = Integer.parseInt(lineArr[2]);
	    this.income = 30 * this.manNum;
	    
	    workDay = new int[25];
	    weekend = new int[25];
	    
	    for(int i=9; i<=11; i++){
	    	workDay[i] = 30;
	    	weekend[i] = 40;
	    }
	    for(int i=12; i<=17; i++){
	    	workDay[i] = 50;
	    	weekend[i] = 50;
	    }
	    for(int i=18; i<=19; i++){
	    	workDay[i] = 80;
	    	weekend[i] = 60;
	    }
	    for(int i=20; i<=21; i++){
	    	workDay[i] = 60;
	    	weekend[i] = 60;
	    }
	}
	
	public int dayForWeek(String pTime) throws Exception {//判断日期是星期几
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		 Calendar c = Calendar.getInstance();  
		 c.setTime(format.parse(pTime));  
		 int dayForWeek = 0;  
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){  
		  dayForWeek = 7;  
		 }else{  
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;  
		 }  
		 return dayForWeek;  
	}  
	
	public int calPayment() throws Exception{//计算场地费用
		int startHour;
		int endHour;
		int payment = 0;
		char[] arr = this.time.toCharArray();
		
		if(arr[0] != 0){
			startHour = Integer.parseInt(String.valueOf(arr[0]))*10 + Integer.parseInt(String.valueOf(arr[1]));
		}else{
			startHour= Integer.parseInt(String.valueOf(arr[1]));
		}
		
		if(arr[6] != 0){
			endHour = Integer.parseInt(String.valueOf(arr[6]))*10 + Integer.parseInt(String.valueOf(arr[7]));
		}else{
			endHour= Integer.parseInt(String.valueOf(arr[7]));
		}
//		System.out.println(startHour);
//		System.out.println(endHour);
		if(dayForWeek(this.day) != 6 && dayForWeek(this.day) != 7){
			for(int i=startHour; i<endHour; i++){
				payment += workDay[i];
			}
		}
		if(dayForWeek(this.day) == 6 || dayForWeek(this.day) == 7){
			for(int i=startHour; i<endHour; i++){
				payment += weekend[i];
			}
		}
		this.payment = calCourt() * payment;//计算总费用
		this.profit = this.income - this.payment;//计算收益差
		return calCourt() * payment;

	}
	
	public int calCourt(){//计算场地数
		int T = this.manNum / 6;
		int X = this.manNum % 6;
		if(T == 0 && X < 4){
			this.income = 0;
			return 0;
		}
		if(T == 0 && X >= 4){
			return 1;
		}
		if(T == 1 && X != 0){
			return 2;
		}
		if((T == 2 || T == 3) && X >= 4){
			return T+1;
		}
		if(T > 3){
			return T;
		}
		return T;
	}
	
	
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getManNum() {
		return manNum;
	}
	public void setManNum(int manNum) {
		this.manNum = manNum;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "EachLine [day=" + day + ", time=" + time + ", manNum=" + manNum
				+ ", income=" + income + ", payment=" + payment + ", profit="
				+ profit + "]";
	}
	
}
