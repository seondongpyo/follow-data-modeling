package me.seondongpyo.videoshop.tape.ui;

import lombok.Getter;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeType;

import java.util.UUID;

@Getter
public class TapeResponseDTO {

    private final UUID id;
    private final String title;
    private final Genre genre;
    private final TapeType type;

    public TapeResponseDTO(Tape tape) {
        this.id = tape.getId();
        this.title = tape.getMovie().getTitle();
        this.genre = tape.getMovie().getGenre();
        this.type = tape.getType();
    }
}
