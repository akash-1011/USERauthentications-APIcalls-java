package com.akash.struts;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

public class FindWeather implements SessionAware {
	
	private String city;
	private String temp;
	private String description;
	private String apiId = "6d5ada141c0d278e2f891004230acbe5";
	SessionMap<String,String> sessionmap;
	
	@Override
	public void setSession(Map map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, String>)map;  
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
	public String execute() throws MalformedURLException {
		
		if((sessionmap.get("login") != null)) { 
			
		
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+getCity()+"&appid="+apiId+"&units=metric";
		String line;
		StringBuffer responseContent = new StringBuffer();
		try {
			URL api = new URL(url);
			HttpURLConnection con = (HttpURLConnection)api.openConnection();
			con.setRequestMethod("GET");
			
			if(con.getResponseCode() < 299) {
				//read response
				BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				
				reader.close();
				con.disconnect();
				
				JSONObject obj = new JSONObject(responseContent.toString());
				JSONObject main = new JSONObject(obj.get("main").toString());
				setTemp(main.get("temp")+"°C");
				JSONArray weatherArray = new JSONArray(obj.get("weather").toString());
				JSONObject weatherObj = weatherArray.getJSONObject(0);
				setDescription(weatherObj.getString("description"));
			}
			if(getDescription().length() != 0) {
				return "success";
			} else {
				return "failure";
			}
		} catch(Exception e) {
			setTemp("0");
			setDescription("cant find");
			return "failure";
		}
		} else {
			return "failure";
		}
	}

}
