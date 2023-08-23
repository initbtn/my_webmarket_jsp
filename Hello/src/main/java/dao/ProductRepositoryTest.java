package dao;

import java.util.ArrayList;

import dto.Product;

public class ProductRepositoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductRepository dao=ProductRepository.getInstance();
		ArrayList<Product> listOfProducts = dao.getAllProducts();
 		for (int i = 0; i < listOfProducts.size(); i++) {
		Product product = listOfProducts. get(i);
		System.out.println(product.getMajorCtg()+"/"+product.getSubCtg()+"/"+product.getPname());
 		}
	}
}
