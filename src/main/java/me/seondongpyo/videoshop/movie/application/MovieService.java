package me.seondongpyo.videoshop.movie.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.actor.domain.Actor;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;
import me.seondongpyo.videoshop.actor.domain.StarringActor;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;
import me.seondongpyo.videoshop.movie.ui.MovieRequestDTO;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {

	private final MovieRepository movieRepository;
	private final ActorRepository actorRepository;

	public Movie create(Movie movie) {
		List<StarringActor> starringActors = movie.getStarringActors();
		List<UUID> actorIds = starringActors
			.stream()
			.map(StarringActor::getActorId)
			.collect(Collectors.toList());

		List<Actor> actors = actorRepository.findAllByIdIn(actorIds);
		if (actorIds.size() != actors.size()) {
			throw new IllegalArgumentException();
		}

		movie.setId(UUID.randomUUID());

		for (int i = 0; i < starringActors.size(); i++) {
			StarringActor starringActor = starringActors.get(i);
			starringActor.setMovie(movie);
			starringActor.setActor(actors.get(i));
		}

		return movieRepository.save(movie);
	}

	@Transactional(readOnly = true)
	public Movie findById(UUID id) {
		return movieRepository.findById(id)
			.orElseThrow(IllegalAccessError::new);
	}

	@Transactional(readOnly = true)
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	public void update(UUID id, MovieRequestDTO updateParam) {
		Movie movie = movieRepository.findById(id)
			.orElseThrow(IllegalAccessError::new);

		movie.setTitle(updateParam.getTitle());
		movie.setGenre(updateParam.getGenre());
	}
}
