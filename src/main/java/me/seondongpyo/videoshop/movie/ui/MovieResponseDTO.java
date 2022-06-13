package me.seondongpyo.videoshop.movie.ui;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.seondongpyo.videoshop.actor.ui.StarringActorResponseDTO;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class MovieResponseDTO {

    private final UUID id;
    private final String title;
    private final Genre genre;
    private final List<StarringActorResponseDTO> starringActors;

    public MovieResponseDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.starringActors = movie.getStarringActors()
            .stream()
            .map(StarringActorResponseDTO::new)
            .collect(Collectors.toList());
    }
}
