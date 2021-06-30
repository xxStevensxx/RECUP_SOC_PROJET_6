package com.pay.my.budy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pay.my.budy.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

	@Query(value = "SELECT * FROM bankaccounts  WHERE id = ?1", nativeQuery = true)
	Optional<BankAccount> findBankAccountWithId(int id);

	// La suite a venir ...

}
