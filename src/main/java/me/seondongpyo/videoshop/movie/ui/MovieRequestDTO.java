package me.seondongpyo.videoshop.movie.ui;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.seondongpyo.videoshop.actor.ui.StarringActorRequestDTO;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {

    private String title;
    private Genre genre;
    private List<StarringActorRequestDTO> starringActors;

    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setStarringActors(starringActors.stream()
            .map(StarringActorRequestDTO::toEntity)
            .collect(Collectors.toList()));
        return movie;
    }
}
