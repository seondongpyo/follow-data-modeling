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

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac)
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
    }

    @DisplayName("새로운 배우를 등록한다.")
    @Test
    void create() throws Exception {
        String realName = "Don Lee";
        String stageName = "마동석";

        given(actorService.create(any(Actor.class))).willReturn(actor(realName, stageName));

        mvc.perform(post("/actors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"realName\":\"" + realName + "\", \"stageName\":\"" + stageName + "\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.realName").value(realName))
            .andExpect(jsonPath("$.stageName").value(stageName));
    }

    @DisplayName("특정 배우를 조회한다.")
    @Test
    void findById() throws Exception {
        Actor actor = actor("Don Lee", "마동석");

        given(actorService.findById(any(UUID.class))).willReturn(actor);

        mvc.perform(get("/actors/{id}", actor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(actor)));
    }

    @DisplayName("배우 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        Actor actorA = actor("AA", "aa");
        Actor actorB = actor("BB", "bb");

        given(actorService.findAll()).willReturn(List.of(actorA, actorB));

        mvc.perform(get("/actors"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(List.of(actorA, actorB))));
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