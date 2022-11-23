package com.wholesail.invoice.controller;

import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.manager.InvoiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceManager invoiceManager;

    @RequestMapping("/invoice")
    public String listInvoices(Model model) {
        List<Invoice> invoices = invoiceManager.getAllInvoices();
        model.addAttribute("invoices", invoices);
        return "invoice";
    }
}
