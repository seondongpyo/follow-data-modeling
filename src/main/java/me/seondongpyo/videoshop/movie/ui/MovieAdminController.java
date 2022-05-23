package me.seondongpyo.videoshop.movie.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.application.MovieService;
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
}
