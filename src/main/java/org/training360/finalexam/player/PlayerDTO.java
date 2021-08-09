package org.training360.finalexam.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.teams.TeamDTO;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private PositionType position;
    @JsonIgnore
    private TeamDTO team;

    public PlayerDTO(Long id, String name, LocalDate birthDate, PositionType position) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
    }
}
