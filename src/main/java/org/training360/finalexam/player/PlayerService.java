package org.training360.finalexam.player;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class PlayerService {

    private PlayerRepository playerRepository;
    private ModelMapper modelMapper;


    public List<PlayerDTO> listAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(player -> modelMapper.map(player, PlayerDTO.class)).collect(Collectors.toList());
    }

    public PlayerDTO createPLayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }


    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }
}
