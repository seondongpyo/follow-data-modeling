package me.seondongpyo.videoshop.movie.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieRestController.class)
class MovieRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private MovieService movieService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mvc;

    private Movie guardiansOfGalaxy;
    private Movie avengers;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();

        guardiansOfGalaxy = new Movie();
        guardiansOfGalaxy.setId(UUID.randomUUID());
        guardiansOfGalaxy.setTitle("가디언즈 오브 갤럭시");
        guardiansOfGalaxy.setGenre(Genre.ACTION);

        avengers = new Movie();
        avengers.setId(UUID.randomUUID());
        avengers.setTitle("어벤져스");
        avengers.setGenre(Genre.ACTION);

        given(movieService.create(any(Movie.class))).willReturn(guardiansOfGalaxy);
        given(movieService.findById(guardiansOfGalaxy.getId())).willReturn(guardiansOfGalaxy);
        given(movieService.findAll()).willReturn(List.of(guardiansOfGalaxy, avengers));
    }

    @DisplayName("새로운 영화를 등록한다.")
    @Test
    void create() throws Exception {
        MovieRequestDTO request = new MovieRequestDTO(avengers.getTitle(), avengers.getGenre());

        mvc.perform(MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());
    }

    @DisplayName("특정 영화를 조회한다.")
    @Test
    void findById() throws Exception {
        MovieResponseDTO response = new MovieResponseDTO(guardiansOfGalaxy);

        mvc.perform(MockMvcRequestBuilders.get("/movies/{id}", guardiansOfGalaxy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("영화 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        List<MovieResponseDTO> response = List.of(
            new MovieResponseDTO(guardiansOfGalaxy),
            new MovieResponseDTO(avengers)
        );

        mvc.perform(MockMvcRequestBuilders.get("/movies"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }
}
