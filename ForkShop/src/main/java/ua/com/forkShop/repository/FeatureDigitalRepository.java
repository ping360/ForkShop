package ua.com.forkShop.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.FeatureDigital;

public interface FeatureDigitalRepository extends JpaRepository<FeatureDigital, Integer> {

	@Query("SELECT fd FROM FeatureDigital fd " + "LEFT JOIN FETCH fd.digitalUnits WHERE "
			+ "fd.nameOfFeatureDigital.id=:nofdId " + "AND fd.value=:value")
	FeatureDigital findByNofdValue(@Param("nofdId") Integer nofdId, @Param("value") BigDecimal value);
}
