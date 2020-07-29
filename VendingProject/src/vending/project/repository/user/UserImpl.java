package vending.project.repository.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vending.project.config.ConnectionFactory;
import vending.project.entity.product.Product;
import vending.project.entity.user.User;
import vending.project.repository.product.ProductImpl;

public class UserImpl implements UserRepository{
	static Scanner sc = new Scanner(System.in);
	static User user = new User();
	static Product pro = new Product();
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
	
	
	
	public  User login(User user) {
		System.out.println("-------------------------------------------");
		System.out.println("----------------LOGIN------------------");
		System.out.print("User Name: ");
		String username = sc.next();
		System.out.print("PassWord: ");
		String password = sc.next();
		
			try {
				String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
				conn = getConnection();
				ptmt = conn.prepareStatement(sql);
				ptmt.setString(1, username);
				ptmt.setString(2, password);
				rs = ptmt.executeQuery();
				
				while(rs.next()) {
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setAge(rs.getInt("age"));
					user.setEmail(rs.getString("email"));
					user.setPhone(rs.getString("phone"));
					user.setUserName(rs.getString("username"));
					user.setPassWord(rs.getString("password"));
					user.setMoney(rs.getInt("money"));
					user.setRole_id(rs.getInt("role_id"));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e ) {
				System.out.println("-------------------------------------------");
				System.out.println("Connection failed ~~");
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
			return user;
		
	}
	
	
	@Override
	public void add(User user) {
		System.out.println("-------------------------------------------");
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Age: ");
		int age = sc.nextInt();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Phone: ");
		String phone = sc.next();
		System.out.print("UserName: ");
		String username = sc.next();
		System.out.print("PassWord: ");
		String password = sc.next();
		try {
			String sql = "SELECT * FROM user ";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				user.setUserName(rs.getString("username"));
			}
			
			if (username.equals(user.getUserName())) {
				System.out.println("-------------------------------------------");
				System.out.println("The Account is already in use !");
				System.out.println("Do you want Sign Up ?");
				System.out.println("1. Yes\n2. No");
				System.out.print("Your choose: ");
				int choose = sc.nextInt();
				switch(choose) {
				case 1:
					add(user);
					break;
				case 2:
					break;
				}
			}
			else {
				String queryString = "INSERT INTO user(name, age, email, phone, username, password, role_id) VALUES(?,?,?,?,?,?,2)";
				ptmt = conn.prepareStatement(queryString);
				ptmt.setString(1, name);
				ptmt.setInt(2, age);
				ptmt.setString(3, email);
				ptmt.setString(4, phone);
				ptmt.setString(5, username);
				ptmt.setString(6, password);
				ptmt.executeUpdate();
				System.out.println("Sign Up Success ~");
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
	}
	
	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<User>();
		User user = new User();
		try {
			String queryString = "SELECT * FROM user";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				list.add(user);
				System.out.println("-------------------------------------------");
				user.display();
				System.out.println("-------------------------------------------");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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
		return list;
	}

	@Override
	public void update(User user) {
		try {
			String queryString = "UPDATE user set name = ?, age = ?, email = ?, phone = ?, username = ?, password = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			ptmt.setString(1, user.getName());
			ptmt.setInt(2, user.getAge());
			ptmt.setString(3, user.getEmail());
			ptmt.setString(4, user.getPhone());
			ptmt.setString(6, user.getPassWord());
			
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
	
	public static void updatePass(User user) {
		System.out.println("-------------------------------------------");
		System.out.print("New PassWord: ");
		String pass = sc.next();
		System.out.print("Enter a new PassWord: ");
		String pass1 = sc.next();
		if (pass1.equals(pass)) {
			try {
				String queryString = "UPDATE user set  password = ? WHERE id = ?";
				conn = getConnection();
				ptmt = conn.prepareStatement(queryString);
				ptmt.setString(1, pass1);
				ptmt.setInt(2, user.getId());
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
		else {
			System.out.println("-------------------------------------------");
			System.out.println("PassWord Incorrect !");
			updatePass(user);
		}
	}
	
	public static void findById(User user) {
		
		try {
			String queryString = "SELECT * FROM user WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			ptmt.setInt(1, user.getId());
			rs = ptmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setUserName(rs.getString("username"));
				user.setPassWord(rs.getString("password"));
				user.setMoney(rs.getInt("money"));
				user.display();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
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

	public static void inputMoney(User user) {
		try {
			System.out.println("-------------------------------------------");
			System.out.print("Input money: ");
			int money = sc.nextInt();
			String sql = "UPDATE user set money = ? WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, money + user.getMoney());
			ptmt.setInt(2, user.getId());
			
			ptmt.execute();
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
		System.out.println("Input money Success ~");
	}
	
	public static void updateMoney(User user) {
		try {
			String sql = "UPDATE user set money = ? WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, user.getMoney());
			ptmt.setInt(2, user.getId());
			ptmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e ) {
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
		System.out.println("Payment Success ~");
		System.out.println("You have Money: " + user.getMoney());
	}
	
	@Override
	public void delete(int id) {
		try {
			String queryString = "DELETE from user where id = ?";
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