package me.seondongpyo.videoshop.movie.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MovieAdminControllerTest {

	@Autowired
	private MockMvc mvc;

	@DisplayName("영화 목록을 조회한다.")
	@Test
	void findAll() throws Exception {
		mvc.perform(get("/admin/movies"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("movies"))
			.andExpect(view().name("admin/movie/list"));
	}

}
