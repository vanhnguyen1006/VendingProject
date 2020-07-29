package vending.project.repository.sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import vending.project.config.ConnectionFactory;
import vending.project.entity.product.Product;
import vending.project.entity.sales.Order;
import vending.project.entity.sales.OrderItems;
import vending.project.entity.user.User;
import vending.project.repository.product.ProductImpl;

public class OrderImpl implements OrderRepository {
	static Scanner sc = new Scanner(System.in);
	static User user = new User();
	static Product pro = new Product();
	
	static Order order = new Order();
	
	static ProductImpl proImpl = new ProductImpl();
	
	private static Connection conn = null;
	private static PreparedStatement ptmt = null;
	private static ResultSet rs = null;
	
	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	public void addOrder(List<OrderItems> list, User user) {
		try {
			String sql = "INSERT INTO order (user_id, orderitem_id) VALUES(?,?)";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				ptmt.setInt(1, user.getId());
				ptmt.setInt(2, list.get(i).getId());
			}
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
