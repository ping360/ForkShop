package ua.com.forkShop.controller.user;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.forkShop.entity.User;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.ItemService;
import ua.com.forkShop.service.UserService;

@Controller
public class IndexComtroller {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;

	// @RequestMapping("/category/{id}")
	// public String category(@PathVariable int id, Model model) {
	// model.addAttribute("category", categoryService.findOne(id));
	// model.addAttribute("items", itemService.findByCategoryId(id));
	// return "user-category";
	// }

	// @RequestMapping("/")
	// public String index(Principal principal){
	// if(principal!=null){
	// System.out.println(principal.getName());
	// }
	// return "user-index";
	// }

	@RequestMapping("/")
	public String index(Model model, @CookieValue(defaultValue = "0", name = "userId") int id,
			HttpServletResponse response) {
		if (id == 0) {
			id = userService.createNewUser();
			response.addCookie(new Cookie("userId", String.valueOf(id)));
		}
		model.addAttribute("items", itemService.findAll());
		return "user-index";
	}

	@GetMapping("/shopping")
	public String shopping(Model model, @CookieValue(defaultValue = "0", name = "userId") int userId) {
		model.addAttribute("items", itemService.findByUserId(userId));
		return "user-shopping";
	}

	@GetMapping("/buy/{itemId}")
	public String buy(@CookieValue(defaultValue = "0", name = "userId") int userId, @PathVariable int itemId) {
		userService.addToShoppingCart(userId, itemId);
		return "redirect:/";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin-admin";
	}

	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
	@RequestMapping("/cancel")
	public String cancel(Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "user-login";
	}
}
