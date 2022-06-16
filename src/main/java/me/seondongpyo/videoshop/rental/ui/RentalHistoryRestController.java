package me.seondongpyo.videoshop.rental.ui;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.rental.application.RentalHistoryService;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RentalHistoryRestController {

    private final RentalHistoryService rentalHistoryService;

    @GetMapping("/customers/{customerId}/rental-histories")
    public ResponseEntity<List<RentalHistoryResponseDTO>> findAllByCustomerId(@PathVariable UUID customerId) {
        List<RentalHistory> rentalHistories = rentalHistoryService.findAllByCustomerId(customerId);
        return ResponseEntity.ok(rentalHistories.stream()
            .map(RentalHistoryResponseDTO::new)
            .collect(Collectors.toList()));
    }
}
