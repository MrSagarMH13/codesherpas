package com.codesherpas.controller;

import com.codesherpas.entity.Dispenser;
import com.codesherpas.service.dto.DispenserDTO;
import com.codesherpas.service.impl.DispenserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispensers")
public class DispenserController {

    @Autowired
    private DispenserServiceImpl dispenserService;

    @PostMapping
    public ResponseEntity<DispenserDTO> createDispenser(@RequestParam double flowVolume) {
        DispenserDTO dispenserDTO = dispenserService.createDispenser(flowVolume);
        return ResponseEntity.ok(dispenserDTO);
    }

    @PostMapping("/{dispenserId}/open")
    public ResponseEntity<Void> openTap(@PathVariable Long dispenserId) {
        dispenserService.openTap(dispenserId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{dispenserId}/close")
    public ResponseEntity<Void> closeTap(@PathVariable Long dispenserId) {
        dispenserService.closeTap(dispenserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dispenserId}/info")
    public ResponseEntity<DispenserDTO> getDispenserInfo(@PathVariable Long dispenserId) {
        DispenserDTO dispenserDTO = dispenserService.getDispenserById(dispenserId);
        return ResponseEntity.ok(dispenserDTO);
    }

    @GetMapping
    public ResponseEntity<List<DispenserDTO>> getAllDispensers() {
        List<DispenserDTO> dispensersDtos = dispenserService.getAllDispensers();
        return ResponseEntity.ok(dispensersDtos);
    }
}
