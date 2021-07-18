package com.akash.struts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Profile extends ActionSupport implements ServletRequestAware,ApplicationAware,SessionAware{
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	Map m;
	SessionMap<String,String> sessionmap;
	
	@Override
	public void setSession(Map map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, String>)map;  
	}

	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setApplication(Map m)
	{
		this.m=m;
	}   

	public String execute() throws ClassNotFoundException, SQLException {
		

		String userName = "";
		try {
			
		Cookie cw[] = request.getCookies();
		for(Cookie c : cw) {
			if(c.getName().equals("username")) {
				userName = c.getValue();
			}
		}
		} catch(Exception e) {
			userName="";
		}
		
		if(!userName.toLowerCase().equals("admin")) {
			Date date = new Date();
			String day = Utils.getDay(date.getDay());
			String hour = Integer.toString(date.getHours());
			
			String collection = DbConnection.checkUserTime(userName, day);
			
			collection = collection.substring(1,collection.length()-1);
			String[] arr = collection.split("[, ?.@]+");
			
			int flag=0;
			for( String a : arr) {
				if(a.equals(hour)) {
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				return "error";
			}
		}
		
		if((sessionmap.get("login") != null) && (userName.length() > 0)) {
			
			List li = null;
    		li = new ArrayList();
    		UserBean ub = null;
    	
			ResultSet rs = DbConnection.getProfile(userName);

			while(rs.next())
			{
			    ub = new UserBean();

			    ub.setUserId(rs.getInt(1));
			    ub.setUserName(rs.getString("username"));
			    ub.setApproved(rs.getInt("approved"));
			    ub.setApprovedTime(rs.getTimestamp("approvedTime"));
			    ub.setlName(rs.getString("lName"));
			    ub.setLevelID(rs.getInt("levelID"));
			    ub.setRegisterTime(rs.getTimestamp("registerTime"));  

			    li.add(ub);

			}

			request.setAttribute("dispProfile", li);
			DbConnection.close();
			return "success";
		} else {
			DbConnection.close();
			return "failure";
		}
	}
	
}
