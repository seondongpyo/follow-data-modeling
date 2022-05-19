package me.seondongpyo.videoshop.actor.application;

import java.util.List;
import java.util.UUID;

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
}
