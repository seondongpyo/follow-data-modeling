package me.seondongpyo.videoshop.tape.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.movie.domain.Movie;

@Getter
@Setter
@Entity
public class Tape {

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Enumerated
    private TapeType type;
}
