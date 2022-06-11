package me.seondongpyo.videoshop.movie.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/admin/movies")
@Controller
public class MovieAdminController {

	private final MovieService movieService;

	@ModelAttribute("genres")
	public List<Genre> genres() {
		return Arrays.stream(Genre.values())
			.collect(Collectors.toList());
	}

	@GetMapping
	public String findAll(Model model) {
		List<MovieResponseDTO> movies = movieService.findAll()
			.stream()
			.map(MovieResponseDTO::new)
			.collect(Collectors.toList());

		model.addAttribute("movies", movies);
		return "admin/movie/list";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable UUID id, Model model) {
		Movie movie = movieService.findById(id);
		model.addAttribute("movie", new MovieResponseDTO(movie));
		return "admin/movie/detail";
	}

	@GetMapping("/new")
	public String addForm(Model model) {
		model.addAttribute("movie", new MovieRequestDTO());
		return "admin/movie/new";
	}

	@PostMapping
	public String add(@ModelAttribute MovieRequestDTO request) {
		movieService.create(request.toEntity());
		return "redirect:/admin/movies";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable UUID id, Model model) {
		Movie movie = movieService.findById(id);
		model.addAttribute("movie", new MovieResponseDTO(movie));
		return "admin/movie/edit";
	}

	@PostMapping("/{id}/edit")
	public String edit(@PathVariable UUID id,
					   @ModelAttribute MovieRequestDTO request,
					   RedirectAttributes redirectAttributes) {

		movieService.update(id, request);
		redirectAttributes.addAttribute("id", id);
		return "redirect:/admin/movies/{id}";
	}
}
