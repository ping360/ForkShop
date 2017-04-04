package ua.com.forkShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.com.forkShop.entity.NameOfFeatureString;

public interface NameOfFeatureStringRepository
		extends JpaRepository<NameOfFeatureString, Integer>, JpaSpecificationExecutor<NameOfFeatureString> {

	@Query("SELECT DISTINCT nofs FROM Category c JOIN c.nameOfFeatureStrings nofs LEFT JOIN FETCH nofs.featureStrings WHERE c.id=:id")
	List<NameOfFeatureString> findByCategoryId(@Param("id") int id);

	NameOfFeatureString findByName(String name);

	@Query("SELECT DISTINCT nofs FROM NameOfFeatureString nofs LEFT JOIN FETCH nofs.featureStrings")
	List<NameOfFeatureString> findAllLoadedFS();
}
