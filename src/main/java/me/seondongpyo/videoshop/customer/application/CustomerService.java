package me.seondongpyo.videoshop.customer.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;

@RequiredArgsConstructor

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
