package me.seondongpyo.videoshop.tape.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.seondongpyo.videoshop.tape.domain.TapeType;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TapeRequestDTO {

    private UUID movieId;
    private TapeType type;
}
