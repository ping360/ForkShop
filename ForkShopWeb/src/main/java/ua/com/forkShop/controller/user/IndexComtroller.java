package ua.com.forkShop.controller.user;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.forkShop.dto.filter.ItemFilter;
import ua.com.forkShop.entity.User;
import ua.com.forkShop.service.BrandService;
import ua.com.forkShop.service.CategoryService;
import ua.com.forkShop.service.DigitalUnitService;
import ua.com.forkShop.service.FeatureStringService;
import ua.com.forkShop.service.ItemService;
import ua.com.forkShop.service.UserService;
import ua.com.forkShop.validator.UserValidator;

@Controller
public class IndexComtroller {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private DigitalUnitService digitalUnitService;
	
	@Autowired
	private FeatureStringService featureStringService;

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
			HttpServletResponse response, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemFilter filter) {
		if (id == 0) {
			id = userService.createNewUser();
			response.addCookie(new Cookie("userId", String.valueOf(id)));
		}
		model.addAttribute("page", itemService.findAll(filter, pageable));
		model.addAttribute("brands", brandService.findAll());
		return "user-index";
	}
	
	@ModelAttribute("filter")
	public ItemFilter getFilter(){
		return new ItemFilter();
	}
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}

	@GetMapping("/shopping")
	public String shopping(Model model, @CookieValue(defaultValue = "0", name = "userId") int userId) {
		model.addAttribute("items", itemService.findByUserId(userId));
//		model.addAttribute("totalPrice", itemService.findAllPrice(itemId));
//		if (cartItemService.findAllPrice(cartId) != 0) {
//			model.addAttribute("cartItemPrice",
//					cartItemRepository.findAllPrice(cartId));
//		}
		return "user-shopping";
	}
	
	@GetMapping("/del/{itemId}")
	public String remove(@CookieValue(defaultValue = "0", name = "userId") int userId, @PathVariable int itemId) {
		userService.removeToShoppingCart(userId, itemId);
		return "redirect:/shopping";
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
	
	@PostMapping("/registration")
	public String save(@Valid User user, BindingResult br) {
		if (br.hasErrors())return "user-registration";
		userService.save(user);
		return "redirect:/login";
	}
	
	@RequestMapping("/cancel")
	public String cancel(Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/registration";
	}
	
	@RequestMapping("/cancelF")
	public String cancelF(Model model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/";
	}


	@GetMapping("/login")
	public String login() {
		return "user-login";
	}

	@RequestMapping("/item/{id}")
	public String hotelInCity(@PathVariable int id, Model model) {
		model.addAttribute("item", itemService.findOne(id));
		model.addAttribute("category", categoryService.findOneByItem(id));
		model.addAttribute("brand", brandService.findOneByItem(id));
//		model.addAttribute("digitalUnits", digitalUnitService.findAllByItem(id));
//		model.addAttribute("featureStrings" ,aditionalServiceService.findByHotelNameId(id));
		return "user-item";
	}
	@GetMapping("/iNeedIt")
	public String iNeedIt(@CookieValue(defaultValue = "0", name = "userId") int userId, Principal principal) {
		userService.sendMail("ForkShop", principal.getName(), "You buy shit!!!");
		userService.removeAllToShoppingCart(userId);
		return "user-success";
	}
	
}
