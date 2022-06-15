package me.seondongpyo.videoshop.movie.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.actor.domain.Actor;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;
import me.seondongpyo.videoshop.actor.domain.StarringActor;
import me.seondongpyo.videoshop.actor.ui.StarringActorRequestDTO;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;
import me.seondongpyo.videoshop.movie.ui.MovieRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
			starringActor.setId(UUID.randomUUID());
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

		List<StarringActorRequestDTO> starringActorRequests = updateParam.getStarringActors();
		if (starringActorRequests != null) {
			List<UUID> actorIds = starringActorRequests
				.stream()
				.map(StarringActorRequestDTO::getActorId)
				.collect(Collectors.toList());

			List<Actor> actors = actorRepository.findAllByIdIn(actorIds);
			if (actorIds.size() != actors.size()) {
				throw new IllegalArgumentException();
			}

			List<StarringActor> starringActors = movie.getStarringActors();
			for (int i = 0; i < starringActors.size(); i++) {
				StarringActor starringActor = starringActors.get(i);
				starringActor.setActor(actors.get(i));
				starringActor.setLeadRole(starringActorRequests.get(i).isLeadRole());
			}
		}
	}
}
