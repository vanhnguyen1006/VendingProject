package vending.project.repository.product;

import java.util.List;

import vending.project.entity.product.Product;

public interface ProductRepository {
	void addProduct(Product product);
	List<Product> findAll(Product pro);
	void update(Product product);
	void delete();

}
