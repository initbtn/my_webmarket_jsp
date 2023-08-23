<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="QueryBean" scope="page" class="persistence.beans.QueryBean" />
<jsp:setProperty property="*" name="QueryBean" />

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
// 	String user_id = "";
// 	if(request.getParameter("user_id") == null )
// 		user_id = "";
// 	else
// 		user_id = request.getParameter("user_id").trim();
		
	String majorCtgName = (request.getParameter("majorCtgName") == null) ? "" : request.getParameter("majorCtgName").trim();
	
	QueryBean.getConnection();
	
	ArrayList resArr = new ArrayList();
	try {
		resArr = QueryBean.getCtgInfo_S(majorCtgName);
		
	} catch (SQLException e) {
		out.print(e.toString());
	} finally {
		QueryBean.closeConnection();
	}
	out.println("{");
	out.println("\"datas\":[");
	if (resArr.size() == 0) {
		out.println("}");
		out.println("}");
	} else {
		out.print("{");
		out.print("\"majorCtgName\":\"" + (String) resArr.get(0) + "\", ");
		out.print("\"subCtgId\":\"" + (String) resArr.get(1) + "\", ");
		out.print("\"subCtgName\":\"" + (String) resArr.get(2) + "\" ");
		out.print("}");
		for (int i = 3; i < resArr.size(); i += 3) {
			out.print(",");
			out.print("{");
			out.print("\"majorCtgName\":\"" + (String) resArr.get(i) + "\", ");
			out.print("\"subCtgId\":\"" + (String) resArr.get(i+1) + "\", ");
			out.print("\"subCtgName\":\"" + (String) resArr.get(i+2) + "\" ");
			out.print("}");
		}
		out.println("]");
		out.println("}");
	}
%>