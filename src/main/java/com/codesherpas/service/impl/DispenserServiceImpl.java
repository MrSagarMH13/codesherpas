package com.codesherpas.service.impl;

import com.codesherpas.entity.Dispenser;
import com.codesherpas.entity.Transaction;
import com.codesherpas.enums.DispenserStatus;
import com.codesherpas.repository.DispenserRepository;
import com.codesherpas.repository.TransactionRepository;
import com.codesherpas.service.IDispenserService;
import com.codesherpas.service.dto.DispenserDTO;
import com.codesherpas.service.mapper.DispenserMapper;
import com.codesherpas.service.mapper.TransactionMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DispenserServiceImpl implements IDispenserService {

    private final DispenserRepository dispenserRepository;

    private final TransactionRepository transactionRepository;

    private final DispenserMapper dispenserMapper;

    private final TransactionMapper transactionMapper;

    @Autowired
    public DispenserServiceImpl(DispenserRepository dispenserRepository, TransactionRepository transactionRepository,
                                DispenserMapper dispenserMapper, TransactionMapper transactionMapper) {
        this.dispenserRepository = dispenserRepository;
        this.transactionRepository = transactionRepository;
        this.dispenserMapper = dispenserMapper;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public DispenserDTO createDispenser(double flowVolume) {
        Dispenser dispenser = new Dispenser();
        dispenser.setFlowVolume(flowVolume);
        dispenser.setDispenserStatus(DispenserStatus.CLOSE);
        dispenser.setTotalRevenue(0.0);
        dispenserRepository.save(dispenser);
        return dispenserMapper.toDto(dispenser);

    }

    @Override
    public void openTap(Long dispenserId) {
        Dispenser dispenser = dispenserRepository.findById(dispenserId)
                .orElseThrow(() -> new EntityNotFoundException("Dispenser not found"));

        dispenser.setDispenserStatus(DispenserStatus.OPEN);
        dispenserRepository.save(dispenser);

        Transaction transaction = new Transaction();
        transaction.setDispenserId(dispenserId);
        transaction.setStartTime(LocalDateTime.now());
        transaction.setAmount(0.0);
        transactionRepository.save(transaction);
    }

    @Override
    public void closeTap(Long dispenserId) {
        Dispenser dispenser = dispenserRepository.findById(dispenserId)
                .orElseThrow(() -> new EntityNotFoundException("Dispenser not found"));

        dispenser.setDispenserStatus(DispenserStatus.CLOSE);
        dispenserRepository.save(dispenser);

        List<Transaction> transactions = transactionRepository.findByDispenserIdOrderByStartTimeDesc(dispenserId);
        if (!transactions.isEmpty()) {
            Transaction lastTransaction = transactions.get(0);
            lastTransaction.setEndTime(LocalDateTime.now());
            lastTransaction.setAmount(calculateAmountSpent(lastTransaction));
            transactionRepository.save(lastTransaction);

            dispenser.setTotalRevenue(dispenser.getTotalRevenue() + lastTransaction.getAmount());
            dispenserRepository.save(dispenser);
        }
    }

    private double calculateAmountSpent(Transaction transaction) {
        double flowVolume = dispenserRepository.findById(transaction.getDispenserId())
                .map(Dispenser::getFlowVolume)
                .orElse(0.0);
        Duration duration = Duration.between(transaction.getStartTime(), transaction.getEndTime());
        long seconds = duration.getSeconds();
        return flowVolume * seconds;
    }

    @Override
    public DispenserDTO getDispenserById(Long dispenserId) {
        return dispenserRepository.findById(dispenserId).map(dispenserMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Dispenser not found"));
    }

    @Override
    public List<DispenserDTO> getAllDispensers() {
        List<Dispenser> dispensers = dispenserRepository.findAll();
        return dispenserMapper.toDto(dispensers);
    }
}
