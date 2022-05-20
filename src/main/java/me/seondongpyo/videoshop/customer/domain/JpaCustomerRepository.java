package me.seondongpyo.videoshop.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCustomerRepository extends CustomerRepository, JpaRepository<Customer, UUID> {
}
