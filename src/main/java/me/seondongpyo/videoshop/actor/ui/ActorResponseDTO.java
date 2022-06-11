package me.seondongpyo.videoshop.actor.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import me.seondongpyo.videoshop.actor.domain.Actor;

import java.util.UUID;

@Getter
@Setter
public class ActorResponseDTO {

    private UUID id;
    private String stageName;
    private String realName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthDate;

    public ActorResponseDTO(Actor actor) {
        this.id = actor.getId();
        this.stageName = actor.getStageName();
        this.realName = actor.getRealName();
        this.birthDate = actor.getBirthDate().toString();
    }
}
