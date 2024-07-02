package com.example.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.entity.SubMenu;

public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {
}
