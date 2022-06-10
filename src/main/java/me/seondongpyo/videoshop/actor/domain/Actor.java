package me.seondongpyo.videoshop.actor.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
public class Actor {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	private UUID id;

	private String stageName;

	private String realName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
}
