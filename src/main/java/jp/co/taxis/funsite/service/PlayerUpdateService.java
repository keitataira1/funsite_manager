package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.repository.PlayerRepository;

@Transactional
@Service
public class PlayerUpdateService {

	@Autowired
	private PlayerRepository playerRepository;

	public Player getPlayer(Integer id) {
		Player player = playerRepository.findById(id).orElse(null);
		return player;
	}

	public void update(Player player) {

		playerRepository.save(player);

	}
}
