package me.seondongpyo.videoshop.customer.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;

class CustomerServiceTest {

	private CustomerRepository customerRepository;
	private CustomerService customerService;

	@BeforeEach
	void setup() {
		customerRepository = new InMemoryCustomerRepository();
		customerService = new CustomerService(customerRepository);
	}

	@Test
	void create() {
		Customer customer = new Customer();
		Customer created = customerService.create(customer);
		assertThat(created.getId()).isEqualTo(1L);
	}

	@Test
	void findAll() {
		customerRepository.save(new Customer());
		customerRepository.save(new Customer());
		List<Customer> customers = customerService.findAll();
		assertThat(customers).hasSize(2);
	}
}
