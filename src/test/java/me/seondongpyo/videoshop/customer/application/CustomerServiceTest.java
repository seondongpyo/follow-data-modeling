package me.seondongpyo.videoshop.customer.application;

import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
		Customer customer = customer();
		Customer created = customerService.create(customer);
		assertThat(created.getId()).isEqualTo(customer.getId());
	}

	@Test
	void findById() {
		Customer customer = customer();
		customerRepository.save(customer);
		assertThat(customerService.findById(customer.getId())).isPresent();
	}

	@Test
	void findAll() {
		customerRepository.save(customer());
		customerRepository.save(customer());
		List<Customer> customers = customerService.findAll();
		assertThat(customers).hasSize(2);
	}

	private Customer customer() {
		Customer customer = new Customer();
		customer.setId(UUID.randomUUID());
		return customer;
	}
}
