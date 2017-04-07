package ua.com.forkShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.FeatureString;

public interface FeatureStringRepository extends JpaRepository<FeatureString, Integer>, JpaSpecificationExecutor<FeatureString> {

	FeatureString findByName(String name);

}
