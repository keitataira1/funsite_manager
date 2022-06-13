package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicListService {

	@Autowired
	private TopicRepository topicRepository;

	public List<TopicEntity> selectByPlayerId(Integer playerId) {
		List<TopicEntity> topicList = topicRepository.selectByPlayerId(playerId);
		return topicList;
	}

}
