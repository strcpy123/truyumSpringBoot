package com.cognizant.truyum.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.dao.ConnectionHandler;
import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Component
public class MenuItemService implements MenuItemDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemService.class);

	PreparedStatement preparedStatement = null;
	MenuItem menuItem = null;
	@SuppressWarnings("rawtypes")
	private List menuItemList;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> getMenuItemListAdmin() {

		LOGGER.info("show-menu-item-list-admin-Start");
		try {

			String query = "select * from menu_items";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			menuItemList = new ArrayList<MenuItem>();

			while (resultSet.next()) {
				menuItem = new MenuItem(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getBoolean(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getBoolean(7));
				menuItemList.add(menuItem);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("show-menu-item-list-admin-end");

		return menuItemList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItem> getMenuItemListCustomer() {
		// TODO Auto-generated method stub
		LOGGER.info("show-menu-list-customer-Start-MenuItemService");
		try {
			String query = "select * from menu_items where actives=1 AND DateOfLaunch >= CURDATE() ";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			menuItemList = new ArrayList<MenuItem>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				menuItem = new MenuItem();
				menuItem.setId(resultSet.getInt(1));
				menuItem.setName(resultSet.getString(2));
				menuItem.setPrice(resultSet.getString(3));
				menuItem.setActive(resultSet.getBoolean(4));
				menuItem.setDateOfLaunch(resultSet.getDate(5));
				menuItem.setCategory(resultSet.getString(6));
				menuItem.setFreeDelivery(resultSet.getBoolean(7));
				menuItemList.add(menuItem);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("show-menu-list-customer-end-MenuItemService");
		return menuItemList;
	}

	@Override
	public void modifyMenuItem(MenuItem menuItem) {
		LOGGER.info("Post-edit-menu-item-Start-MenuItemService");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String date = simpleDateFormat.format(menuItem.getDateOfLaunch());
		String query = "UPDATE menu_items set Namess='" + menuItem.getName() + "',Price='" + menuItem.getPrice()
				+ "',actives=" + menuItem.isActive() + ",DateOfLaunch='" + date + "',category='"
				+ menuItem.getCategory() + "',FreeDelivery=" + menuItem.isFreeDelivery() + " where id ="
				+ menuItem.getId();
		try {
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Post-edit-menu-item-end-MenuItemService");
	}

	@Override
	public MenuItem getMenuItem(long menuItemId) {
		// TODO Auto-generated method stub
		LOGGER.info("edit-menu-item-Start-MenuItemService");
		menuItem = new MenuItem();

		try {
			String query = "Select * from menu_items where id =" + menuItemId + " group by id";
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				menuItem.setId(resultSet.getLong(1));
				menuItem.setName(resultSet.getString(2));
				menuItem.setPrice(resultSet.getString(3));
				menuItem.setActive(resultSet.getBoolean(4));
				menuItem.setDateOfLaunch(resultSet.getDate(5));
				menuItem.setCategory(resultSet.getString(6));
				menuItem.setFreeDelivery(resultSet.getBoolean(7));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("edit-menu-item-end-MenuItemService");
		return menuItem;
	}

}
