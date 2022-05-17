package me.seondongpyo.videoshop.tape.application;

import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeRepository;

import java.util.*;

public class InMemoryTapeRepository implements TapeRepository {

    private final Map<UUID, Tape> tapes = new HashMap<>();

    @Override
    public Tape save(Tape tape) {
        tapes.put(tape.getId(), tape);
        return tape;
    }

    @Override
    public Optional<Tape> findById(UUID id) {
        return Optional.ofNullable(tapes.get(id));
    }

    @Override
    public List<Tape> findAll() {
        return new ArrayList<>(tapes.values());
    }
}
