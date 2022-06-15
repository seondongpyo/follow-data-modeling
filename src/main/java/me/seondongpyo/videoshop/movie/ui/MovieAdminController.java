package me.seondongpyo.videoshop.movie.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.actor.application.ActorService;
import me.seondongpyo.videoshop.actor.ui.ActorResponseDTO;
import me.seondongpyo.videoshop.actor.ui.StarringActorsForm;
import me.seondongpyo.videoshop.movie.application.MovieService;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
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
	private final ActorService actorService;

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
		List<ActorResponseDTO> actors = actorService.findAll()
			.stream()
			.map(ActorResponseDTO::new)
			.collect(Collectors.toList());

		model.addAttribute("movie", new MovieRequestDTO());
		model.addAttribute("actors", actors);
		return "admin/movie/new";
	}

	@PostMapping
	public String add(@ModelAttribute MovieRequestDTO movieRequest,
					  @RequestParam MultiValueMap<String, Object> starringActorsRequest) {

		StarringActorsForm form = new StarringActorsForm(starringActorsRequest);
		movieRequest.setStarringActors(form.starringActorRequests());

		movieService.create(movieRequest.toEntity());
		return "redirect:/admin/movies";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable UUID id, Model model) {
		List<ActorResponseDTO> actors = actorService.findAll()
			.stream()
			.map(ActorResponseDTO::new)
			.collect(Collectors.toList());

		Movie movie = movieService.findById(id);
		model.addAttribute("movie", new MovieResponseDTO(movie));
		model.addAttribute("actors", actors);
		return "admin/movie/edit";
	}

	@PostMapping("/{id}/edit")
	public String edit(@PathVariable UUID id,
					   @ModelAttribute MovieRequestDTO movieRequest,
					   @RequestParam(required = false) MultiValueMap<String, Object> starringActorsRequest,
					   RedirectAttributes redirectAttributes) {

		List<Object> actorIds = starringActorsRequest.get("actorId");

		if (actorIds != null) {
			StarringActorsForm form = new StarringActorsForm(starringActorsRequest);
			movieRequest.setStarringActors(form.starringActorRequests());
		}

		movieService.update(id, movieRequest);
		redirectAttributes.addAttribute("id", id);
		return "redirect:/admin/movies/{id}";
	}
}
