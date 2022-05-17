package me.seondongpyo.videoshop.tape.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class TapeService {

    private final TapeRepository tapeRepository;

    public Tape create(Tape tape) {
        return tapeRepository.save(tape);
    }

    @Transactional(readOnly = true)
    public Tape findById(UUID id) {
        return tapeRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<Tape> findAll() {
        return tapeRepository.findAll();
    }
}
