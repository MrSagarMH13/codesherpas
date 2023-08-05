package com.codesherpas.service.mapper;

import com.codesherpas.entity.Dispenser;
import com.codesherpas.service.dto.DispenserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DispenserMapper extends EntityMapper<DispenserDTO, Dispenser> {
}
