package me.seondongpyo.videoshop.rental.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seondongpyo.videoshop.common.BaseEntity;
import me.seondongpyo.videoshop.customer.domain.Customer;
import me.seondongpyo.videoshop.tape.domain.Tape;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class RentalHistory extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tape tape;

    private boolean isReturned;
}
