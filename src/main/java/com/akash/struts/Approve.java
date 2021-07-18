package com.akash.struts;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

public class Approve implements ServletRequestAware{

	HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
	
	public String execute() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int a = Integer.parseInt(request.getParameter("fid"));
		int level = Integer.parseInt(request.getParameter("flevel"));
		int status = DbConnection.setApprove(a,level);
		DbConnection.close();
		if(status != 0) {
			return "success";
		} else {
			return "failure";
		}
		
	}
	
}
