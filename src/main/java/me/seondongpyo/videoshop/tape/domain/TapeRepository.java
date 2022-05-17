package me.seondongpyo.videoshop.tape.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TapeRepository {

    Tape save(Tape tape);

    Optional<Tape> findById(UUID id);

    List<Tape> findAll();
}
