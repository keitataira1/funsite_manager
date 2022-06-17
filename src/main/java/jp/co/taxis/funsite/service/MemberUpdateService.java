package jp.co.taxis.funsite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.exception.ApplicationException;
import jp.co.taxis.funsite.repository.MemberRepository;

@Transactional
@Service
public class MemberUpdateService {

	@Autowired
	private MemberRepository memberRepository;

	public MemberEntity getMember(Integer id) {
		MemberEntity member = memberRepository.findById(id).orElse(null);
		return member;
	}

	public void update(MemberEntity member) {
		
		List<MemberEntity> searchSameList = memberRepository.searchSameNameId(member.getMailAddress(),member.getDisplayName(),member.getId());
		if (!searchSameList.isEmpty()) {
			throw new ApplicationException("member.samename.error");
		}
		try {
			memberRepository.save(member);
		} catch (OptimisticLockingFailureException e) {
			throw new ApplicationException("optimistic.locking.error");
		}
		
	}
}
