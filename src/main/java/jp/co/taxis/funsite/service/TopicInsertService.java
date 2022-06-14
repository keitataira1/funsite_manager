package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicInsertService {

	@Autowired
	private TopicRepository topicRepository;

	public TopicEntity insert(TopicEntity topic) {
		topic.setInvalidFlg(false);
		topic.setVersion(1);
		TopicEntity result = topicRepository.save(topic);
		return result;
	}

}
