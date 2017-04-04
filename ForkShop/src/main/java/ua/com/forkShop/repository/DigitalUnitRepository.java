package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.forkShop.entity.DigitalUnit;

public interface DigitalUnitRepository extends JpaRepository<DigitalUnit, Integer> , JpaSpecificationExecutor<DigitalUnit>{

	DigitalUnit findByName(String name);
}
