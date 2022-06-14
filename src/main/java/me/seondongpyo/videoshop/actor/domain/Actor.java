package me.seondongpyo.videoshop.actor.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Actor extends BaseEntity {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	private UUID id;

	private String stageName;

	private String realName;

	private LocalDate birthDate;
}
