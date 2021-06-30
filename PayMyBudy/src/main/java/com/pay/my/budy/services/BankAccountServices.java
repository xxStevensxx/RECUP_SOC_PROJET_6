package com.pay.my.budy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.my.budy.model.BankAccount;
import com.pay.my.budy.repository.BankAccountRepository;

@Service
public class BankAccountServices {

	@Autowired
	BankAccountRepository bankAccountRepository;

	public Iterable<BankAccount> getBankAccounts() {

		return bankAccountRepository.findAll();

	}

	public Optional<BankAccount> getBankAccountById(Integer id) {

		return bankAccountRepository.findById(id);

	}

}
