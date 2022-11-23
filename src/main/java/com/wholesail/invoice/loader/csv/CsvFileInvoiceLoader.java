package com.wholesail.invoice.loader.csv;

import com.wholesail.invoice.loader.InvoiceLoader;
import com.wholesail.invoice.loader.LoadContext;
import com.wholesail.invoice.persistent.SellerAwarePersistentManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "CSVFileInvoiceLoader")
public class CsvFileInvoiceLoader implements InvoiceLoader {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileInvoiceLoader.class);

    @Value("classpath:${path.invoice.files}")
    private Resource[] resources;

    @Autowired
    private CsvLoaderWorkerFactory loaderWorkerFactory;

    @Autowired
    private SellerAwarePersistentManager persistentManager;

    /**
     * Returns a list of names of all the resources (Files, S3 Object and etc.)
     * that can be loaded into the DB.
     *
     * @return
     */
    @Override
    public List<String> listAvailableResources() {
        if (resources == null) return Collections.emptyList();
        return Arrays.stream(resources)
                .map(resource -> resource.getFilename())
                .collect(Collectors.toList());
    }

    /**
     * Load a single resource identified by "name".
     * CSVFileInvoiceLoader does the following steps:
     * 1. Convert lines in CSV file into CSVInvoiceItem
     * 2. For each {@link CsvInvoiceItem}, transform it to {@link com.wholesail.invoice.data.entity.Invoice} and persist.
     *
     * @param filename
     */
    @Override
    public void loadResourceByName(String filename) throws Exception {
        Resource fileResource = Arrays.stream(resources)
                .filter(
                        resource -> StringUtils.equals(resource.getFilename(), filename))
                .findFirst()
                .orElseThrow();

        CsvLoadContext context = new CsvLoadContext();
        context.setResource(fileResource);

        // This is really ugly here...
        if (StringUtils.equals("data-golden-gate-produce-10.csv", filename)) {
            context.setProfile(CsvProfile.PROFILE_GOLDEN_GATE_PRODUCE);
        } else if (StringUtils.equals("data-happy-fruits-10.csv", filename)) {
            context.setProfile(CsvProfile.PROFILE_HAPPY_FRUITS);
        }

        loadResourceFromCSVContext(context);
    }

    /**
     * Load a single resource identified by information provided in {@link LoadContext}.
     *
     * @param context
     */
    @Override
    public void loadResource(LoadContext context) throws Exception {
        if (!(context instanceof CsvLoadContext)) {
            logger.error("Cannot load CSV from a non-CSV context.");
            throw new TypeMismatchException(context, CsvLoadContext.class);
        }
        loadResourceFromCSVContext((CsvLoadContext) context);
    }

    /**
     * Load all resources available in the data source.
     *
     * @throws Exception
     */
    @Override
    public void loadAllResources() throws Exception {
        // TODO: Implement this method when needed
    }

    private void loadResourceFromCSVContext(CsvLoadContext context) throws Exception {
        CsvLoaderWorkerWithProfile loaderWorker = loaderWorkerFactory.createLoadWorker(context);
        loaderWorker.load();
        persistentManager.persistInvoices(context.getProfile(), context.getInvoiceEntities());
    }
}
