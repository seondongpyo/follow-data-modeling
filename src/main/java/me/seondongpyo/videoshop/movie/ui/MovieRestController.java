package me.seondongpyo.videoshop.movie.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/movies")
@RestController
public class MovieRestController {

	private final MovieService movieService;

	@PostMapping
	public ResponseEntity<MovieResponseDTO> create(@RequestBody MovieRequestDTO request) {
		Movie movie = movieService.create(request.toEntity());
		return ResponseEntity.created(URI.create("/movies/" + movie.getId()))
				.body(new MovieResponseDTO(movie));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> findById(@PathVariable UUID id) {
		Movie movie = movieService.findById(id);
		return ResponseEntity.ok(new MovieResponseDTO(movie));
	}

	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> findAll() {
		List<MovieResponseDTO> movies = movieService.findAll()
			.stream()
			.map(MovieResponseDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(movies);
	}
}
