package me.seondongpyo.videoshop.tape.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.common.BaseEntity;
import me.seondongpyo.videoshop.movie.domain.Movie;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Tape extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Enumerated
    private TapeType type;
}
