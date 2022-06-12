package me.seondongpyo.videoshop.movie.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import me.seondongpyo.videoshop.actor.application.InMemoryActorRepository;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;
import me.seondongpyo.videoshop.movie.ui.MovieRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;

class MovieServiceTest {

	private MovieRepository movieRepository;
	private ActorRepository actorRepository;
	private MovieService movieService;

	@BeforeEach
	void setup() {
		movieRepository = new InMemoryMovieRepository();
		actorRepository = new InMemoryActorRepository();
		movieService = new MovieService(movieRepository, actorRepository);
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

	@DisplayName("영화 정보를 수정한다.")
	@Test
	void update() {
		Movie avengers = movieRepository.save(movie("The Avengers", Genre.ACTION));
		movieService.update(avengers.getId(), new MovieRequestDTO("3 idiots", Genre.COMEDY, null));

		Movie found = movieService.findById(avengers.getId());
		assertThat(found.getTitle()).isEqualTo("3 idiots");
		assertThat(found.getGenre()).isEqualTo(Genre.COMEDY);
	}

	private Movie movie(String title, Genre genre) {
		Movie movie = new Movie();
		movie.setId(UUID.randomUUID());
		movie.setTitle(title);
		movie.setGenre(genre);
		return movie;
	}
}
