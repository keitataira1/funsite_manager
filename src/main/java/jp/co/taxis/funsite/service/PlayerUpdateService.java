package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.repository.PlayerRepository;

@Transactional
@Service
public class PlayerUpdateService {

	@Autowired
	private PlayerRepository playerRepository;

	public PlayerEntity getPlayer(Integer id) {
		PlayerEntity player = playerRepository.findById(id).orElse(null);
		return player;
	}

	public void update(PlayerEntity player) {

		playerRepository.save(player);

	}
}
