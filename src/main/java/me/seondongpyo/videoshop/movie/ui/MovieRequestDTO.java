package me.seondongpyo.videoshop.movie.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {

    private String title;
    private Genre genre;

    public Movie toEntity() {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        return movie;
    }
}
