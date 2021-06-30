package com.pay.my.budy.repository;

import java.util.ArrayList;

import com.pay.my.budy.model.User;

public interface UserRepository {

<<<<<<< Updated upstream
	void create(User user);

	User getById(int id);

	User getByName(String name);
	
	User getByEmail(String email);
	
	ArrayList<User> getAll();
	
	void save(User user);

	void delete(User user);
=======
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	

	@Query(value = "SELECT * FROM Users  WHERE firstname = ?1", nativeQuery = true)
	Optional<User> findUserWithName(String userName);
	
<<<<<<< Updated upstream
=======
	
	//La suite a venir ...  
>>>>>>> Stashed changes
>>>>>>> Stashed changes

}
