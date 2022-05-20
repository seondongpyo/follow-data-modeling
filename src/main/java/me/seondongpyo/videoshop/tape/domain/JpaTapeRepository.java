package me.seondongpyo.videoshop.tape.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTapeRepository extends TapeRepository, JpaRepository<Tape, UUID> {
}
