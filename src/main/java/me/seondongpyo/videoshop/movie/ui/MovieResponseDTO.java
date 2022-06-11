package me.seondongpyo.videoshop.movie.ui;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class MovieResponseDTO {

    private final UUID id;
    private final String title;
    private final Genre genre;

    public MovieResponseDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
    }
}
