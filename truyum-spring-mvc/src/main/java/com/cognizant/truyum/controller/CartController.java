package com.cognizant.truyum.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;

@Controller
public class CartController {

	@Autowired
	private CartService service;
	
	List<MenuItem> list;

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@GetMapping(value = "/add-to-cart")
	public String addToCart(@RequestParam int id, ModelMap model) {
		LOGGER.info("add-to-cart-Start-addToCart-CartController");
		service.addCartItem(1, id);
		model.put("addCartStatus",true);
		LOGGER.info("add-to-cart-end-addToCart-CartController");
		return "forward:/show-menu-list-customer";
	}

	@GetMapping(value = "/show-cart")
	public String showCart(@RequestParam int userId, ModelMap model) {
		LOGGER.info("show-cart-Start-showCart-CartController");
		double total=0;
		try {
			list = service.getAllCartItems(userId);
			model.addAttribute("menuItemList",list);
			//System.out.println(service.getAllCartItems(userId).get);
			for (int i = 0; i < service.getAllCartItems(userId).size(); i++) {
				if(Float.parseFloat(service.getAllCartItems(userId).get(i).getPrice())>0)
					total+=Float.parseFloat(service.getAllCartItems(userId).get(i).getPrice());
			}
			model.addAttribute("total",total);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block			
			LOGGER.info("show-cart-catch-showCart-CartController");
			return "cart-empty";
		}
		//System.out.println(total);
		LOGGER.info("show-cart-end-showCart-CartController");
		return "cart";
	}

	@GetMapping(value = "/remove-cart")
	public String removeCart(@RequestParam int menuItemId, int userId, ModelMap model) {
		LOGGER.info("remove-cart-Start-removeCart-CartController");
		service.removeCartItem(userId, menuItemId);
		try {
			model.addAttribute("cartList", service.getAllCartItems(userId));
		} catch (CartEmptyException e) {
			LOGGER.info("remove-cart-end-catch-removeCart-CartController");
			return "cart-empty";
		}
		model.addAttribute("removeCartItemStatus", true);
		LOGGER.info("remove-cart-end-removeCart-CartController");
		return "cart";
	}
}
