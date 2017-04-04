package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfFeatureStrings WHERE " + "c.id=:id")
	Category loadedNofs(@Param("id") int id);

	@Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.nameOfFeatureDigitals WHERE " + "c.id=:id")
	Category loadedNofd(@Param("id") int id);

	Category findByName(String name);
}
