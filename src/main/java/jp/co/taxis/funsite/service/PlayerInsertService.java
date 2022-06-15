package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.PlayerRepository;

@Transactional
@Service
public class PlayerInsertService {

	@Autowired
	private PlayerRepository playerRepository;

	public PlayerEntity insert(PlayerEntity player) {
		List<PlayerEntity> searchList = playerRepository.searchSameName(player.getName());
		if (!searchList.isEmpty()) {
			throw new ApplicationException("player.samename.error");
		}
		player.setVersion(1);
		PlayerEntity result = playerRepository.save(player);
		return result;

	}
	
	
	
}
