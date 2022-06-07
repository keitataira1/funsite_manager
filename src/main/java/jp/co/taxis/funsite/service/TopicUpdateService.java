package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicUpdateService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	public Topic getTopic(Integer id) {
		Topic topic = topicRepository.findById(id).orElse(null);
		return topic;
	}
	
	public void update(Topic topic) {

		try {
			topicRepository.save(topic);
		} catch (OptimisticLockingFailureException e) {
			throw new ApplicationException("optimistic.locking.error");
		}
	}

}
