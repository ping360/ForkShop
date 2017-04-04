package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.forkShop.entity.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer> {

}
