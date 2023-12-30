package com.onlinefoodordering.onlineOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.onlinefoodordering.onlineOrder.dao.ItemOrderDao;
import com.onlinefoodordering.onlineOrder.entity.Customer;
import com.onlinefoodordering.onlineOrder.entity.MenuItem;
import com.onlinefoodordering.onlineOrder.entity.OrderItem;

@Service
public class ItemOrderService {

    @Autowired
    private ItemOrderDao itemOrderDao;

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private CustomerService customerService;

    public void saveItem(int menuId) {
        OrderItem orderItem = new OrderItem();
        MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();

        Customer customer = customerService.getCustomer(email);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());

        itemOrderDao.save(orderItem);
    }

}
