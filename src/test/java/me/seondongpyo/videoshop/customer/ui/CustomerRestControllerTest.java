package me.seondongpyo.videoshop.customer.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.seondongpyo.videoshop.customer.application.CustomerService;
import me.seondongpyo.videoshop.customer.domain.Customer;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Customer customer1 = customer();
    private final Customer customer2 = customer();

    @BeforeEach
    void setup() {
        given(customerService.create(any(Customer.class))).willReturn(customer1);
        given(customerService.findById(any(UUID.class))).willReturn(customer1);
        given(customerService.findAll()).willReturn(List.of(customer1, customer2));
    }

    @DisplayName("새로운 고객을 등록한다.")
    @Test
    void create() throws Exception {
        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer1)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(customer1)));
    }

    @DisplayName("특정 고객을 조회한다.")
    @Test
    void findById() throws Exception {
        mvc.perform(get("/customers/{id}", customer1.getId()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(customer1)));
    }

    @DisplayName("고객 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        mvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(List.of(customer1, customer2))));
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setMembershipId(UUID.randomUUID());
        return customer;
    }
}
