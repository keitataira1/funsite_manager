package jp.co.taxis.funsite.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.taxis.funsite.entity.MemberEntity;
import jp.co.taxis.funsite.repository.MemberRepository;

@Transactional
@Service
public class MemberListService {


		@Autowired
		private MemberRepository memberRepository;

		public List<MemberEntity> selectAll() {
			List<MemberEntity> memberList = memberRepository.findAll();
			return memberList;
		}
	}
