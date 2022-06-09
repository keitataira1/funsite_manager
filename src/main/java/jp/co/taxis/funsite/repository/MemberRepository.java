package jp.co.taxis.funsite.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}
