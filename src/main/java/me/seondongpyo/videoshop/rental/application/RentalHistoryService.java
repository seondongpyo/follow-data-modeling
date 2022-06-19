package me.seondongpyo.videoshop.rental.application;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.rental.domain.RentalHistoryRepository;
import me.seondongpyo.videoshop.rental.ui.RentalHistoryRequestDTO;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class RentalHistoryService {

    private final RentalHistoryRepository rentalHistoryRepository;
    private final CustomerRepository customerRepository;
    private final TapeRepository tapeRepository;

    public RentalHistory create(UUID customerId, RentalHistoryRequestDTO request) {
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(IllegalArgumentException::new);

        Tape tape = tapeRepository.findById(request.getTapeId())
            .orElseThrow(IllegalArgumentException::new);

        RentalHistory rentalHistory = new RentalHistory();
        rentalHistory.setCustomer(customer);
        rentalHistory.setTape(tape);

        return rentalHistoryRepository.save(rentalHistory);
    }

    @Transactional(readOnly = true)
    public List<RentalHistory> findAllByCustomerId(UUID customerId) {
        return rentalHistoryRepository.findAllByCustomerId(customerId);
    }
}
