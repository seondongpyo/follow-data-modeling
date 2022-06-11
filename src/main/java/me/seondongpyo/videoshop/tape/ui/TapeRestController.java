package me.seondongpyo.videoshop.tape.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.tape.application.TapeService;
import me.seondongpyo.videoshop.tape.domain.Tape;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/tapes")
@RestController
public class TapeRestController {

    private final TapeService tapeService;

    @PostMapping
    public ResponseEntity<TapeResponseDTO> create(@RequestBody TapeRequestDTO request) {
        Tape tape = tapeService.create(request.getMovieId(), request.getType());
        return ResponseEntity.created(URI.create("/tapes/" + tape.getId()))
                .body(new TapeResponseDTO(tape));
    }

    @GetMapping("/{tapeId}")
    public ResponseEntity<TapeResponseDTO> findById(@PathVariable UUID tapeId) {
        Tape tape = tapeService.findById(tapeId);
        return ResponseEntity.ok(new TapeResponseDTO(tape));
    }

    @GetMapping
    public ResponseEntity<List<TapeResponseDTO>> findAll() {
        List<TapeResponseDTO> tapes = tapeService.findAll()
            .stream()
            .map(TapeResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(tapes);
    }
}
