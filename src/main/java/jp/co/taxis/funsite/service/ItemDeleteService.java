package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.repository.ItemRepository;

@Transactional
@Service
public class ItemDeleteService {
	@Autowired
	private ItemRepository itemRepository;

	public ItemEntity getItem(Integer id) {
		ItemEntity item = itemRepository.findById(id).orElse(null);
		return item;
	}

	public void delete(Integer id) {
		ItemEntity target = getItem(id);
		itemRepository.delete(target);
	}

}
