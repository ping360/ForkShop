package ua.com.forkShop.service;

import ua.com.forkShop.entity.User;

public interface UserService {

	void save(User user);
	
	int createNewUser();

	void addToShoppingCart(int userId, int itemId);
}
