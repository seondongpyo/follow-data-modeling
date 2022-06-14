package me.seondongpyo.videoshop.rental.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.rental.domain.RentalHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class RentalHistoryService {

    private final RentalHistoryRepository rentalHistoryRepository;

    public RentalHistory create(RentalHistory rentalHistory) {
        return rentalHistoryRepository.save(rentalHistory);
    }

    public List<RentalHistory> findAllByCustomerId(UUID customerId) {
        return rentalHistoryRepository.findAllByCustomerId(customerId);
    }
}
