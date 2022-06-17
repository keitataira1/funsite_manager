package jp.co.taxis.funsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer> {
	@Query("SELECT p FROM PlayerEntity p WHERE p.name LIKE :searchName")
	public List<PlayerEntity> searchSameName(@Param("searchName") String searchName);
	@Query("SELECT p FROM PlayerEntity p WHERE p.name LIKE :searchName AND p.id != :searchId")
	public List<PlayerEntity> searchSameNameId(@Param("searchName") String searchName, @Param("searchId")Integer searchId);

}