package jp.co.taxis.funsite.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.PlayerEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
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

		try {
			playerRepository.save(player);
		} catch (OptimisticLockingFailureException e) {
			throw new ApplicationException("optimistic.locking.error");
		}

	}
}
