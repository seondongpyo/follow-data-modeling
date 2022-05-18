package me.seondongpyo.videoshop.movie.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;

class MovieServiceTest {

	private MovieRepository movieRepository;
	private MovieService movieService;

	@BeforeEach
	void setup() {
		movieRepository = new InMemoryMovieRepository();
		movieService = new MovieService(movieRepository);
	}

	@DisplayName("영화를 등록한다.")
	@Test
	void create() {
		Movie movie = movie("3 idiots", Genre.COMEDY);
		Movie created = movieService.create(movie);
		assertThat(created.getId()).isEqualTo(movie.getId());
	}

	@DisplayName("특정 영화를 조회한다.")
	@Test
	void findById() {
		Movie movie = movie("Avengers", Genre.ACTION);
		movieRepository.save(movie);
		Movie found = movieService.findById(movie.getId());
		assertThat(found.getTitle()).isEqualTo(movie.getTitle());
		assertThat(found.getGenre()).isEqualTo(movie.getGenre());
	}

	@DisplayName("영화 목록을 조회한다.")
	@Test
	void findAll() {
		movieRepository.save(movie("Spider Man", Genre.ACTION));
		movieRepository.save(movie("The Last Dance", Genre.DOCUMENTARY));
		List<Movie> movies = movieService.findAll();
		assertThat(movies).hasSize(2);
	}

	private Movie movie(String title, Genre genre) {
		Movie movie = new Movie();
		movie.setId(UUID.randomUUID());
		movie.setTitle(title);
		movie.setGenre(genre);
		return movie;
	}
}
