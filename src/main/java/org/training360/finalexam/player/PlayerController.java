package org.training360.finalexam.player;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    public List<PlayerDTO> listAllPlayers() {
        return playerService.listAllPlayers();
    }


    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createPLayer(@RequestBody @Valid CreatePlayerCommand command) {
        return playerService.createPLayer(command);
    }


    @DeleteMapping("/{id}")
    public void deletePlayerById(@PathVariable("id") long id) {
        playerService.deletePlayerById(id);
    }

}
