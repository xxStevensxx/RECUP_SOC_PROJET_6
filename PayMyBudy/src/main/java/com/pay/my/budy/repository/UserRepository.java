package com.pay.my.budy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pay.my.budy.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	

	@Query(value = "SELECT * FROM Users  WHERE firstname = ?1", nativeQuery = true)
	Optional<User> findUserWithName(String userName);


}
