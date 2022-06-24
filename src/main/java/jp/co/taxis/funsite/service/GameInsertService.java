package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.repository.GameRepository;

@Transactional
@Service
public class GameInsertService {

	@Autowired
	private GameRepository gameRepository;

	public GameEntity insert(GameEntity game) {
		GameEntity result = gameRepository.save(game);
		return result;

	}
	
	public List<GameEntity> getGameAll() {
	        return gameRepository.findAll();
	}
	
	public GameEntity getGame(Integer id) {
		GameEntity game = gameRepository.findById(id).orElse(null);
		return game;
	}


}
