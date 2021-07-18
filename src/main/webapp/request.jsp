<%@page import="com.akash.struts.Utils"%>
<%@page import="com.akash.struts.DbConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.akash.struts.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Requests</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="./styles/request.css">
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="app">
<table class="table table-hover table-bordered table-responsive">
	<thead>
		<tr>
    		<th scope="col">UserID</th>
    		<th scope="col">Username</th>
    		<th scope="col">Approval status</th>
    		<th scope="col">Set Level</th>
    		<th scope="col">Action</th>
  		</tr>
	</thead>
	<tbody>
<%
List l=(List)request.getAttribute("disp");
if(l!=null && (l.size() != 0)) {
	Iterator it=l.iterator();
	while(it.hasNext()) {
		UserBean b=(UserBean)it.next();
		int tempUserId = b.getUserId();
		String tempName = b.getUserName();
		int approved = b.getApproved();
		String tempApproved = "";
		if(b.getApproved() == 0){
			tempApproved = "Not yet";
		}	
%>
		<tr>
			<form name="fom" id="<%=tempUserId %>" method="post">
			<td><%= tempUserId %></td>
			<td><%= tempName %></td>
			<td><%= tempApproved %></td> 
			<td>
				<select id="level">
			<%
			ResultSet rs = DbConnection.getLevelOption();
			while(rs.next()){%>
				<option value="<%=rs.getInt("levelID") %>"><%=rs.getString("lName") %></option>
			<%}
			%>
				</select>
			</td>
			<td>
			<% if(DbConnection.checkTime(tempName) == 1){%>
				<a class="link-success" href="javascript:edit(<%=tempUserId %>)">Approve</a>	
			<%} else {%>
				<button type="button" class="btn btn-primary" id='<%=tempName %>' onClick="setName(this.id)" data-toggle="modal" data-target="#myModal1">Set Time</button>
			<%}%>
			</td>
			</form>
		</tr>
<!-- Modal -->
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Set Time</h4>
        </div>
        <div class="modal-body">
          <form action="setUserTime.action">
          <input type="text" name="name" value="<%=tempName %>" style="display:none" />
          <%
          	for(int i =0;i<7;i++){ 
		          int val=10; %>
          		<fieldset>      
                <legend><%=Utils.getDay(i) %></legend>
                <%
                	for(int j=0;j<11;j++){ %>
                		<input type="checkbox" name="<%=i %>" value=<%=val%>><%=val++ %>:00-<%=val %>:00		
                	<%}
                %>           
              </fieldset>
              <br>
          	<%}
          %>
              <button class="btn btn-primary" type="submit">Submit</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
<%

}
} else {
%>
	<tr><td colspan="5">No Records Found</td></tr>
<%
}
%>
</tbody>
</table>
</div>
<script type="text/javascript">
          var id;
          function setName(clicked_id){
              name = clicked_id;
          } 
          function edit(val){
        	var e = document.getElementById("level");
            var strUser = e.options[e.selectedIndex].value;
            var str = "approve.action?fid="+val+"&flevel="+strUser;
            document.getElementById(val).action="approve.action?fid="+val+"&flevel="+strUser;
            document.getElementById(val).submit();
          }
    </script>
	
</body>
</html>