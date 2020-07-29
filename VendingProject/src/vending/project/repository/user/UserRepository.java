package vending.project.repository.user;

import java.util.List;
import java.util.Optional;

import vending.project.entity.user.User;

public interface UserRepository {
	void add(User user);
	List<User> findAll();
	void update(User user);
	void delete(int id);
	User login(User user);
}
