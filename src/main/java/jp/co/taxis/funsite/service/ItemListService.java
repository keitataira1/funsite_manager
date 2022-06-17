package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.ItemEntity;
import jp.co.taxis.funsite.repository.ItemRepository;

@Transactional
@Service
public class ItemListService {

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemEntity> selectAll() {
		List<ItemEntity> ItemList = itemRepository.findAll();
		return ItemList;
	}
	
	public List<ItemEntity> selectLikeItem(String searchItem) {
		List<ItemEntity> searchList = itemRepository.searchItem("%"+searchItem+"%");
		return searchList;
	}
	
}
