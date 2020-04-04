package com.cognizant.truyum.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.exception.SystemException;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;
import com.cognizant.truyum.validate.CustomValidator;

@Controller
@ComponentScan("com.cognizant.truyum.validate")
public class MenuItemController {
	@Autowired
	private MenuItemService service;

	@Autowired
	private CustomValidator custValidator;
	
	//private final PropertyEditorRegistrar customPropertyEditorRegistrar;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
//		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
//		binder.registerCustomEditor(Float.class, null, new CustomValidator() );
//		binder.registerCustomEditor(float.class, "", new CustomValidator() );
	}
	
	@RequestMapping(value="/go")
	public String local(ModelMap model) {
		model.addAttribute("price",100);
		return "zola";
	}
	
	@GetMapping(value="/show-menu-item-list-admin")
	public String showMenuItemListAdmin(ModelMap model) throws SystemException{
		LOGGER.info("show-menu-item-list-admin-Start-showMenuItemListAdmin-MenuItemController");
		model.addAttribute("menuItemList", service.getMenuItemListAdmin());
		LOGGER.info("show-menu-item-list-admin-End-showMenuItemListAdmin-MenuItemController");
		return "menu-item-list-admin";
	}
	@GetMapping(value="/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) throws SystemException{
		LOGGER.info("show-menu-list-customer-Start-showMenuItemListCustomer-MenuItemController");
		model.addAttribute("menuItemList", service.getMenuItemListCustomer());
		LOGGER.info("show-menu-list-customer-End-showMenuItemListCustomer-MenuItemController");
		return "menu-item-list-customer";
	}
	@GetMapping(value="/edit-menu-item")
	public String showEditMenuItem(@RequestParam int id,ModelMap model,@ModelAttribute("menuItem") MenuItem menuItem) {
		LOGGER.info("edit-menu-item-Start-showEditMenuItem-MenuItemController");
		model.put("menuItem", service.getMenuItem(id));
		//model.put("price", service.getMenuItem(id).getPrice());
		LOGGER.info("edit-menu-item-End-showEditMenuItem-MenuItemController");
		return "edit-menu-item";
	}
//	@PostMapping(value="/edit-menu-item")
	@RequestMapping(value = "/edit-menu-item", method = RequestMethod.POST)
	public String editMenuItem(ModelMap model,@ModelAttribute("menuItem") @Valid MenuItem menuItem,
			BindingResult bindingResult) {
		LOGGER.info("Post-edit-menu-item-Start-editEditMenuItem-MenuItemController");
		custValidator.validate(menuItem, bindingResult);
		//System.out.println("Cust");
		if(bindingResult.hasErrors()) {
			return "edit-menu-item";
		}
		service.modifyMenuItem(menuItem);
		LOGGER.info("Post-edit-menu-item-End-editEditMenuItem-MenuItemController");
		return "edit-menu-item-status";
	} 
}
