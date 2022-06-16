package me.seondongpyo.videoshop.rental.ui;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.tape.ui.TapeResponseDTO;

import java.util.UUID;

@Getter
@Setter
public class RentalHistoryResponseDTO {

    private UUID id;
    private TapeResponseDTO tape;
    private boolean isReturned;

    public RentalHistoryResponseDTO(RentalHistory rentalHistory) {
        this.id = rentalHistory.getId();
        this.tape = new TapeResponseDTO(rentalHistory.getTape());
        this.isReturned = rentalHistory.isReturned();
    }
}
