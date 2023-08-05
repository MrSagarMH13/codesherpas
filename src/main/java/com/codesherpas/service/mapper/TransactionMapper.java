package com.codesherpas.service.mapper;

import com.codesherpas.entity.Transaction;
import com.codesherpas.service.dto.TransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
