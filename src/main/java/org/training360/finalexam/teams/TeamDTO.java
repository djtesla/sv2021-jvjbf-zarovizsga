package org.training360.finalexam.teams;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.training360.finalexam.player.PlayerDTO;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PlayerDTO> players = new HashSet<>();


    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
