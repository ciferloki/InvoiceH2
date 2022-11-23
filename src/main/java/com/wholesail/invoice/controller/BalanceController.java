package com.wholesail.invoice.controller;

import com.wholesail.invoice.data.entity.Balance;
import com.wholesail.invoice.manager.BalanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BalanceController {

    @Autowired
    private BalanceManager balanceManager;

    @RequestMapping("/balance")
    public String listBalances(Model model) {
        List<Balance> balances = balanceManager.getAllBalances();
        model.addAttribute("balances", balances);
        return "balance";
    }
}
