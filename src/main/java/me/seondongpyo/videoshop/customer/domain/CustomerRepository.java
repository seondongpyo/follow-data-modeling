package me.seondongpyo.videoshop.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

	Customer save(Customer customer);

	Optional<Customer> findById(Long id);

	List<Customer> findAll();
}
