package ua.com.forkShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.NameOfFeatureDigital;

public interface NameOfFeatureDigitalRepository
		extends JpaRepository<NameOfFeatureDigital, Integer>, JpaSpecificationExecutor<NameOfFeatureDigital> {

	@Query("SELECT nofd FROM Category c JOIN c.nameOfFeatureDigitals nofd WHERE c.id=:id")
	List<NameOfFeatureDigital> findByCategoryId(@Param("id") int id);

	NameOfFeatureDigital findByName(String name);

	@Query("SELECT DISTINCT nofd FROM NameOfFeatureDigital nofd LEFT JOIN FETCH nofd.featureDigitals")
	List<NameOfFeatureDigital> findAllLoadedFD();
}
