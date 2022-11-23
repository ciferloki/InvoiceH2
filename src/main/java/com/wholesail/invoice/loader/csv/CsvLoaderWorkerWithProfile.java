package com.wholesail.invoice.loader.csv;

import com.opencsv.bean.CsvToBean;
import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.loader.csv.adaptor.CsvItemAdaptor;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Builder
public class CsvLoaderWorkerWithProfile {

    private static final Logger logger = LoggerFactory.getLogger(CsvLoaderWorkerWithProfile.class);

    private String profile;
    private Resource resource;
    private CsvLoadContext context;
    private CsvToBean<CsvInvoiceItem> bean;
    private CsvItemAdaptor itemAdaptor;

    public void load() {

        logger.info("Loading items from CSV file {} ...", resource.getFilename());
        List<CsvInvoiceItem> csvInvoiceItems = bean.parse();
        logger.info("Done extracting {} items from CSV file.", csvInvoiceItems.size());
        context.setCsvInvoiceItems(csvInvoiceItems);

        List<Invoice> invoiceEntities = new ArrayList<>();

        logger.info("Parsing items extracted from CSV file into persistable Invoice entities...");
        for (CsvInvoiceItem csvItem : csvInvoiceItems) {
            final Invoice invoice;
            try {
                invoice = itemAdaptor.toInvoiceEntity(csvItem);
                invoiceEntities.add(invoice);
            } catch (ParseException e) {
                logger.error("Error parsing CSV item {}.", csvItem);
            }
        }

        logger.info("Done parsing {} of {} invoice items.", invoiceEntities.size(), csvInvoiceItems.size());
        context.setInvoiceEntities(invoiceEntities);
    }
}
