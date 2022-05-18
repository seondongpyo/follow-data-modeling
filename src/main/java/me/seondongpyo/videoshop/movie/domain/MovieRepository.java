package me.seondongpyo.videoshop.movie.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository {

	Movie save(Movie movie);

	Optional<Movie> findById(UUID id);

	List<Movie> findAll();
}
