package com.akash.struts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class Admin extends ActionSupport implements ServletRequestAware,ApplicationAware{
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	Map m;

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

		request.setAttribute("dispAdmin", "Admin");
	
		return "success";
	}
	
}
