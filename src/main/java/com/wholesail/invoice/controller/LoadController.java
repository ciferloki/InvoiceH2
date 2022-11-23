package com.wholesail.invoice.controller;

import com.wholesail.invoice.manager.InvoiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoadController {

    @Autowired
    private InvoiceManager invoiceManager;

    @RequestMapping("/load")
    public void listAvailableResources(Model model) {
        List<String> fileNames = invoiceManager.listAvailableRawInvoiceResources();
        model.addAttribute("invoices", fileNames);
    }

    @PostMapping("/load")
    public String loadSelectedResources(@RequestParam String[] selectedResources) {
        invoiceManager.loadResources(selectedResources);
        return "load";
    }
}
