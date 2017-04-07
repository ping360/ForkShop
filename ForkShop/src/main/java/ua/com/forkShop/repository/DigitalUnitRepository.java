package ua.com.forkShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.forkShop.entity.DigitalUnit;

public interface DigitalUnitRepository extends JpaRepository<DigitalUnit, Integer> , JpaSpecificationExecutor<DigitalUnit>{

	DigitalUnit findByName(String name);

//	@Query("SELECT a FROM DigitalUnit a WHERE a.items.id=?1")
	List<DigitalUnit> findAllByItems(int id);
}
