package jp.co.taxis.funsite.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.Player;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.PlayerRepository;

@Transactional
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public Player insert(Player player) {
		Player result = playerRepository.save(player);
		return result;
	}

	public Player getPlayer(Integer id) {
		Player player = playerRepository.findById(id).orElse(null);
		return player;
	}

	public void update(Player player) {

		try {
			playerRepository.save(player);
		} catch (OptimisticLockingFailureException e) {
			throw new ApplicationException("optimistic.locking.error");
		}
	}

	public void delete(Integer id) {
		Player target = getPlayer(id);
		playerRepository.delete(target);
	}

	public List<Player> selectAll() {
		List<Player> playerList = playerRepository.findAll();
		return playerList;
	}

}