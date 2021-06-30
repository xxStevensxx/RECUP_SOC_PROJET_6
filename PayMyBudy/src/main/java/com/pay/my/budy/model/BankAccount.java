package com.pay.my.budy.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="bankaccounts")
public class BankAccount {

	@Id
	@Column(name = "idaccount")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idAccount;
	
	@Column(name = "Fk_iduser")
	private int idUser;
	
	@Column(name = "iban")
	private String iban;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "moneyavailable")
	private Double moneyAvailable;
	
	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getMoneyAvailable() {
		return moneyAvailable;
	}

	public void setMoneyAvailable(Double moneyAvailable) {
		this.moneyAvailable = moneyAvailable;
	}

}
