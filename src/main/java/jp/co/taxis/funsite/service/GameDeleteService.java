package jp.co.taxis.funsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.repository.GameRepository;

@Transactional
@Service
public class GameDeleteService {
	@Autowired
	private GameRepository gameRepository;

	public GameEntity getGame(Integer id) {
		GameEntity game = gameRepository.findById(id).orElse(null);
		return game;
	}

	public void delete(Integer id) {
		GameEntity target = getGame(id);
		gameRepository.delete(target);
	}

}
