package jp.co.taxis.funsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

	@Query("SELECT g FROM GameEntity g WHERE g.match_team LIKE :searchMatchTeam")
	public List<GameEntity> searchGameMatchTeam(@Param("searchMatchTeam") String searchMatchTeam);
}
