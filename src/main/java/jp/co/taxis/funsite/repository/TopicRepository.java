package jp.co.taxis.funsite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {

	@Query("SELECT t FROM TopicEntity t WHERE player_id = :playerId")
	public List<TopicEntity> selectByPlayerId(@Param("playerId") Integer playerId);

}