package com.wholesail.invoice.manager;

import com.wholesail.invoice.data.entity.Balance;
import com.wholesail.invoice.data.repository.BalanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceManager {
    private static final Logger logger = LoggerFactory.getLogger(BalanceManager.class);

    @Autowired
    private BalanceRepository balanceRepository;

    public List<Balance> getAllBalances() {
        logger.info("Loading all persisted balances...");
        return balanceRepository.findAll();
    }
}
