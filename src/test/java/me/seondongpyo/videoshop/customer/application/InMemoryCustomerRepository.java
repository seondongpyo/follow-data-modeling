package me.seondongpyo.videoshop.customer.application;

import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<UUID, Customer> customers = new HashMap<>();

	@Override
	public Customer save(Customer customer) {
		customers.put(customer.getId(), customer);
		return customer;
	}

	@Override
	public Optional<Customer> findById(UUID id) {
		return Optional.ofNullable(customers.get(id));
	}

	@Override
	public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
	}
}
