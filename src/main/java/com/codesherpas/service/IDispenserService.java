package com.codesherpas.service;

import com.codesherpas.entity.Dispenser;
import com.codesherpas.service.dto.DispenserDTO;

import java.util.List;

public interface IDispenserService {
    DispenserDTO createDispenser(double flowVolume);

    void openTap(Long dispenserId);

    void closeTap(Long dispenserId);

    DispenserDTO getDispenserById(Long dispenserId);

    List<DispenserDTO> getAllDispensers();
}
