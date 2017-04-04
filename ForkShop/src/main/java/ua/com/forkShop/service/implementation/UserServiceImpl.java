package ua.com.forkShop.service.implementation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.forkShop.entity.Item;
import ua.com.forkShop.entity.Role;
import ua.com.forkShop.entity.ShopingCart;
import ua.com.forkShop.entity.User;
import ua.com.forkShop.repository.ItemRepository;
import ua.com.forkShop.repository.ShopingCartRepository;
import ua.com.forkShop.repository.UserRepository;
import ua.com.forkShop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private ShopingCartRepository shopingCartRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	@PostConstruct
	public void addAdmin() {
		User user = userRepository.findByUsername("admin");
		if (user == null) {
			user = new User();
			user.setEmail("");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			user.setUsername("admin");
			userRepository.save(user);
		}
	}
	
	@Override
	@Transactional
	public void addToShoppingCart(int userId, int itemId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		if(cart==null){
			cart = shopingCartRepository.save(new ShopingCart());
			user.setShopingCart(cart);
		}
		Item item = itemRepository.findOne(itemId);
		cart.add(item);
	}
	
	@Override
	public int createNewUser() {
		return userRepository.saveAndFlush(new User()).getId();
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public void setShopingCartRepository(ShopingCartRepository shopingCartRepository) {
		this.shopingCartRepository = shopingCartRepository;
	}

	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
}

}