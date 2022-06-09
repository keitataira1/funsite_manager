package jp.co.taxis.funsite.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.MemberEntity;
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

			memberRepository.save(member);

	}

}
