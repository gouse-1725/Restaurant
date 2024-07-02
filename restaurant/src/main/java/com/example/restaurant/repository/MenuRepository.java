package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
