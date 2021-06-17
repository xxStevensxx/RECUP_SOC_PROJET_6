package com.pay.my.budy.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "friends")
public class Friends {
	
	
	public Friends(){}
	
	
	public Friends(Set<Integer> idFriend) {
		
		this.idFriend = idFriend;
		
	}
	
	
	@Id
	@Column(name = "id")
	private long id;
	
	@ManyToOne(targetEntity = User.class)
//	@Column(name = "id_friend")
	private Set<Integer>idFriend = new HashSet<Integer>();
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Set<Integer> getIdFriend() {
		return idFriend;
	}


	public void setIdFriend(Set<Integer> idFriend) {
		this.idFriend = idFriend;
	}


}
