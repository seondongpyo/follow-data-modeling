package me.seondongpyo.videoshop.movie.ui;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.seondongpyo.videoshop.actor.application.ActorService;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

@WebMvcTest(MovieAdminController.class)
class MovieAdminControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MovieService movieService;

	@MockBean
	private ActorService actorService;

	private Movie threeIdiots;
	private Movie theRoundup;

	@BeforeEach
	void setup() {
		threeIdiots = movie("3 Idiots", Genre.COMEDY);
		theRoundup = movie("The Roundup", Genre.ACTION);

		given(movieService.findAll()).willReturn(List.of(threeIdiots, theRoundup));
		given(movieService.findById(threeIdiots.getId())).willReturn(threeIdiots);
		given(movieService.findById(theRoundup.getId())).willReturn(theRoundup);
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
		MultiValueMap<String, String> requests = new LinkedMultiValueMap<>();
		requests.put("actorId", List.of());
		requests.put("isLeadRole", List.of());

		mvc.perform(post("/admin/movies")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "드라마")
				.param("genre", Genre.DRAMA.name())
				.param("actorId", UUID.randomUUID().toString(), UUID.randomUUID().toString())
				.param("isLeadRole", Boolean.TRUE.toString(), Boolean.TRUE.toString()))
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

	@DisplayName("영화 수정 페이지로 이동한다.")
	@Test
	void editPage() throws Exception {
		mvc.perform(get("/admin/movies/{id}/edit", theRoundup.getId()))
			.andExpect(status().isOk())
			.andExpect(view().name("admin/movie/edit"))
			.andExpect(model().attribute("movie", new MovieResponseDTO(theRoundup)));
	}

	@DisplayName("영화 정보를 수정한다.")
	@Test
	void edit() throws Exception {
		mvc.perform(post("/admin/movies/{id}/edit", threeIdiots.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "쥬라기 월드")
				.param("genre", Genre.ACTION.name()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/admin/movies/" + threeIdiots.getId()));
	}

	private Movie movie(String title, Genre genre) {
		Movie movie = new Movie();
		movie.setId(UUID.randomUUID());
		movie.setTitle(title);
		movie.setGenre(genre);
		return movie;
	}

}
