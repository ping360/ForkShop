package ua.com.forkShop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.forkShop.dto.filter.ItemFilter;
import ua.com.forkShop.dto.form.ItemForm;
import ua.com.forkShop.entity.Item;

public interface ItemService {

	List<Item> findAll();

	void delete(int id);
	
	void save(ItemForm item);

	List<Item> findByCategoryId(int id);
	
	Page<Item> findAll(ItemFilter filter, Pageable pageable);
	
	int findCount(int id);

	List<Item> findByUserId(int userId);
}
