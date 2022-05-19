package me.seondongpyo.videoshop.actor.ui;

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
import me.seondongpyo.videoshop.actor.application.ActorService;
import me.seondongpyo.videoshop.actor.domain.Actor;

@RequiredArgsConstructor
@RequestMapping("/actors")
@RestController
public class ActorRestController {

	private final ActorService actorService;

	@PostMapping
	public ResponseEntity<Actor> create(@RequestBody Actor actor) {
		Actor created = actorService.create(actor);
		return ResponseEntity.created(URI.create("/actors/" + created.getId()))
				.body(created);
	}

	@GetMapping("/{actorId}")
	public ResponseEntity<Actor> findById(@PathVariable UUID actorId) {
		return ResponseEntity.ok(actorService.findById(actorId));
	}

	@GetMapping
	public ResponseEntity<List<Actor>> findAll() {
		return ResponseEntity.ok(actorService.findAll());
	}
}
