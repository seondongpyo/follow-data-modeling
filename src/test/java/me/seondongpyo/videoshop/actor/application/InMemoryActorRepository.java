package me.seondongpyo.videoshop.actor.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import me.seondongpyo.videoshop.actor.domain.Actor;
import me.seondongpyo.videoshop.actor.domain.ActorRepository;

public class InMemoryActorRepository implements ActorRepository {

	private final Map<UUID, Actor> actors = new HashMap<>();

	@Override
	public Actor save(Actor actor) {
		actors.put(actor.getId(), actor);
		return actor;
	}

	@Override
	public Optional<Actor> findById(UUID id) {
		return Optional.ofNullable(actors.get(id));
	}

	@Override
	public List<Actor> findAll() {
		return new ArrayList<>(actors.values());
	}

	@Override
	public List<Actor> findAllByIdIn(List<UUID> ids) {
		return actors.values()
			.stream()
			.filter(actor -> ids.contains(actor.getId()))
			.collect(Collectors.toList());
	}
}
