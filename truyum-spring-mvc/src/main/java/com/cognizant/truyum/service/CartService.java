package com.cognizant.truyum.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.controller.MenuItemController;
import com.cognizant.truyum.dao.CartDao;
import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.dao.ConnectionHandler;
import com.cognizant.truyum.model.MenuItem;

@Component
public class CartService implements CartDao {
	
	PreparedStatement preparedStatement = null;
	@Autowired
	MenuItemService service;
	private MenuItem menuItem;
	private List<MenuItem> menuItemList;

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);

	@Override
	public void addCartItem(long userId, long menuItemId) {
		LOGGER.info("add-to-cart-Start-addCartItem-Cartservice");
		// TODO Auto-generated method stub
		String query = "INSERT INTO CART VALUES(?,?,?,?,?)";
		MenuItem menuItem = service.getMenuItem(menuItemId);
		try {
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.setString(1, menuItem.getName());
			preparedStatement.setBoolean(2, menuItem.isFreeDelivery());
			preparedStatement.setString(3, menuItem.getPrice());
			preparedStatement.setInt(4, (int) menuItemId);
			preparedStatement.setInt(5, (int) userId);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("add-to-cart-end-addCartItem-Cartservice");

	}

	@Override
	public List<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		LOGGER.info("show-cart-Start-getAllCartItem-Cartservice");
		menuItemList = new ArrayList<>();
		ResultSet resultSet = null;
		
		
		try {
			//System.out.println("1");
			String query = "select menu_id,Namess,FreeDelivery,Price from Cart where customer_id =" + (int) userId;
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			//System.out.println("2");
			System.out.println("Start before Loop:"+resultSet.next());
			if(resultSet.next()==false) {
				//System.out.println("3");
				throw new CartEmptyException();
			}
			else {
			while (resultSet.next()) {
				//System.out.println("Inside Loop:"+resultSet.next());
				menuItem = new MenuItem();
				menuItem.setId(resultSet.getInt(1));
				menuItem.setName(resultSet.getString(2));
				menuItem.setFreeDelivery(resultSet.getBoolean(3));
				menuItem.setPrice(resultSet.getString(4));
				menuItemList.add(menuItem);
			}
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Khali");
			throw new CartEmptyException();
		}
		
		
		LOGGER.info("show-cart-end-getAllCartItem-Cartservice");
		return menuItemList;
	}

	@Override
	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		LOGGER.info("remove-cart-Start-removeCartItem-Cartservice");
		String query = "DELETE FROM CART where menu_id=" + menuItemId +"customer_id"+userId;
		try {
			preparedStatement = ConnectionHandler.getConnection().prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("remove-cart-end-removeCartItem-Cartservice");

	}

}
