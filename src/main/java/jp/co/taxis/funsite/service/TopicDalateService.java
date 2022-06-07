package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicDalateService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public Topic getTopic(Integer id) {
		Topic topic = topicRepository.findById(id).orElse(null);
		return topic;
	}
	
	public void delete(Integer id) {
		Topic target = getTopic(id);
		topicRepository.delete(target);
	}

}
