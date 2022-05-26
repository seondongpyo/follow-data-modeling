package me.seondongpyo.videoshop.customer.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.tape.domain.Tape;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Customer {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tape tape;

	private String name;

	private String phoneNumber;

	private String address;

	@Column(nullable = false)
	private UUID membershipId;
}
