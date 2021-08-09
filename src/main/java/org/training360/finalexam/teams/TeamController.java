package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.player.CreatePlayerCommand;
import org.training360.finalexam.player.PlayerDTO;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;

    @GetMapping
    public List<TeamDTO> listAllTeams() {
        return teamService.listAllTeams();
    }


    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@RequestBody @Valid CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayerToTeam(@PathVariable("id") long id, @RequestBody CreatePlayerCommand command) {
        return teamService.addNewPlayerToTeam(id, command);
    }

    @PutMapping("/{id}/players")
    public PlayerDTO addExistingPlayerToTeam(@PathVariable("id") long teamId, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.addExistingPlayerToTeam(teamId, command);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("teams/not-found"))
                .withStatus(Status.NOT_FOUND)
                .withTitle("Not found")
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType((MediaType.APPLICATION_JSON))
                .body(problem);


    }

}
