package me.seondongpyo.videoshop.actor.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.seondongpyo.videoshop.actor.application.ActorService;
import me.seondongpyo.videoshop.actor.domain.Actor;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActorRestController.class)
class ActorRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private ActorService actorService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private Actor actorA;
    private Actor actorB;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();

        actorA = actor("aa", "AA");
        actorB = actor("bb", "BB");

        given(actorService.create(any(Actor.class))).willReturn(actorA);
        given(actorService.findById(actorA.getId())).willReturn(actorA);
        given(actorService.findById(actorB.getId())).willReturn(actorB);
        given(actorService.findAll()).willReturn(List.of(actorA, actorB));
    }

    @DisplayName("새로운 배우를 등록한다.")
    @Test
    void create() throws Exception {
        ActorRequestDTO request = new ActorRequestDTO(actorA.getStageName(), actorA.getRealName(), actorA.getBirthDate());

        mvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.realName").value(actorA.getRealName()))
            .andExpect(jsonPath("$.stageName").value(actorA.getStageName()));
    }

    @DisplayName("특정 배우를 조회한다.")
    @Test
    void findById() throws Exception {
        ActorResponseDTO response = new ActorResponseDTO(actorA);

        mvc.perform(get("/actors/{id}", actorA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @DisplayName("배우 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        List<ActorResponseDTO> response = List.of(new ActorResponseDTO(actorA), new ActorResponseDTO(actorB));

        mvc.perform(get("/actors"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    private Actor actor(String realName, String stageName) {
        Actor actor = new Actor();
        actor.setId(UUID.randomUUID());
        actor.setRealName(realName);
        actor.setStageName(stageName);
        actor.setBirthDate(LocalDate.now());
        return actor;
    }
}