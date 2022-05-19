package me.seondongpyo.videoshop.actor.application;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.seondongpyo.videoshop.actor.domain.Actor;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;

class ActorServiceTest {

	private ActorRepository actorRepository;
	private ActorService actorService;

	@BeforeEach
	void setup() {
		actorRepository = new InMemoryActorRepository();
		actorService = new ActorService(actorRepository);
	}

	@DisplayName("배우를 등록한다.")
	@Test
	void create() {
		Actor actor = actor("Hyunbin", "Kim Taepyeong", LocalDate.of(1982, 9, 25));
		Actor created = actorService.create(actor);
		assertThat(created.getId()).isEqualTo(actor.getId());
	}

	@DisplayName("특정 배우를 조회한다.")
	@Test
	void findById() {
		Actor actor = actor("Lee DongSeok", "Don Lee", LocalDate.of(1971, 3, 1));
		actorRepository.save(actor);
		Actor found = actorService.findById(actor.getId());
		assertThat(found.getId()).isEqualTo(actor.getId());
	}

	@DisplayName("배우 목록을 조회한다.")
	@Test
	void findAll() {
		actorRepository.save(actor("Jang Hyeok", "Jeong Yongjun", LocalDate.of(1976, 12, 20)));
		actorRepository.save(actor("Ha Jeongwoo", "Kim Seonghun", LocalDate.of(1978, 3, 11)));
		List<Actor> actors = actorService.findAll();
		assertThat(actors).hasSize(2);
	}

	private Actor actor(String stageName, String realName, LocalDate birthDate) {
		Actor actor = new Actor();
		actor.setId(UUID.randomUUID());
		actor.setStageName(stageName);
		actor.setRealName(realName);
		actor.setBirthDate(birthDate);
		return actor;
	}
}
