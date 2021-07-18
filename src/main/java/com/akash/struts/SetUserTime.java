package com.akash.struts;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class SetUserTime {
	
	HttpServletRequest request =  ServletActionContext.getRequest();
	
	public String execute() throws SQLException, ClassNotFoundException {
		
		String name = request.getParameter("name");
		String[][] collection = {request.getParameterValues("0"),request.getParameterValues("1"),request.getParameterValues("2"),request.getParameterValues("3"),request.getParameterValues("4"),request.getParameterValues("5"),request.getParameterValues("6")};
		for(int i=0;i<collection.length;i++) {
			if(collection[i] != null) {
				DbConnection.addTime(name,Utils.getDay(i),Arrays.toString(collection[i]));
			}
		}
		DbConnection.close();
		return "success";
	}
}
