package com.wholesail.invoice.data.repository;

import com.wholesail.invoice.data.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    List<Balance> findBalanceByCustomerName(String customerName);
}
