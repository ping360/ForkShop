package ua.com.forkShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.forkShop.entity.DigitalUnit;

public interface DigitalUnitRepository extends JpaRepository<DigitalUnit, Integer> , JpaSpecificationExecutor<DigitalUnit>{

	DigitalUnit findByName(String name);

//	@Query("SELECT DISTINCT c FROM DigitalUnit c JOIN Item c WHERE c.digitalUnits.id=?1")
//	@Query("Select DISTINCT d from DigitalUnit d join d.items t join t. t where t.id=?1")
//	@Query("SELECT a FROM DigitalUnit a WHERE items.id=?")
//	List <DigitalUnit> findByItems(int id);
}
