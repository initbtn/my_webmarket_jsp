package dto;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -4274700572038677000L;

	private String productId;	//상품 아이디
	private String pname;		//상품명
	private Integer unitPrice; //상품 가격
	private String description; //상품 설명
	private String manufacturer;//제조사
	private String category; 	//분류
	private Integer unitsInStock; //재고수 
	private String condition; 	//신상품 or 중고품 or 재생품
	private String filename; 	//이미지 파일명
	private int quantity;		 //장바구니에 담은 개수
	private String majorCtg;	 //카테고리명
	private String subCtg;  	 //서브카테고리		

	public Product() {
		super();
	}

	public Product(String productId, String pname, Integer unitPrice) {
		this.productId = productId;
		this.pname = pname;
		this.unitPrice = unitPrice;
	}

	
	
	public Product(String productId, String pname, Integer unitPrice, String description, String manufacturer,
			Integer unitsInStock, String filename, String majorCtg, String subCtg) {
		super();
		this.productId = productId;
		this.pname = pname;
		this.unitPrice = unitPrice;
		this.description = description;
		this.manufacturer = manufacturer;
		this.unitsInStock = unitsInStock;
		this.filename = filename;
		this.majorCtg = majorCtg;
		this.subCtg = subCtg;
	}

	public String getProductId() {
		return productId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMajorCtg() {
		return majorCtg;
	}

	public String getSubCtg() {
		return subCtg;
	}

	public void setMajorCtg(String majorCtg) {
		this.majorCtg = majorCtg;
	}

	public void setSubCtg(String subCtg) {
		this.subCtg = subCtg;
	}			
	
}
