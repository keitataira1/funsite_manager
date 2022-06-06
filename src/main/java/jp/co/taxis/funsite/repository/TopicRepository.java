package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.entity.User;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM operation_user WHERE player_id = :player")
	public User getTopic(@Param("player") Player player);

}
