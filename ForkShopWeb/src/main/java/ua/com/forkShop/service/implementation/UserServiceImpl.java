package ua.com.forkShop.service.implementation;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	@PostConstruct
	public void addAdmin() {
		User user = userRepository.findByEmail("admin");
		if (user == null) {
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}
	}
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return userRepository.findByUsername(username);
//	}
//
//	@PostConstruct
//	public void addAdmin() {
//		User user = userRepository.findByUsername("admin");
//		if (user == null) {
//			user = new User();
//			user.setEmail("");
//			user.setPassword(encoder.encode("admin"));
//			user.setRole(Role.ROLE_ADMIN);
//			user.setUsername("admin");
//			userRepository.save(user);
//		}
//	}
	
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
	@Transactional
	public void removeToShoppingCart(int userId, int itemId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		Item item = itemRepository.findOne(itemId);
		cart.remove(item);
	}
	
	@Override
	@Transactional
	public void removeAllToShoppingCart(int userId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		List<Item> item = itemRepository.findAll();
		cart.removeAll(item);
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
	@Override
	public void sendMail(String content, String email, String mailBody) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"ping360p@gmail.com", "judaspr1est");
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ping360p@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			message.setSubject(content, "UTF-8");
			message.setText(mailBody);
			Transport.send(message);
		} catch (MessagingException е) {
			е.printStackTrace();
		}
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


}