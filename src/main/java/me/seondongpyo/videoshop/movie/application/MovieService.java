package me.seondongpyo.videoshop.movie.application;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {

	private final MovieRepository movieRepository;

	public Movie create(Movie movie) {
		return movieRepository.save(movie);
	}

	public Movie findById(UUID id) {
		return movieRepository.findById(id)
			.orElseThrow(IllegalAccessError::new);
	}

	public List<Movie> findAll() {
		return movieRepository.findAll();
	}
}
