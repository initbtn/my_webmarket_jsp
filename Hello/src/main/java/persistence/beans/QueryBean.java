package persistence.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBean 
{

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public QueryBean() {

		conn = null;
		stmt = null;
		rs = null;

	}

	public void getConnection() {

		try {

			conn = DBConnection.getConnection();

		} catch (Exception e1) {

			e1.printStackTrace();
		}

		try {

			stmt = conn.createStatement();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void closeConnection() 
	{
		if (stmt != null) {

			try {

				stmt.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		if (conn != null) {

			try {
				conn.close();

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	//1.select all
	public ArrayList getCtgInfo_M() throws Exception {

		StringBuffer sb = new StringBuffer();

		sb.append("SELECT");
		sb.append("   CTG_ID,CTG_NAME ");
		sb.append(" FROM ");
		sb.append("   MAJOR_CATEGORY ");

		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			
		}
		System.out.println(sb.toString());
		return res;
	}
	
	public ArrayList getCtgInfo_S(String majorCtgName) throws Exception {

		StringBuffer sb = new StringBuffer();

		sb.append(" select "); 
		sb.append("         m_ctg_name, s_ctg_id, s_ctg_name ");
		sb.append(" from  ");
		sb.append("         ctg_info  ");
		sb.append(" where  ");
		sb.append("         m_ctg_name ='"+ majorCtgName +"' ");
		sb.append(" order by  ");
		sb.append("         s_ctg_name ");
		
		System.out.println("쿼리 문장: "+sb.toString());
		
		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			
		}
		System.out.println(sb.toString());
		return res;
	}
	
	public ArrayList getProducts(String ctg_name) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select "); 
		sb.append("         p_id, p_name, p_price, p_description, p_filename, ctg_name ");
		sb.append(" from  ");
		sb.append("         p_list  ");
		sb.append(" where  ");
		sb.append("         ctg_name like '%"+ ctg_name +"%'");
		
		System.out.println("쿼리 문장: "+sb.toString());
		
		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			res.add(rs.getString(6));
			
		}
		System.out.println(sb.toString());
		return res;
	}
	
	//2.insert
	public int insertUserInfo(String user_id, String user_name, String user_phone, String user_grade)//시간빼고 다 받아옴, db시간을 쓰기위해서
	{
		String query = "";
		PreparedStatement pstmt = null;
		
		int result = 0;//1:성공, 0:실패
		
		query  = " insert into ";
		query += "            USER_INFO_SAMPLE (U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME) ";
		query += " values ";
//		query += "        ('"+ user_id +"','"+ user_name +"','"+user_phone+"', '"+user_grade+"', sysdate) ";
		query += "        (?, ?, ?, ?, sysdate) ";
		
		System.out.println(query);
		
		try 
		{
			pstmt = conn.prepareStatement(query);
			
			pstmt.clearParameters();
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_phone);
			pstmt.setString(4, user_grade);
			
			
			result = pstmt.executeUpdate();//쿼리를 db에 날린다 최종적으로!
			//executeUpdate()함수는 insert, update, delete에만 쓴다! select에는 안쓴다!
			//왜 그러냐면, 반환값이 다르다~! int이다~! cf)select는 ResultSet으로 반환한다.
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
		}
		return result;
	}
	
	//3.update
	public int updateUserInfo(String user_id, String user_name, String user_phone, String user_grade)//시간빼고 다 받아옴, db시간을 쓰기위해서
	{
		StringBuffer query1 = new StringBuffer();
		
		//String query = "";
		PreparedStatement pstmt = null;
		
		int result = 0;//1:성공, 0:실패
		
		query1.append(" update ");
		query1.append("        USER_INFO_SAMPLE ");
		query1.append(" set ");
//		query1.append("        U_NAME = '"+user_name+"', U_PHONE = '"+user_phone+"', U_GRADE = "+user_grade+", WRITE_TIME = sysdate ");
		query1.append("        U_NAME = ?, U_PHONE = ?, U_GRADE = ?, WRITE_TIME = SYSDATE ");
		query1.append(" where ");
//		query1.append("        U_ID = '"+user_id+"' ");
		query1.append("        U_ID = ? ");
		
		
		System.out.println(query1.toString());
		
		try 
		{
			pstmt = conn.prepareStatement(query1.toString());
			
			pstmt.clearParameters();
			
			pstmt.setString(1, user_name);
			pstmt.setString(2, user_phone);
			pstmt.setString(3, user_grade);
			pstmt.setString(4, user_id);
			
			result = pstmt.executeUpdate();//쿼리를 db에 날린다 최종적으로!
			//executeUpdate()함수는 insert, update, delete에만 쓴다! select에는 안쓴다!
			//왜 그러냐면, 반환값이 다르다~! int이다~! cf)select는 ResultSet으로 반환한다.
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
		}
		return result;
	}
	
	
	//4.delete
	public int deleteUserInfo(String user_id)//시간빼고 다 받아옴, db시간을 쓰기위해서
	{
		StringBuffer query1 = new StringBuffer();
		
		//String query = "";
		PreparedStatement pstmt = null;
		
		int result = 0;//1:성공, 0:실패
		
		query1.append(" delete ");
		query1.append("       from ");
		query1.append("              USER_INFO_SAMPLE ");
		query1.append("       where");
//		query1.append("              U_ID = '"+user_id+"'");
		query1.append("              U_ID = ?");
		
		
		System.out.println(query1.toString());
		
		try 
		{
			pstmt = conn.prepareStatement(query1.toString());
			
			pstmt.clearParameters();
			
			pstmt.setString(1, user_id);
			
			result = pstmt.executeUpdate();//쿼리를 db에 날린다 최종적으로!
			//executeUpdate()함수는 insert, update, delete에만 쓴다! select에는 안쓴다!
			//왜 그러냐면, 반환값이 다르다~! int이다~! cf)select는 ResultSet으로 반환한다.
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try 
			{
				pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}	
		}
		return result;
	}
	

}








