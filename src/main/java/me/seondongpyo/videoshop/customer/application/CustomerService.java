package me.seondongpyo.videoshop.customer.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional(readOnly = true)
	public Optional<Customer> findById(UUID id) {
		return customerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
