package com.onlinefoodordering.onlineOrder.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinefoodordering.onlineOrder.entity.MenuItem;
import com.onlinefoodordering.onlineOrder.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants() {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Restaurant> criteriaQuery = criteriaBuilder.createQuery(Restaurant.class);
            Root<Restaurant> root = criteriaQuery.from(Restaurant.class);

            criteriaQuery.select(root).distinct(true);

            return session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }


    public List<MenuItem> getMenuItems(int restaurantId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if(restaurant != null) {
                return  restaurant.getMenuItemList();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }


    public MenuItem getMenuItem(int menuItemId) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            MenuItem menuItem = session.get(MenuItem.class, menuItemId);
            return menuItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return null;
    }

}
