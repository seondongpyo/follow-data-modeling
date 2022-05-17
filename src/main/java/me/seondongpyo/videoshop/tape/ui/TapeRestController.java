package me.seondongpyo.videoshop.tape.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.tape.application.TapeService;
import me.seondongpyo.videoshop.tape.domain.Tape;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/tapes")
@RestController
public class TapeRestController {

    private final TapeService tapeService;

    @PostMapping
    public ResponseEntity<Tape> create(@RequestBody Tape tape) {
        Tape created = tapeService.create(tape);
        return ResponseEntity.created(URI.create("/tapes/" + created.getId()))
                .body(created);
    }

    @GetMapping("/{tapeId}")
    public ResponseEntity<Tape> findById(@PathVariable UUID tapeId) {
        return ResponseEntity.ok(tapeService.findById(tapeId));
    }

    @GetMapping
    public ResponseEntity<List<Tape>> findAll() {
        return ResponseEntity.ok(tapeService.findAll());
    }
}
