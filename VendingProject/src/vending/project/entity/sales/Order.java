package vending.project.entity.sales;

import java.util.List;

import vending.project.entity.user.User;

public class Order {
	private int id;
	private User user;
	private List<OrderItems> orderItems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	public Order(int id, User user, List<OrderItems> orderItems) {
		super();
		this.id = id;
		this.user = user;
		this.orderItems = orderItems;
		
	}
	public Order() {
		super();
	}
	
	
	
	
	
}
