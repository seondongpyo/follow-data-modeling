package me.seondongpyo.videoshop.customer.ui;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.seondongpyo.videoshop.customer.application.CustomerService;
import me.seondongpyo.videoshop.customer.domain.Customer;

@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerRestController {

	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		Customer created = customerService.create(customer);
		return ResponseEntity.created(URI.create("/customers/" + created.getId()))
				.body(created);
	}

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> customers = customerService.findAll();
		return ResponseEntity.ok(customers);
	}
}
