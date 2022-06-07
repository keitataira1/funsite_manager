package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Topic;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicListService {
	
	@Autowired
	private TopicRepository topicRepository;

	public List<Topic> selectAll() {
		List<Topic> topicList = topicRepository.findAll();
		return topicList;
	}

}
