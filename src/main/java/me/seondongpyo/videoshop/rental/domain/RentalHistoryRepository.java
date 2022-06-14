package me.seondongpyo.videoshop.rental.domain;

import java.util.List;
import java.util.UUID;

public interface RentalHistoryRepository {

    RentalHistory save(RentalHistory rentalHistory);

    List<RentalHistory> findAllByCustomerId(UUID customerId);
}
