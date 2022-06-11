package me.seondongpyo.videoshop.movie.ui;

import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
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

	private Movie threeIdiots;
	private Movie theRoundup;

	@BeforeEach
	void setup() {
		threeIdiots = movie("3 Idiots", Genre.COMEDY);
		theRoundup = movie("The Roundup", Genre.ACTION);

		given(movieService.findAll()).willReturn(List.of(threeIdiots, theRoundup));
		given(movieService.findById(threeIdiots.getId())).willReturn(threeIdiots);
	}

	@DisplayName("영화 목록을 조회한다.")
	@Test
	void findAll() throws Exception {
		mvc.perform(get("/admin/movies"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("movies", hasSize(2)))
			.andExpect(view().name("admin/movie/list"));
	}

	@DisplayName("새로운 영화를 등록한다.")
	@Test
	void add() throws Exception {
		mvc.perform(post("/admin/movies")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "드라마")
				.param("genre", Genre.DRAMA.name()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/admin/movies"));
	}

	@DisplayName("영화 상세 페이지로 이동한다.")
	@Test
	void detail() throws Exception {
		mvc.perform(get("/admin/movies/{id}", threeIdiots.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("admin/movie/detail"))
			.andExpect(model().attribute("movie", new MovieResponseDTO(threeIdiots)));
	}

	@DisplayName("영화 등록 페이지로 이동한다.")
	@Test
	void addPage() throws Exception {
		mvc.perform(get("/admin/movies/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("admin/movie/new"));
	}

	private Movie movie(String title, Genre genre) {
		Movie movie = new Movie();
		movie.setId(UUID.randomUUID());
		movie.setTitle(title);
		movie.setGenre(genre);
		return movie;
	}

}
