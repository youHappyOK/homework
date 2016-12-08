package com.cqupt.homework;

/***
 * Summary类
 * 
 * @author Jin
 *
 */

public class Summary {
	
	private String result = "";

	public String generateSummary(String input) throws Exception{
			
		result += "[Summary]\n\n";

		int totalIncome = 0;
		int totalPayment = 0;
		int totalProfit = 0;
		
		String[] lines = input.split("\n");
		String line = null;
		
		for (int k = 0; k < lines.length; k++) {
			line = lines[k];
			EachLine el = new EachLine(line);
			el.calPayment();

			totalIncome += el.getIncome();
			totalPayment += el.getPayment();
			totalProfit += el.getProfit();

			String profit;
			if (el.getProfit() > 0) { // 调整profit的输出格式
				profit = "+" + el.getProfit() + "";
			} else if (el.getProfit() == 0) {
				profit = "" + el.getProfit();
			} else {
				profit = "" + el.getProfit();
			}
			result += el.getDay() + " " + el.getTime() + " +"
					+ el.getIncome() + " -" + el.getPayment() + " "
					+ profit + "\n";          // 输出每次活动的花费情况
		}
		result += "\n" + "Total Income: " + totalIncome + "\n";//输出总体花费情况
		result += "Total Payment: " + totalPayment + "\n";
		result += "Profit: " + totalProfit + "\n";	
		
		return result;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
