package me.seondongpyo.videoshop.actor.ui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.seondongpyo.videoshop.actor.domain.StarringActor;

import java.util.UUID;

@Getter
@Setter
@ToString
public class StarringActorRequestDTO {

	private UUID actorId;
	private boolean isLeadRole;

	public StarringActor toEntity() {
		StarringActor starringActor = new StarringActor();
		starringActor.setActorId(actorId);
		starringActor.setLeadRole(isLeadRole);
		return starringActor;
	}
}
