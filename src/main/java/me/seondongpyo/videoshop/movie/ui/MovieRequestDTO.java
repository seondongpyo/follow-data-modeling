package me.seondongpyo.videoshop.movie.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

@Getter
@Setter
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
