package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
	
}