package com.akash.struts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.opensymphony.xwork2.ActionSupport;

public class FindMeaning extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private String word;
	private String meaning;
	SessionMap<String,String> sessionmap;
	
	@Override
	public void setSession(Map map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, String>)map;  
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String execute() {
		
		
		HttpServletRequest request=ServletActionContext.getRequest();  
		HttpSession session=request.getSession();  
		  
		String s=(String)session.getAttribute("login");  
		
		session.setAttribute("word", getWord());
		
		String url,line;
		String path = "C:\\Users\\hp\\struts2\\struts2-hello-basic\\src\\main\\java\\json-data.json";
		BufferedReader reader;
		StringBuffer responseContent = new StringBuffer();
		JSONArray responseArray,meanings,definitions;
		JSONObject obj,meaning,definition;
		
		try {
			FileReader jsonFile = new FileReader(path);
			JSONTokener tokener = new JSONTokener(jsonFile);
			JSONObject root = new JSONObject(tokener);
		
			if(root.has(getWord())) {	
			
				setMeaning(root.get(getWord()).toString());
			
			} else {
			
					url = "https://api.dictionaryapi.dev/api/v2/entries/en_US/"+getWord();
					URL api = new URL(url);
					HttpURLConnection con = (HttpURLConnection)api.openConnection();
					con.setRequestMethod("GET");
			
					if(con.getResponseCode() < 299) {
						//read response
						reader = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
				
						while((line = reader.readLine()) != null) {
							responseContent.append(line);
						}
				
						reader.close();
						con.disconnect();
				
						//JSON drilling
						responseArray = new JSONArray(responseContent.toString());
						obj = responseArray.getJSONObject(0);
						meanings = new JSONArray(obj.get("meanings").toString());
						meaning = meanings.getJSONObject(0);
						definitions = new JSONArray(meaning.get("definitions").toString());
						definition = definitions.getJSONObject(0);
				
						setMeaning(definition.get("definition").toString());						
			
					}
					//adding it to local JSON
					root.put(getWord(), getMeaning());
			
					FileWriter file = new FileWriter(path);
					file.write(root.toString());
					file.flush();
				}
		
			} catch (Exception e) {
				return "error";
			}
	
			if((getMeaning() != null) && (getMeaning().length() > 1)) {			
				return "success";
			} else {
				return "failure";
			}
		
		
	}

}
