package me.seondongpyo.videoshop.actor.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActorRepository {

	Actor save(Actor actor);

	Optional<Actor> findById(UUID id);

	List<Actor> findAll();

	List<Actor> findAllByIdIn(List<UUID> ids);
}
