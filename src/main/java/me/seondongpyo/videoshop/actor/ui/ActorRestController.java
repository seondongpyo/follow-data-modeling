package me.seondongpyo.videoshop.actor.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.actor.application.ActorService;
import me.seondongpyo.videoshop.actor.domain.Actor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/actors")
@RestController
public class ActorRestController {

	private final ActorService actorService;

	@PostMapping
	public ResponseEntity<ActorResponseDTO> create(@RequestBody ActorRequestDTO request) {
		Actor actor = actorService.create(request.toEntity());
		return ResponseEntity.created(URI.create("/actors/" + actor.getId()))
				.body(new ActorResponseDTO(actor));
	}

	@GetMapping("/{actorId}")
	public ResponseEntity<ActorResponseDTO> findById(@PathVariable UUID actorId) {
		Actor actor = actorService.findById(actorId);
		return ResponseEntity.ok(new ActorResponseDTO(actor));
	}

	@GetMapping
	public ResponseEntity<List<ActorResponseDTO>> findAll() {
		List<ActorResponseDTO> actors = actorService.findAll()
			.stream()
			.map(ActorResponseDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok(actors);
	}
}
