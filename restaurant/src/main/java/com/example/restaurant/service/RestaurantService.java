package com.example.restaurant.service;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.entity.SubMenu;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        Optional<Restaurant> existingRestaurantOpt = restaurantRepository.findById(id);

        if (existingRestaurantOpt.isPresent()) {
            Restaurant existingRestaurant = existingRestaurantOpt.get();

            // Update basic details
            existingRestaurant.setName(updatedRestaurant.getName());
            existingRestaurant.setAddress(updatedRestaurant.getAddress());

            // Update Menus and SubMenus
            if (updatedRestaurant.getMenus() != null) {
                for (Menu menu : updatedRestaurant.getMenus()) {
                    menu.setRestaurant(existingRestaurant);

                    if (menu.getSubMenus() != null) {
                        for (SubMenu subMenu : menu.getSubMenus()) {
                            subMenu.setMenu(menu);
                        }
                    }
                }
                existingRestaurant.setMenus(updatedRestaurant.getMenus());
            }

            // Save the updated restaurant
            return restaurantRepository.save(existingRestaurant);
        } else {
            throw new RuntimeException("Restaurant not found with id: " + id);
        }
    }


    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
    }

    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
