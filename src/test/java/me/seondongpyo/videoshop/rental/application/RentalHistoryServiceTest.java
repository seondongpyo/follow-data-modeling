package me.seondongpyo.videoshop.rental.application;

import me.seondongpyo.videoshop.customer.application.InMemoryCustomerRepository;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.rental.ui.RentalHistoryRequestDTO;
import me.seondongpyo.videoshop.tape.application.InMemoryTapeRepository;
import me.seondongpyo.videoshop.tape.domain.Tape;
import me.seondongpyo.videoshop.tape.domain.TapeType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RentalHistoryServiceTest {

    private InMemoryRentalHistoryRepository rentalHistoryRepository;
    private InMemoryCustomerRepository customerRepository;
    private InMemoryTapeRepository tapeRepository;
    private RentalHistoryService rentalHistoryService;

    @BeforeEach
    void setup() {
        rentalHistoryRepository = new InMemoryRentalHistoryRepository();
        customerRepository = new InMemoryCustomerRepository();
        tapeRepository = new InMemoryTapeRepository();
        rentalHistoryService = new RentalHistoryService(rentalHistoryRepository, customerRepository, tapeRepository);
    }

    @AfterEach
    void teardown() {
        rentalHistoryRepository.clear();
    }

    @DisplayName("새로운 대여 내역을 등록한다.")
    @Test
    void create() {
        Customer customer = customerRepository.save(customer("홍길동"));
        Tape tape = tapeRepository.save(tape(TapeType.VHS));
        RentalHistoryRequestDTO request = new RentalHistoryRequestDTO(tape.getId());

        RentalHistory rentalHistory = rentalHistoryService.create(customer.getId(), request);

        assertAll(() -> {
            assertThat(rentalHistory.getCustomer().getId()).isEqualTo(customer.getId());
            assertThat(rentalHistory.getTape().getId()).isEqualTo(tape.getId());
        });
    }

    @DisplayName("고객의 id로 대여 내역 목록을 조회한다.")
    @Test
    void findAllByCustomerId() {
        Customer customer = customer("김길동");
        rentalHistoryRepository.save(rentalHistory(customer, tape(TapeType.VHS)));
        rentalHistoryRepository.save(rentalHistory(customer, tape(TapeType.BETA)));

        List<RentalHistory> rentalHistories = rentalHistoryService.findAllByCustomerId(customer.getId());

        assertThat(rentalHistories).hasSize(2);
    }

    private RentalHistory rentalHistory(Customer customer, Tape tape) {
        RentalHistory rentalHistory = new RentalHistory();
        rentalHistory.setId(UUID.randomUUID());
        rentalHistory.setCustomer(customer);
        rentalHistory.setTape(tape);
        return rentalHistory;
    }

    private Tape tape(TapeType type) {
        Tape tape = new Tape();
        tape.setId(UUID.randomUUID());
        tape.setType(type);
        return tape;
    }

    private Customer customer(String name) {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName(name);
        return customer;
    }
}
