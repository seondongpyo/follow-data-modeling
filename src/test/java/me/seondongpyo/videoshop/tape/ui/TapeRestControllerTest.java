package me.seondongpyo.videoshop.tape.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.tape.application.TapeService;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TapeRestController.class)
class TapeRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private TapeService tapeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mvc;

    private Movie ironMan;
    private Tape betaTape;
    private Tape vhsTape;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();

        ironMan = new Movie();
        ironMan.setId(UUID.randomUUID());
        ironMan.setTitle("아이언 맨");
        ironMan.setGenre(Genre.ACTION);

        betaTape = new Tape();
        betaTape.setId(UUID.randomUUID());
        betaTape.setMovie(ironMan);
        betaTape.setType(TapeType.BETA);

        vhsTape = new Tape();
        vhsTape.setId(UUID.randomUUID());
        vhsTape.setMovie(ironMan);
        vhsTape.setType(TapeType.VHS);

        given(tapeService.create(ironMan.getId(), TapeType.BETA)).willReturn(betaTape);
        given(tapeService.create(ironMan.getId(), TapeType.VHS)).willReturn(vhsTape);
        given(tapeService.findById(betaTape.getId())).willReturn(betaTape);
        given(tapeService.findAll()).willReturn(List.of(betaTape, vhsTape));
    }

    @DisplayName("새로운 테이프를 등록한다.")
    @Test
    void create() throws Exception {
        TapeRequestDTO request = new TapeRequestDTO(ironMan.getId(), TapeType.BETA);

        mvc.perform(post("/tapes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());
    }

    @DisplayName("특정 테이프를 조회한다.")
    @Test
    void findById() throws Exception {
        TapeResponseDTO response = new TapeResponseDTO(betaTape);

        mvc.perform(get("/tapes/{id}", betaTape.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("테이프 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        List<TapeResponseDTO> response = List.of(new TapeResponseDTO(betaTape), new TapeResponseDTO(vhsTape));

        mvc.perform(get("/tapes"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }
}
