package me.seondongpyo.videoshop.tape.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Tape {

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @Enumerated
    private TapeType type;
}
