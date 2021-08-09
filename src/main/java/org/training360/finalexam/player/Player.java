package org.training360.finalexam.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.teams.Team;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private PositionType position;

    @ManyToOne
    private Team team;

    public Player(String name, LocalDate birthDate, PositionType position) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
    }
}
