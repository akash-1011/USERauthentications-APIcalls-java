package com.akash.struts;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class User implements SessionAware {

	private String username;
	private String password;
	SessionMap<String, String> sessionmap;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map map) {
		// TODO Auto-generated method stub
		sessionmap = (SessionMap<String, String>) map;
	}

	public String checkCookie() {
		String name = "";
		try {
			Cookie cw[] = request.getCookies();
			for (Cookie c : cw) {
				if (c.getName().equals("username")) {
					sessionmap.put("login", "true");
					name = c.getValue();
				}
			}
		} catch (Exception e) {
			name = "";
		}
		return name;
	}

	public String validateSession()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String userName = checkCookie();

		if ((sessionmap.get("login") != null) && (userName.length() > 0)) {

			if (userName.toLowerCase().equals("admin")) {
				return "failure";
			}

			ResultSet status = DbConnection.checkApprove(userName);
			if (status.next()) {
				return "success";
			} else {
				return "error";
			}
		} else {
			return "login";
		}

	}

	public String login() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		setUsername(request.getParameter("username"));
		setPassword(request.getParameter("password"));

		ResultSet rs = DbConnection.checkRecord(getUsername(), getPassword());

		if (rs.next()) {

			Cookie cw = new Cookie("username", getUsername());
			cw.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cw);

			sessionmap.put("login", "true");

			ResultSet status = DbConnection.checkApprove(getUsername());

			if (status.next()) {
				DbConnection.close();
				return "success";
			} else {
				DbConnection.close();
				return "error";
			}

		} else if (getUsername().toLowerCase().equals("admin") && getPassword().toLowerCase().equals("admin")) {

			Cookie cw = new Cookie("username", getUsername());
			cw.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cw);

			sessionmap.put("login", "true");
			return "login";

		} else {
			return "failure";
		}
	}

	public String register()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		setUsername(request.getParameter("username"));
		setPassword(request.getParameter("password"));
		int status = DbConnection.addRecord(getUsername(), getPassword());

		if (status != 0) {
			DbConnection.close();
			return "success";
		} else {
			DbConnection.close();
			return "failure";
		}
	}

	public String logout() throws IOException {

		Cookie cookie = new Cookie("username", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		sessionmap.remove("login", "true");
		sessionmap.invalidate();
		return "success";
	}

}
