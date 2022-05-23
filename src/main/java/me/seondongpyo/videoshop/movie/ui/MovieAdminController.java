package me.seondongpyo.videoshop.movie.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;

@RequiredArgsConstructor
@RequestMapping("/admin/movies")
@Controller
public class MovieAdminController {

	private final MovieService movieService;

	@GetMapping
	public String findAll(Model model) {
		List<Movie> movies = movieService.findAll();
		model.addAttribute("movies", movies);
		return "admin/movie/list";
	}

	@GetMapping("/new")
	public String addForm(Model model) {
		List<Genre> genres = Arrays.stream(Genre.values())
			.collect(Collectors.toList());
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", genres);
		return "admin/movie/new";
	}

	@PostMapping
	public String add(@ModelAttribute Movie movie) {
		movieService.create(movie);
		return "redirect:/admin/movies";
	}
}
