package me.seondongpyo.videoshop.movie.ui;

import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieAdminController.class)
class MovieAdminControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MovieService movieService;

	@DisplayName("영화 목록을 조회한다.")
	@Test
	void findAll() throws Exception {
		List<Movie> movies = List.of(movie("세 얼간이", Genre.COMEDY), movie("범죄도시 2", Genre.ACTION));
		given(movieService.findAll()).willReturn(movies);

		mvc.perform(get("/admin/movies"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("movies", movies))
			.andExpect(view().name("admin/movie/list"));
	}

	@DisplayName("새로운 영화를 등록한다.")
	@Test
	void add() throws Exception {
		String title = "세 얼간이";
		Genre genre = Genre.COMEDY;

		given(movieService.create(any(Movie.class))).willReturn(movie(title, genre));

		mvc.perform(post("/admin/movies")
				.param("title", title)
				.param("genre", genre.name()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/admin/movies"));
	}

	private Movie movie(String title, Genre genre) {
		Movie movie = new Movie();
		movie.setId(UUID.randomUUID());
		movie.setTitle(title);
		movie.setGenre(genre);
		return movie;
	}

}
