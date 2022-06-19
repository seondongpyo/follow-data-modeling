package me.seondongpyo.videoshop.rental.ui;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RentalHistoryRequestDTO {

    private UUID tapeId;

    public RentalHistoryRequestDTO(UUID tapeId) {
        this.tapeId = tapeId;
    }
}
