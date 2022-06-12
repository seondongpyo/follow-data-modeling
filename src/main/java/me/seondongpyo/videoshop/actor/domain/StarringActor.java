package me.seondongpyo.videoshop.actor.domain;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.movie.domain.Movie;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class StarringActor {

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Actor actor;

    private boolean isLeadRole;

    @Transient
    private UUID actorId;

}
