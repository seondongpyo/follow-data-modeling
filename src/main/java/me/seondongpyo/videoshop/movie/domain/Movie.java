package me.seondongpyo.videoshop.movie.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.tape.domain.Tape;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Movie {

	@Id
	@Column(name = "id", columnDefinition = "varbinary(16)")
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Tape tape;

	private String title;

	@Enumerated(EnumType.STRING)
	private Genre genre;
}
