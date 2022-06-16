package me.seondongpyo.videoshop.rental.application;

import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.rental.domain.RentalHistoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryRentalHistoryRepository implements RentalHistoryRepository {

    private final Map<UUID, RentalHistory> rentalHistories = new HashMap<>();

    @Override
    public RentalHistory save(RentalHistory rentalHistory) {
        rentalHistories.put(rentalHistory.getId(), rentalHistory);
        return rentalHistory;
    }

    @Override
    public List<RentalHistory> findAllByCustomerId(UUID customerId) {
        return rentalHistories.values()
            .stream()
            .filter(rentalHistory -> rentalHistory.getCustomer().getId().equals(customerId))
            .collect(Collectors.toList());
    }

    public void clear() {
        rentalHistories.clear();
    }
}
