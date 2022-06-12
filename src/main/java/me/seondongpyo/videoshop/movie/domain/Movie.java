package me.seondongpyo.videoshop.movie.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.actor.domain.StarringActor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<StarringActor> starringActors = new ArrayList<>();
}
