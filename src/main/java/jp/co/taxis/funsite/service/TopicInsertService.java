package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicInsertService {

	@Autowired
	private TopicRepository topicRepository;

	public TopicEntity insert(TopicEntity topic) {
		TopicEntity result = topicRepository.save(topic);
		return result;
	}

	public TopicEntity getTopic(Integer id) {
		TopicEntity topic = topicRepository.findById(id).orElse(null);
		return topic;
	}

}
