package ua.com.forkShop.service;

import ua.com.forkShop.entity.User;

public interface UserService {

	void save(User user);
	
	int createNewUser();

	void addToShoppingCart(int userId, int itemId);
	
	void removeToShoppingCart(int userId, int itemId);

	void removeAllToShoppingCart(int userId);
	
	public void sendMail(String content, String email, String mailBody);

	User findByEmail(String email);
}
