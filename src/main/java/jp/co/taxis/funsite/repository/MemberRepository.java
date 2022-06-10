package jp.co.taxis.funsite.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	@Query(nativeQuery = true, value = "SELECT * FROM member WHERE name and display_name LIKE :memberName")
	public List<MemberEntity> selectLikeName(@Param("memberName") String memberName);
}
