package me.seondongpyo.videoshop.customer.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional(readOnly = true)
	public Customer findById(UUID id) {
		return customerRepository.findById(id)
			.orElseThrow(IllegalArgumentException::new);
	}

	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
