package me.seondongpyo.videoshop.actor.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seondongpyo.videoshop.actor.domain.Actor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActorRequestDTO {

    private String stageName;
    private String realName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public Actor toEntity() {
        Actor actor = new Actor();
        actor.setStageName(stageName);
        actor.setRealName(realName);
        actor.setBirthDate(birthDate);
        return actor;
    }
}
