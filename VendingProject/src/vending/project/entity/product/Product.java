package vending.project.entity.product;

public class Product {
	private int id;
	private String name;
	private int price;
	private String category;
	private String brand;
	private int stock;
	public Product(int id, String name, int price, String category, String brand, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.brand = brand;
		this.stock = stock;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Product:\nId: " + id + "\nName:" + name + "\nPrice: " + price + "\nCategory: " + category + "\nBrand: "
				+ brand + "\nStock: " + stock;
	}
	
	public void display() {
		System.out.println(this);
	}
	
	
}
