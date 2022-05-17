package me.seondongpyo.videoshop.customer.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.customer.domain.CustomerRepository;

public class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<Long, Customer> customers = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Customer save(Customer customer) {
		customer.setId(++sequence);
		customers.put(customer.getId(), customer);
		return customer;
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return Optional.ofNullable(customers.get(id));
	}

	@Override
	public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
	}

	public void clear() {
		customers.clear();
	}
}
