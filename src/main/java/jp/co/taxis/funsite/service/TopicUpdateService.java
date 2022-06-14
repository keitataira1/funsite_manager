package jp.co.taxis.funsite.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicUpdateService {
	
	@Autowired
	private TopicRepository topicRepository;
	
	/**
	 * getTopicメソッド
	 * 
	 * @param id
	 * @return
	 */
	public TopicEntity getTopic(Integer id) {
		TopicEntity topic = topicRepository.findById(id).orElse(null);
		return topic;
	}
	
	/**
	 * updateメソッド
	 * 
	 * @param topic
	 */
	public void update(TopicEntity topic) {

		try {
			topicRepository.save(topic);
		} catch (OptimisticLockingFailureException e) {
			throw new ApplicationException("optimistic.locking.error");
		}
	}

}
