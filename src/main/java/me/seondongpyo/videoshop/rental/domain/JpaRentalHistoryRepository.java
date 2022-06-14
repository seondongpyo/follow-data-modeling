package me.seondongpyo.videoshop.rental.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRentalHistoryRepository
    extends RentalHistoryRepository, JpaRepository<RentalHistory, UUID> {
}
