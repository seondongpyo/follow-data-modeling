package me.seondongpyo.videoshop.movie.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	private UUID id;

	private String title;

	@Enumerated(EnumType.STRING)
	private Genre genre;
}
