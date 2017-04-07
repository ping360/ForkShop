package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.forkShop.entity.Brand;
import ua.com.forkShop.entity.Category;

public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {

	Brand findByName(String name);
	
	@Query("SELECT i FROM Brand i LEFT JOIN FETCH i.items WHERE i.id = ?1")
	Brand findOneByItem(int id);
}
