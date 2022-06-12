package me.seondongpyo.videoshop.actor.ui;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.actor.domain.StarringActor;

@Getter
@Setter
public class StarringActorRequestDTO {

	private UUID actorId;
	private boolean isLeadRole;

	public StarringActor toEntity() {
		StarringActor starringActor = new StarringActor();
		starringActor.setId(UUID.randomUUID());
		starringActor.setActorId(actorId);
		starringActor.setLeadRole(isLeadRole);
		return starringActor;
	}
}
