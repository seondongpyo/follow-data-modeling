package me.seondongpyo.videoshop.actor.application;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.actor.domain.Actor;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class ActorService {

	private final ActorRepository actorRepository;

	public Actor create(Actor actor) {
		return actorRepository.save(actor);
	}

	@Transactional(readOnly = true)
	public Actor findById(UUID id) {
		return actorRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
	}

	@Transactional(readOnly = true)
	public List<Actor> findAll() {
		return actorRepository.findAll();
	}

	@PostConstruct
	private void init() {
		Actor actor = new Actor();
		actor.setId(UUID.randomUUID());
		actor.setRealName("Don Lee");
		actor.setStageName("마동석");
		actor.setBirthDate(LocalDate.of(1971, 3, 1));
		actorRepository.save(actor);

		actor = new Actor();
		actor.setId(UUID.randomUUID());
		actor.setRealName("윤계상");
		actor.setStageName("윤계상");
		actor.setBirthDate(LocalDate.of(1978, 12, 20));
		actorRepository.save(actor);
	}
}
