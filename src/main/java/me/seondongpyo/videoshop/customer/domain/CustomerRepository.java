package me.seondongpyo.videoshop.customer.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

	Customer save(Customer customer);

	Optional<Customer> findById(UUID id);

	List<Customer> findAll();
}
