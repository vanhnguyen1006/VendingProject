package vending.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vending.project.entity.product.Product;
import vending.project.entity.sales.Order;
import vending.project.entity.sales.OrderItems;
import vending.project.entity.user.User;
import vending.project.repository.product.ProductImpl;
import vending.project.repository.sales.OrderImpl;
import vending.project.repository.sales.OrderItemsImpl;
import vending.project.repository.user.UserImpl;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	static User user = new User();
	static Product pro = new Product();
	static Order order = new Order();
	static OrderItems orderItem = new OrderItems();
	
	static UserImpl userImpl = new UserImpl();
	static ProductImpl proImpl = new ProductImpl();
	static OrderImpl orderImpl = new OrderImpl();
	static OrderItemsImpl orderItemImpl = new OrderItemsImpl();
	
	static List<Order> orderList = new ArrayList();
	static List<OrderItems> orderItemsList = new ArrayList();
	static List<Product> productList = new ArrayList();
	static List<User> userList = new ArrayList();
	
	static boolean newCart = false;
	static int count = orderItemImpl.countOrderI(orderItem);
	
	public static void showMenuUser(User user) {
		System.out.println("--------------------------------------------");
		System.out.println("--------------User--Menu----------------");
		System.out.println("1.View Products");
		System.out.println("2.Add Product to Cart");
		System.out.println("3.View Cart");
		System.out.println("4.View Profile");
		System.out.println("5.Input money");
		System.out.println("6.Change PassWord");
		System.out.println("7.Log Out");
		System.out.println("--------------------------------------------");
		System.out.print("Your choose: ");
		String choose = sc.next();
		switch (choose) {
		case "1":
			System.out.println("--------------------------------------------");
			proImpl.findAll(pro);
			System.out.println("--------------------------------------------");
			showMenuUser(user);
			break;
		case "2":
			addToCart(user);
			showMenuUser(user);
			break;
		case "3":
			viewCart(user);
			showMenuUser(user);
			break;
		case "4":
			System.out.println("--------------------------------------------");
			userImpl.findById(user);
			showMenuUser(user);
			break;
		case "5":
			userImpl.inputMoney(user);
			showMenuUser(user);
			break;
		case "6":
			userImpl.updatePass(user);
			System.out.println("--------------------------------------------");
			System.out.println("Success ~~");
			showMenuUser(user);
			break;
		case "7":
			showLogin();
			break;
		default:
			System.out.println("Error ~~");
			showMenuUser(user);
			break;
		}
		
	}
	
	public static void showMenuAdmin() {
		System.out.println("--------------------------------------------");
		System.out.println("----------Admin---Menu---------------");
		System.out.println("1.View Products");
		System.out.println("2.Add Product");
		System.out.println("3.Edit Product");
		System.out.println("4.Delete Product");
		System.out.println("5.View Profile User");
		System.out.println("6.Log Out");
		System.out.println("--------------------------------------------");
		System.out.print("Your choose: ");
		String choose = sc.next();
		switch(choose) {
		case "1":
			proImpl.findAll(pro);
			showMenuAdmin();
			break;
		case "2":
			proImpl.addProduct(pro);
			showMenuAdmin();
			break;
		case "3":
			proImpl.update(pro);
			showMenuAdmin();
			break;
		case "4":
			proImpl.delete();
			showMenuAdmin();
			break;
		case "5":
			userImpl.findAll();
			showMenuAdmin();
			break;
		case "6":
			showLogin();
			break;
		default:
			System.out.println("Error ~~");
			showMenuAdmin();
			break;
		}
	}
	
	public static void showLogin() {
		System.out.println("------------------------------------------");
		System.out.println("------------------Menu-----------------");
		System.out.println("--                1.Login");
		System.out.println("--                2.Sign up");
		System.out.println("--                3.Exit");
		System.out.println("------------------------------------------");
		System.out.println("------------------------------------------");
		System.out.print("Your choose: ");
		String choose = sc.next();
		switch (choose) {
		case "1":
			userImpl.login(user);
			switch(user.getRole_id()) {
			case 1:
				showMenuAdmin();
				break;
			case 2:
				showMenuUser(user);
				break;
			}
			break;
		case "2":
			userImpl.add(user);
			showLogin();
			break;
		case "3":
			System.out.println("Bye ~~");
			break;
		default:
			System.out.println("------------------------------------------");
			System.out.println("Erro ~~");
			showLogin();
			break;
		}
	}
	
	public static void addToCart(User user) {
		OrderItems orderICart = new OrderItems();
		Product proCart = new Product();
		while (proCart.getName() == null) {
			System.out.println("------------------------------------------");
			System.out.print("Choose Product ID: ");
			int proId = sc.nextInt();
			proCart = proImpl.findById(proId);
			if (proCart.getName() == null) {
				System.out.println("Error !!");
			}
		}
		System.out.print("The Amount " + proCart.getName() + " do you want to buy: ");
		int quantity = sc.nextInt();
		orderICart.setProduct(proCart);
		orderICart.setQuantity(quantity);
		
		orderItemsList.add(count, orderICart);
		
		order.setOrderItems(orderItemsList);
		order.setUser(user);
		count+=1;
		newCart = true;
	}
	
	public static void viewCart(User user) {
		int total = 0;
		boolean stop = false;
		int stocknew = 0;
		System.out.println("------------------------------------------");
		System.out.println("Bill: ");
		for (int i = 0; i < orderItemsList.size(); i++) {
			int price = order.getOrderItems().get(i).getProduct().getPrice();
			int quantity = order.getOrderItems().get(i).getQuantity();
			int stock = order.getOrderItems().get(i).getProduct().getStock();
			System.out.printf("%-30s", order.getOrderItems().get(i).getProduct().getName());
			System.out.printf("%-30s", price);
			System.out.printf("%-30s", quantity);
			System.out.println();
			if (newCart == true) {
				int tmt = price * quantity;	
				 total += tmt;
			}
		}
		System.out.println("Total: " + total);
		System.out.println("Your money: " + user.getMoney());
		while (user.getMoney() < total) {
			System.out.println("------------------------------------------");
			System.out.println("Your money: " + user.getMoney());
			System.out.println("Do you want input money ?\n1. Yes\n2. No ?");
			System.out.print("You choose: ");
			int choose = sc.nextInt();
			if (choose == 1) {
				userImpl.inputMoney(user);
				userImpl.findById(user);
				user.getMoney();
			}
			else {
				stop = true;
				break;
			}
		}
		if (stop == true) {
			showMenuUser(user);
		}
		else {
			
			System.out.println("------------------------------------------");
			System.out.println("Payment ?");
			System.out.println("1.Yes\n2.No");
			System.out.println("------------------------------------------");
			System.out.print("Your choose: ");
			int choose = sc.nextInt();
			System.out.println("------------------------------------------");
			if (choose == 1) {
				count++;
				userImpl.findById(user);
				int price = user.getMoney() - total;
				user.setMoney(price);
				userImpl.updateMoney(user);
				for (int i = 0; i < orderItemsList.size(); i++) {
					order.getOrderItems().get(i).setId(count);
					orderItemImpl.addOrderI(order.getOrderItems().get(i).getProduct(), order.getOrderItems().get(i));
				}
				orderImpl.addOrder(orderItemsList, user);
			}
			else {
				showMenuUser(user);
			}
		}
	}

	public static void main(String[] args) {
		showLogin();
	}
}
