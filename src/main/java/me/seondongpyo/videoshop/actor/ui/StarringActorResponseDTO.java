package me.seondongpyo.videoshop.actor.ui;

import lombok.Getter;
import me.seondongpyo.videoshop.actor.domain.StarringActor;

import java.util.UUID;

@Getter
public class StarringActorResponseDTO {

    private final UUID id;
    private final ActorResponseDTO actor;
    private final boolean isLeadRole;

    public StarringActorResponseDTO(StarringActor starringActor) {
        this.id = starringActor.getId();
        this.actor = new ActorResponseDTO(starringActor.getActor());
        this.isLeadRole = starringActor.isLeadRole();
    }
}
