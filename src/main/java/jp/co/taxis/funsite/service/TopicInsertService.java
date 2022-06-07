package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicInsertService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public Topic insert(Topic topic) {
		Topic result = topicRepository.save(topic);
		return result;
	}


}
