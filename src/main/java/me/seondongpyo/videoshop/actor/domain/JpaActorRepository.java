package me.seondongpyo.videoshop.actor.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaActorRepository extends ActorRepository, JpaRepository<Actor, UUID> {
}
