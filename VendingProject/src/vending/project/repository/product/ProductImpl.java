package vending.project.repository.product;

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

public class ProductImpl implements ProductRepository{
	static Scanner sc = new Scanner(System.in);
	private static Product pro = new Product();
	private static Connection conn = null;
	private static PreparedStatement ptmt = null;
	private static ResultSet rs = null;
	
	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}

	@Override
	public void addProduct(Product pro) {
		System.out.print("Product Name: ");
		String name = sc.nextLine();
		System.out.print("Product Price: ");
		int price = sc.nextInt();
		System.out.print("Category: ");
		sc.nextLine();
		String cate = sc.nextLine();
		System.out.print("Brand: ");
		String br = sc.nextLine();
		System.out.print("Stock: ");
		int stock = sc.nextInt();
		try {
			String queryString = "INSERT INTO product (name, price, category, brand, stock) VALUES (?,?,?,?,?)";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			ptmt.setString(1, name);
			ptmt.setInt(2, price);
			ptmt.setString(3, cate);
			ptmt.setString(4, br);
			ptmt.setInt(5, stock);
			
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

	public List<Product> findAll(Product pro) {
		List<Product> list = new ArrayList<Product>();
		try {
			String queryString = "SELECT * FROM product";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				pro.setPrice(rs.getInt("price"));
				pro.setCategory(rs.getString("category"));
				pro.setBrand(rs.getString("brand"));
				pro.setStock(rs.getInt("stock"));
				list.add(pro);
				System.out.println(rs.getInt("id")
												+ ".Product Name: " + rs.getString("name") + "; "
												+ "Price: " + rs.getInt("price") + "; "
												+ "Category: " + rs.getString("category") + "; "
												+ "Brand: " + rs.getString("brand") + ";"
												+ "Stock: " + rs.getInt("stock"));
								
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
	
	public Product findById(int id) {
		Product pro = new Product();
		try {
			String sql = "SELECT * FROM product WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				pro.setPrice(rs.getInt("price"));
				pro.setCategory(rs.getString("category"));
				pro.setBrand(rs.getString("brand"));
				pro.setStock(rs.getInt("stock"));
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
		return pro;
	}

	@Override
	public void update(Product pro) {
		
		try {
			System.out.print("Choose id product: ");
			int pro_id = sc.nextInt();
			System.out.print("Product Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Product Price: ");
			int price = sc.nextInt();
			System.out.print("Category: ");
			sc.nextLine();
			String cate = sc.nextLine();
			System.out.print("Brand: ");
			String br = sc.nextLine();
			System.out.print("Stock: ");
			int stock = sc.nextInt();
			
			conn = getConnection();
			rs = ptmt.executeQuery();
			String sql = "UPDATE product set name = ?, price = ?, category = ?, brand = ?, stock = ? WHERE id = ?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, name);
			ptmt.setInt(2, price);
			ptmt.setString(3, cate);
			ptmt.setString(4, br);
			ptmt.setInt(5, stock);
			ptmt.setInt(6, pro_id);
			
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
	
	public static void updateStock(Product pro) {
		try {
			String sql = "UPDATE product set stock = ? WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, pro.getStock());
			ptmt.setInt(2, pro.getId());
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
	}

	@Override
	public void delete() {
		System.out.print("Enter the id you want to delete: ");
		int id = sc.nextInt();
		try {
			String queryString = "DELETE FROM product WHERE id = ?";
			conn = getConnection();
			ptmt = conn.prepareStatement(queryString);
			ptmt.setInt(1, id);
			
			ptmt.execute();
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
