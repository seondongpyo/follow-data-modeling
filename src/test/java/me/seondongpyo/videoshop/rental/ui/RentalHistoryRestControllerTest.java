package me.seondongpyo.videoshop.rental.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.movie.domain.Genre;
import me.seondongpyo.videoshop.movie.domain.Movie;
import me.seondongpyo.videoshop.rental.application.RentalHistoryService;
import me.seondongpyo.videoshop.rental.domain.RentalHistory;
import me.seondongpyo.videoshop.tape.domain.Tape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalHistoryRestController.class)
class RentalHistoryRestControllerTest {

    private static final String REQUEST_URI = "/customers/{customerId}/rental-histories";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RentalHistoryService rentalHistoryService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Customer customer;
    private Tape tape;

    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setId(UUID.randomUUID());

        Movie movie = new Movie();
        movie.setTitle("Iron Man");
        movie.setGenre(Genre.ACTION);

        tape = new Tape();
        tape.setId(UUID.randomUUID());
        tape.setMovie(movie);

        RentalHistory rentalHistory = new RentalHistory();
        rentalHistory.setId(UUID.randomUUID());
        rentalHistory.setCustomer(customer);
        rentalHistory.setTape(tape);

        given(rentalHistoryService.create(any(UUID.class), any()))
            .willReturn(rentalHistory);

        given(rentalHistoryService.findAllByCustomerId(customer.getId()))
            .willReturn(List.of(rentalHistory));
    }

    @DisplayName("새로운 테이프 대여 내역을 등록한다.")
    @Test
    void create() throws Exception {
        RentalHistoryRequestDTO request = new RentalHistoryRequestDTO(tape.getId());

        mvc.perform(post(REQUEST_URI, customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());
    }
}
