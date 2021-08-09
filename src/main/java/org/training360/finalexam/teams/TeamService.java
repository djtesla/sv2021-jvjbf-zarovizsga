package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.Player;
import org.training360.finalexam.player.PlayerDTO;
import org.training360.finalexam.player.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TeamService {

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private ModelMapper modelMapper;

    public List<TeamDTO> listAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(team -> modelMapper.map(team, TeamDTO.class)).collect(Collectors.toList());
    }

    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addNewPlayerToTeam(long id, CreatePlayerCommand command) {
        Player playerToBeSigned = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        playerRepository.save(playerToBeSigned);
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team cannot be found by id " + id));
        if (isSignAble(team, playerToBeSigned)) {
            team.addPLayer(playerToBeSigned);
        }
        team = teamRepository.findTeamByIdWithPLayers(id);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public PlayerDTO addExistingPlayerToTeam(long teamId, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team cannot be found by id " + teamId));
        Player playerToBeSigned = playerRepository.findById(command.getPlayerId()).orElseThrow(() -> new IllegalArgumentException("Player cannot be found by id " + command.getPlayerId()));
        if (isSignAble(team, playerToBeSigned)) {
            team.addPLayer(playerToBeSigned);
        } /*else {
            throw new IllegalArgumentException("Player cannot sign to team");

        }*/
        team = teamRepository.findTeamByIdWithPLayers(teamId);
        return modelMapper.map(playerToBeSigned, PlayerDTO.class);

    }

    private boolean isSignAble(Team team, Player playerToBeSigned) {
        return playerToBeSigned.getTeam() == null &&
                team.getPlayers().stream().filter(player -> player.getPosition()==playerToBeSigned.getPosition()).count() < 2;


    }
}
