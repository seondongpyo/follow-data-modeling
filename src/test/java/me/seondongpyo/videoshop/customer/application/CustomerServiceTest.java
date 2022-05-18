package me.seondongpyo.videoshop.customer.application;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

	@DisplayName("고객을 등록한다.")
	@Test
	void create() {
		Customer customer = customer();
		Customer created = customerService.create(customer);
		assertThat(created.getId()).isEqualTo(customer.getId());
	}

	@DisplayName("특정 고객을 조회한다.")
	@Test
	void findById() {
		Customer customer = customer();
		customerRepository.save(customer);
		Customer found = customerService.findById(customer.getId());
		assertThat(found.getId()).isEqualTo(customer.getId());
	}

	@DisplayName("고객 목록을 조회한다.")
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
