package me.seondongpyo.videoshop.customer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
public class Customer {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	@Setter
	private UUID id;

	private String name;

	private String phoneNumber;

	private String address;

	@Column(nullable = false)
	private Long membershipNumber;
}
