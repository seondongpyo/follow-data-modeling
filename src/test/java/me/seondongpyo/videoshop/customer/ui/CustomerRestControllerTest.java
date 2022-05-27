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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setMembershipId(UUID.randomUUID());

        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isCreated());
    }

    @DisplayName("특정 고객을 조회한다.")
    @Test
    void findById() throws Exception {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setMembershipId(UUID.randomUUID());
        Customer created = customerService.create(customer);

        mvc.perform(get("/customers/{id}", created.getId()))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(created)));
    }

    @DisplayName("고객 목록을 조회한다.")
    @Test
    void findAll() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(UUID.randomUUID());
        customer1.setMembershipId(UUID.randomUUID());
        customerService.create(customer1);

        Customer customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setMembershipId(UUID.randomUUID());
        customerService.create(customer2);

        List<Customer> customers = List.of(customer1, customer2);

        mvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(customers)));
    }
}
