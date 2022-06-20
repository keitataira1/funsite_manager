package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.repository.ItemRepository;

@Transactional
@Service
public class ItemInsertService {
	
	@Autowired
	private ItemRepository itemRepository;

	public ItemEntity insert(ItemEntity item) {
		ItemEntity result = itemRepository.save(item);
		return result;

	}

}
