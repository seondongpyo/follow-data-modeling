package me.seondongpyo.videoshop.tape.application;

import me.seondongpyo.videoshop.movie.application.InMemoryMovieRepository;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.movie.domain.MovieRepository;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeRepository;
import me.seondongpyo.videoshop.tape.domain.TapeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TapeServiceTest {

    private TapeRepository tapeRepository;
    private MovieRepository movieRepository;
    private TapeService tapeService;

    @BeforeEach
    void setup() {
        tapeRepository = new InMemoryTapeRepository();
        movieRepository = new InMemoryMovieRepository();
        tapeService = new TapeService(tapeRepository, movieRepository);
    }

    @DisplayName("새로운 테이프를 등록한다.")
    @Test
    void create() {
        Movie ironMan = movie("아이언 맨", Genre.ACTION);
        movieRepository.save(ironMan);
        Tape tape = tapeService.create(ironMan.getId(), TapeType.BETA);
        assertThat(tape.getType()).isEqualTo(TapeType.BETA);
    }

    @DisplayName("특정 테이프를 조회한다.")
    @Test
    void findById() {
        Tape betaTape = betaTape();
        tapeRepository.save(betaTape);
        assertThat(tapeService.findById(betaTape.getId())).isNotNull();
    }

    @DisplayName("테이프 목록을 조회한다.")
    @Test
    void findAll() {
        tapeRepository.save(betaTape());
        tapeRepository.save(vhsTape());
        assertThat(tapeService.findAll()).hasSize(2);
    }

    private Movie movie(String title, Genre genre) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        return movie;
    }

    private Tape betaTape() {
        return tape(TapeType.BETA);
    }

    private Tape vhsTape() {
        return tape(TapeType.VHS);
    }

    private Tape tape(TapeType type) {
        Tape tape = new Tape();
        tape.setId(UUID.randomUUID());
        tape.setType(type);
        return tape;
    }
}
