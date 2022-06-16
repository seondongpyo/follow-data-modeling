package me.seondongpyo.videoshop.rental.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JpaRentalHistoryRepository
    extends RentalHistoryRepository, JpaRepository<RentalHistory, UUID> {

    @Override
    @Query("select r from RentalHistory r join fetch r.tape where r.customer.id = :customerId")
    List<RentalHistory> findAllByCustomerId(@Param("customerId") UUID customerId);
}
