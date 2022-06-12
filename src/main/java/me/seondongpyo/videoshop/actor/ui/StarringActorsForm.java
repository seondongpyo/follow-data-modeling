package me.seondongpyo.videoshop.actor.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.util.MultiValueMap;

public class StarringActorsForm {

	private static final String KEY_ACTOR_ID = "actorId";
	private static final String KEY_IS_LEAD_ROLE = "isLeadRole";

	private final MultiValueMap<String, Object> starringActors;

	public StarringActorsForm(MultiValueMap<String, Object> starringActors) {
		validate(starringActors);
		this.starringActors = starringActors;
	}

	private void validate(MultiValueMap<String, Object> starringActors) {
		List<Object> actorIds = starringActors.get(KEY_ACTOR_ID);
		List<Object> isLeadRoles = starringActors.get(KEY_IS_LEAD_ROLE);

		if (actorIds.size() != isLeadRoles.size()) {
			throw new IllegalArgumentException();
		}
	}

	public List<StarringActorRequestDTO> starringActorRequests() {
		List<UUID> actorIds = actorIds();
		List<Boolean> isLeadRoles = isLeadRoles();

		List<StarringActorRequestDTO> starringActorRequests = new ArrayList<>();
		for (int index = 0; index < actorIds.size(); index++) {
			StarringActorRequestDTO starringActor = new StarringActorRequestDTO();
			starringActor.setActorId(actorIds.get(index));
			starringActor.setLeadRole(isLeadRoles.get(index));
			starringActorRequests.add(starringActor);
		}

		return starringActorRequests;
	}

	private List<UUID> actorIds() {
		return starringActors.get(KEY_ACTOR_ID).stream()
			.map(actorId -> UUID.fromString(String.valueOf(actorId)))
			.collect(Collectors.toList());
	}

	private List<Boolean> isLeadRoles() {
		return starringActors.get(KEY_IS_LEAD_ROLE).stream()
			.map(isLeadRole -> Boolean.valueOf(String.valueOf(isLeadRole)))
			.collect(Collectors.toList());
	}
}
