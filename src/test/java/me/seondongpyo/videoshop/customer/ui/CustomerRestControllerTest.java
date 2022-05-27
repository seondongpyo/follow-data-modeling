package me.seondongpyo.videoshop.customer.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.seondongpyo.videoshop.customer.application.CustomerService;
import me.seondongpyo.videoshop.customer.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @DisplayName("새로운 고객을 등록한다.")
    @Test
    void create() throws Exception {
        Customer customer = customer();

        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(customer)));
    }

    @DisplayName("특정 고객을 조회한다.")
    @Test
    void findById() throws Exception {
        Customer created = customerService.create(customer());

        mvc.perform(get("/customers/{id}", created.getId()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(created)));
    }

    @DisplayName("고객 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        Customer customer1 = customer();
        Customer customer2 = customer();
        customerService.create(customer1);
        customerService.create(customer2);

        List<Customer> customers = List.of(customer1, customer2);

        mvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(customers)));
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setMembershipId(UUID.randomUUID());
        return customer;
    }
}
