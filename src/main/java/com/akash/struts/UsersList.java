package com.akash.struts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UsersList extends ActionSupport implements ServletRequestAware, ApplicationAware, SessionAware {
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	SessionMap<String, String> sessionmap;
	Map m;

	@Override
	public void setSession(Map map) {
		// TODO Auto-generated method stub
		sessionmap = (SessionMap<String, String>) map;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setApplication(Map m) {
		this.m = m;
	}

	public String execute()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		String name = "";
		try {
			Cookie cw[] = request.getCookies();
			for (Cookie c : cw) {
				if (c.getName().equals("username")) {
					name = c.getValue();
				}
			}
		} catch (Exception e) {
			name = "";
		}

		if ((sessionmap.get("login") != null)) {

			List<UserBean> li = null;
			li = new ArrayList<UserBean>();
			UserBean ub = null;
			ResultSet rs = null;

			if (name.toLowerCase().equals("admin")) {
				rs = DbConnection.getUsersAdmin();
			} else {
				rs = DbConnection.getUsers(name);
			}

			while (rs.next()) {
				ub = new UserBean();

				ub.setUserId(rs.getInt(1));
				ub.setUserName(rs.getString("username"));
				ub.setApproved(rs.getInt("approved"));
				ub.setlName(rs.getString("lName"));
				ub.setRegisterTime(rs.getTimestamp("registerTime"));
				ub.setApprovedTime(rs.getTimestamp("approvedTime"));

				li.add(ub);

			}

			request.setAttribute("dispUser", li);
			DbConnection.close();
			return "success";
		} else {
			DbConnection.close();
			return "failure";
		}
	}

}
