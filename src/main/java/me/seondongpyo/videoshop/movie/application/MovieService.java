package me.seondongpyo.videoshop.movie.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;
import me.seondongpyo.videoshop.movie.ui.MovieRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class MovieService {

	private final MovieRepository movieRepository;

	public Movie create(Movie movie) {
		movie.setId(UUID.randomUUID());
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
