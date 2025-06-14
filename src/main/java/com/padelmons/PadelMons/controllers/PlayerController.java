package com.padelmons.PadelMons.controllers;

import com.padelmons.PadelMons.Dto.PlayerDTO;
import com.padelmons.PadelMons.entities.DataContact;
import com.padelmons.PadelMons.entities.Player;
import com.padelmons.PadelMons.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createplayer")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDTO dto) {
        Player player = new Player();
        player.setName(dto.name);
        player.setSurname(dto.surname);
        player.setAge(dto.age);
        player.setGender(dto.gender);
        player.setImgUrl(dto.imgUrl);
        DataContact contact = new DataContact();
        contact.setEmail(dto.email);
        contact.setPhone(dto.phone);
        player.setDataContact(contact);

        player.setTeam(null);
        Player saved = playerRepository.save(player);

        return ResponseEntity.ok(saved);
    }
    @GetMapping("/showplayers")
    public ResponseEntity<List<Player>> showPlayers() {
        List<Player> players = playerRepository.findAll();
        return ResponseEntity.ok(players);
    }


}
