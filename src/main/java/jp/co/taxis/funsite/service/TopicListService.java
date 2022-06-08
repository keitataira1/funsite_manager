package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.TopicEntity;
import jp.co.taxis.funsite.repository.TopicRepository;

@Transactional
@Service
public class TopicListService {
	
	@Autowired
	private TopicRepository topicRepository;

	public List<TopicEntity> selectAll() {
		List<TopicEntity> topicList = topicRepository.findAll();
		return topicList;
	}

}
