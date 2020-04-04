//package com.cognizant.truyum.dao;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.cognizant.truyum.controller.MenuItemController;
//import com.cognizant.truyum.model.MenuItem;
//@Component
//public class MenuItemDaoSqlImpl implements MenuItemDao {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemDaoSqlImpl.class);
//	
//	PreparedStatement preparedStatement=null;
//	MenuItem menuItem=null;
//	@SuppressWarnings("rawtypes")
//	private List menuItemList;
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MenuItem> getMenuItemListAdmin() {
//		// TODO Auto-generated method stub
//		LOGGER.info("Start");
//		try {
//			String query ="select * from menu_item";
//			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
//			menuItemList = new ArrayList<MenuItem>();
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()){  
//				menuItemList.add(resultSet.getLong(1));
//				menuItemList.add(resultSet.getString(2));
//				menuItemList.add(resultSet.getFloat(3));
//				menuItemList.add(resultSet.getBoolean(4));
//				menuItemList.add(resultSet.getDate(5));
//				menuItemList.add(resultSet.getString(6));
//				menuItemList.add(resultSet.getBoolean(7));
//				}  
//		}
//		catch(SQLException |ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		LOGGER.info("end");
//		return menuItemList;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MenuItem> getMenuItemListCustomer() {
//		// TODO Auto-generated method stub
//		LOGGER.info("Start");
//		try {
//			String query ="select * from menu_item where Active='Yes' AND DateOfLaunch >=CURDATE() ";
//			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
//			menuItemList = new ArrayList<MenuItem>();
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()){  
//				menuItemList.add(resultSet.getLong(1));
//				menuItemList.add(resultSet.getString(2));
//				menuItemList.add(resultSet.getFloat(3));
//				menuItemList.add(resultSet.getBoolean(4));
//				menuItemList.add(resultSet.getDate(5));
//				menuItemList.add(resultSet.getString(6));
//				menuItemList.add(resultSet.getBoolean(7));
//				}  
//		}
//		catch(SQLException |ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		LOGGER.info("end");
//		return menuItemList;
//	}
//
//	@Override
//	public void modifyMenuItem(MenuItem menuItem) {
//		// TODO Auto-generated method stub
//		LOGGER.info("Start");
//		String query="Select * from menu_item groupby id";
//		try {
//			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
//			preparedStatement.execute();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LOGGER.info("end");
//	}
//
//	@Override
//	public MenuItem getMenuItem(long menuItemId) {
//		// TODO Auto-generated method stub
//		LOGGER.info("Start");
//		menuItem = new MenuItem();
//		
//		try {
//			String query="Select * from menu_item groupby id";
//			preparedStatement=ConnectionHandler.getConnection().prepareStatement(query);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while(resultSet.next()){  
//				menuItem.setId(resultSet.getLong(1));
//				menuItem.setName(resultSet.getString(2));
//				menuItem.setPrice(resultSet.getString(3));
//				menuItem.setActive(resultSet.getBoolean(4));
//				menuItem.setDateOfLaunch(resultSet.getDate(5));
//				menuItem.setCategory(resultSet.getString(6));
//				menuItem.setFreeDelivery(resultSet.getBoolean(7));
//				}  
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		LOGGER.info("end");
//		return menuItem;
//	}
//
//}
