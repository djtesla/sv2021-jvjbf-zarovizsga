package org.training360.finalexam.teams;

import lombok.*;
import org.training360.finalexam.player.Player;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Player> players = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    public void addPLayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}
