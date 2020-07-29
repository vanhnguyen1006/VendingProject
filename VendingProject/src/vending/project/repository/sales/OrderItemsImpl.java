package vending.project.repository.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vending.project.config.ConnectionFactory;
import vending.project.entity.product.Product;
import vending.project.entity.sales.Order;
import vending.project.entity.sales.OrderItems;
import vending.project.entity.user.User;
import vending.project.repository.product.ProductImpl;
import vending.project.repository.user.UserRepository;

public class OrderItemsImpl implements OrderItemsRepository {
	static Scanner sc = new Scanner(System.in);
	static User user = new User();
	static Product pro = new Product();
	static Order order = new Order();
	static OrderItems orderI = new OrderItems();
	UserRepository userRepository;
	static ProductImpl proImpl = new ProductImpl();
	
	private static Connection conn = null;
	private static PreparedStatement ptmt = null;
	private static ResultSet rs = null;
	
	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	public static void addOrderI(Product pro, OrderItems orderItems) {
		try {
			String sql = "INSERT INTO orderitems (orderitem_id, product_id, quantity) VALUES (?,?,?)";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, orderItems.getId());
			ptmt.setInt(2, pro.getId());
			ptmt.setInt(3, orderItems.getQuantity());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptmt != null) {
					ptmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int countOrderI(OrderItems orderI) {
		try {
			String sql = "SELECT MAX(orderitem_id) FROM orderitems";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptmt != null) {
					ptmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	
	public void delete(int id) {
		try {
			String queryString = "DELETE FROM orderitem where id = ?";
			ptmt = conn.prepareStatement(queryString);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ptmt != null) {
					ptmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
