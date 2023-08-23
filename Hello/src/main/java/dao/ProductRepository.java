package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Product;


public class ProductRepository {
	private Connection conn;
	private Product product;
	
	private ArrayList<Product> listOfProducts = new ArrayList<Product>();
	private static ProductRepository instance = new ProductRepository();

	public static ProductRepository getInstance(){
		return instance;
	} 


	public void getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "hello"; // c##추가
		String pwd = "hello1234"; // c##추가

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("데이터베이스 연결 준비 .....");
			conn = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getAllProducts() {
		getConn();
		String sql = "";
		sql += "select ";
		sql += 			"mc.ctg_name, s.ctg_name, p.p_id, p.p_name, p_price, p_description, p_manufacturer, p_unitsinstock, p_filename ";
		sql += "from ";
		sql += 			"product p,category_map m,sub_category s, major_category mc ";
		sql += "where ";
		sql += 			"p.p_id = m.p_id ";
		sql += 		"and m.ctg_id= s.ctg_id ";
		sql += 		"and s.major_ctg= mc.ctg_id ";
		sql += "order by ";
		sql += 			"major_ctg,s.ctg_id,p.p_id ";
		System.out.println(sql);
		try {
			Statement stmt = conn.createStatement(); // 2
			ResultSet rs = stmt.executeQuery(sql); // 3
			while (rs.next()) {  
				listOfProducts.add(new Product(rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(1),rs.getString(2)));
			} 
			if (rs != null)
				rs.close();
 			if (stmt != null)
 				stmt.close();
 			if (conn != null)
				conn.close();
		}catch (SQLException e) {
		e.printStackTrace();
		}
		return listOfProducts;
	}
	
	public Product getProductById(String productId) {
		Product productById = null;

		for (int i = 0; i < listOfProducts.size(); i++) {
			Product product = listOfProducts.get(i);
			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}
		return productById;
	}
	
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}
}
