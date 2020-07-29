package vending.project.repository.user.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import vending.project.config.ConnectionFactory;
import vending.project.entity.user.Role;
import vending.project.repository.product.ProductImpl;
import vending.project.repository.user.UserRepository;

public class RoleImpl implements RoleRepository {
	static Scanner sc = new Scanner(System.in);
	static Role role = new Role();
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
	
	
	@Override
	public void add(Role role) {
		System.out.print("Role Name: ");
		String name = sc.next();
		try {
			String queryString = "INSERT INTO role(name) VALUES(?)";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			ptmt.setString(1, name);
			
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
