package jp.co.taxis.funsite.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	@Query("SELECT m FROM MemberEntity m WHERE m.name LIKE :searchName OR m.displayName LIKE :searchName")
	public List<MemberEntity> selectLikeName(@Param("searchName") String searchName);
	
	@Query("SELECT m FROM MemberEntity m WHERE (m.mailAddress = :searchMailName AND m.id != :searchId) OR (m.displayName = :searchDisplayName AND m.id != :searchId)")
	public List<MemberEntity> searchSameNameId(@Param("searchMailName") String searchMailName,@Param("searchDisplayName") String searchDisplayName, @Param("searchId")Integer searchId);
}
