package me.seondongpyo.videoshop.movie.ui;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Movie;

@RequiredArgsConstructor
@RequestMapping("/movies")
@RestController
public class MovieRestController {

	private final MovieService movieService;

	@PostMapping
	public ResponseEntity<Movie> create(@RequestBody Movie movie) {
		Movie created = movieService.create(movie);
		return ResponseEntity.created(URI.create("/movies/" + movie.getId()))
				.body(created);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> findById(@PathVariable UUID movieId) {
		return ResponseEntity.ok(movieService.findById(movieId));
	}

	@GetMapping
	public ResponseEntity<List<Movie>> findAll() {
		return ResponseEntity.ok(movieService.findAll());
	}
}
