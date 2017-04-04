package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.FeatureString;

public interface FeatureStringRepository extends JpaRepository<FeatureString, Integer>, JpaSpecificationExecutor<FeatureString> {

	@Query("SELECT fs FROM FeatureString fs LEFT JOIN FETCH fs.nameOfFeatureString WHERE fs.id=:id")
	FeatureString findOne(@Param("id")int id);
	
	FeatureString findByName(String name);

}
