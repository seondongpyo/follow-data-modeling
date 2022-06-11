package me.seondongpyo.videoshop.tape.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeRepository;
import me.seondongpyo.videoshop.tape.domain.TapeType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class TapeService {

    private final TapeRepository tapeRepository;
    private final MovieRepository movieRepository;

    public Tape create(UUID movieId, TapeType type) {
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(IllegalArgumentException::new);

        Tape tape = new Tape();
        tape.setId(UUID.randomUUID());
        tape.setMovie(movie);
        tape.setType(type);

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
