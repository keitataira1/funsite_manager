package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;

	public Topic insert(Topic topic) {
		Topic result = topicRepository.save(topic);
		return result;
	}

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

	public void delete(Integer id) {
		Topic target = getTopic(id);
		topicRepository.delete(target);
	}

	public List<Topic> selectAll() {
		List<Topic> topicList = topicRepository.findAll();
		return topicList;
	}

}
