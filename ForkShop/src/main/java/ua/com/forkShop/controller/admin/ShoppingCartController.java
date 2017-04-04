package ua.com.forkShop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.com.forkShop.dto.Quantity;
import ua.com.forkShop.service.ItemService;

@ControllerAdvice
public class ShoppingCartController {
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute("quantity")
	public Quantity getQuantity(@CookieValue(defaultValue="0", name="userId") int userId){
		int count = itemService.findCount(userId);
		return new Quantity(count);
	}
}
