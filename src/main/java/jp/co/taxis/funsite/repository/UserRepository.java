package jp.co.taxis.funsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.taxis.funsite.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM operation_user WHERE login_id = :loginId")
	public User getUser(@Param("loginId") String loginId);

}
