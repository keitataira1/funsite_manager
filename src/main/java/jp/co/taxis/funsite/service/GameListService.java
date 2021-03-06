package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.GameEntity;
import jp.co.taxis.funsite.repository.GameRepository;

@Transactional
@Service
public class GameListService {

	@Autowired
	private GameRepository gameRepository;

	public List<GameEntity> selectAll() {
		List<GameEntity> gameList = gameRepository.findAll();
		return gameList;
	}

	public List<GameEntity> selectLikeMatchTeam(String searchMatchTeam) {
		List<GameEntity> searchList = gameRepository.searchMatchTeam("%" + searchMatchTeam + "%");
		return searchList;
	}

}
