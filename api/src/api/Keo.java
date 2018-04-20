package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
public class Keo {
	static int countId = 0;
	public int id;
	public int status;
	public String link;
	public String name, exchange;
	public double stoploss, buyzone, cur;
	public double[] target = {0,0,0,0,0};
	public int num;
	public int num_target;
	public int selected;
	
	public void upbit(int i) {
		selected |= (1 << i);
	}
	
	public int getbit(int i) {
		return (selected >> i) & 1;
	}
	
	

	public Keo(int id, int status, String link, String name, String exchange, double stoploss, double buyzone,
			double cur, double[] target, int num, int num_target, int selected) {
		super();
		this.id = id;
		this.status = status;
		this.link = link;
		this.name = name;
		this.exchange = exchange;
		this.stoploss = stoploss;
		this.buyzone = buyzone;
		this.cur = cur;
		this.target = target;
		this.num = num;
		this.num_target = num_target;
		this.selected = selected;
	}

	public Keo(String name, String exchange, String stoploss, String buyzone , String target) {
		super();
		this.id = ++countId;
		this.name = name.toUpperCase();
		System.out.println("name == " + this.name + name.toUpperCase());
		this.link = ("https://api.binance.com/api/v3/ticker/price?symbol=" + this.name);
		this.exchange = exchange.toUpperCase();
		this.stoploss = Double.parseDouble(stoploss);
		this.buyzone = Double.parseDouble(buyzone);
		this.cur = getcurrentPrice();
		System.out.println("Check input target:::::::::" + target);
		String tmp[] = target.split(",");
		for(int i = 0; i < tmp.length; i++) {
			System.out.println("Check target:!! " + tmp[i]);
			this.target[i] = Double.parseDouble(tmp[i]);
		}
		this.num_target = tmp.length;
		//updateStatus();
		this.status = -3;
		this.num = Double.toString(this.target[num_target - 1]).length();
	}

	// -1 stoploss
	// 0 <=buyzone 
	// 1 <= target[0] qua mua
	// 2 target[0] <= prize < target[1] : dat target 0
	// ..
	public static String urlresponse(String link) {
		StringBuilder result = new StringBuilder();
	      try {
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error while connect to binance API");
		}
		return result.toString();
	}
	
	double getcurrentPrice() {
		String data = urlresponse(this.link);
		System.out.println(link);
		System.out.println(data);
		JSONObject obj = new JSONObject(data);
		return Double.parseDouble(obj.getString("price"));
	}
	

	String getStatus() {
		int old_status = this.status;
		updateStatus();
		
		String res ="";
		
		
		if (status == old_status) res = "";
		else {
			String cmd = "%." + Integer.toString(num) +"f";
			System.out.println(cmd + "?WDDD");
			if (status == -1) res= name  + " " + String.format(cmd,cur) + " <= STOPLOSS !! ";
			else
				if (status == 0) res= name  + " " +  String.format(cmd, cur)  + " Buyzone ";
				else
					if (status == 1) res= "";
					else
						if (status == 2) res= name  +" " +  String.format(cmd,cur)  +  " Target 1 done, moon soon ";
						else
							if (status == 3) res= name +" " +  String.format(cmd ,cur)  +     "  Target 2 done";
							else
								if (status == 4) res= name  + " " +  String.format(cmd,cur)  + "  Target 3 done, moon cmnr  ";
								else
									if (status == 5) res= name  +" " +   String.format(cmd,cur)  +  "Target 4 done, go to the sun  ";
									else
										if (status == 6) res= name  +" " +   String.format(cmd,cur)  + "Target 5 done, buy lambo  ";
		}
		//ret
		if (getbit(status + 1) == 0) return res;
			else return "";
	}
	
	int updateStatus() {
		this.cur = getcurrentPrice();
		status = -2;
		for(int i = num_target - 1; i >= 0; i--) if (cur >= target[i]) {
			status = i + 2;
			break;
		}
		
		if (status == -2) {
			if (cur >= buyzone) status = 1;
			else if (cur >= stoploss) status = 0;
			else if (cur < stoploss) status = -1;
		}
		
		upbit(status + 1);
		return status;
	}


}
