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
		
	String ctg_name = (request.getParameter("ctg_name") == null) ? "" : request.getParameter("ctg_name").trim();
	QueryBean.getConnection();
	
	ArrayList resArr = new ArrayList();
	try {
		resArr = QueryBean.getProducts(ctg_name);
		
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
		out.print("\"p_id\":\"" + (String) resArr.get(0) + "\", ");
		out.print("\"p_name\":\"" + (String) resArr.get(1) + "\", ");
		out.print("\"p_price\":\"" + (String) resArr.get(2) + "\", ");
		out.print("\"p_description\":\"" + (String) resArr.get(3) + "\", ");
		out.print("\"p_filename\":\"" + (String) resArr.get(4) + "\", ");
		out.print("\"ctg_name\":\"" + (String) resArr.get(5) + "\" ");
		out.print("}");
		for (int i = 6; i < resArr.size(); i += 6) {
			out.print(",");
			out.print("{");
			out.print("\"p_id\":\"" + (String) resArr.get(i) + "\", ");
			out.print("\"p_name\":\"" + (String) resArr.get(i+1) + "\", ");
			out.print("\"p_price\":\"" + (String) resArr.get(i+2) + "\", ");
			out.print("\"p_description\":\"" + (String) resArr.get(i+3) + "\", ");
			out.print("\"p_filename\":\"" + (String) resArr.get(i+4) + "\", ");
			out.print("\"ctg_name\":\"" + (String) resArr.get(i+5) + "\" ");
			out.print("}");
		}
		out.println("]");
		out.println("}");
	}
%>