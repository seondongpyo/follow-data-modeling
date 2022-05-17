package me.seondongpyo.videoshop.customer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter
	private Long id;

	private String name;

	private String phoneNumber;

	private String address;

	private Long membershipNumber;
}
