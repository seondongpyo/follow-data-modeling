package me.seondongpyo.videoshop.movie.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;

public class InMemoryMovieRepository implements MovieRepository {

	private final Map<UUID, Movie> movies = new HashMap<>();

	@Override
	public Movie save(Movie movie) {
		movies.put(movie.getId(), movie);
		return movie;
	}

	@Override
	public Optional<Movie> findById(UUID id) {
		return Optional.ofNullable(movies.get(id));
	}

	@Override
	public List<Movie> findAll() {
		return new ArrayList<>(movies.values());
	}
}
