package com.wholesail.invoice.loader.csv;

import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.loader.LoadContext;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.util.List;

@Data
public class CsvLoadContext implements LoadContext {
    private String profile;
    private Resource resource;
    private List<CsvInvoiceItem> csvInvoiceItems;
    private List<Invoice> invoiceEntities;
}
