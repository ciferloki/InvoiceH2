package com.wholesail.invoice.loader.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.wholesail.invoice.exception.InvalidStateException;
import com.wholesail.invoice.loader.csv.adaptor.CsvItemAdaptor;
import com.wholesail.invoice.loader.csv.adaptor.impl.AmountInCentCsvItemAdaptor;
import com.wholesail.invoice.loader.csv.adaptor.impl.DefaultCsvItemAdaptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CsvLoaderWorkerFactory {

    /**
     * Assemble a worker based on information provided in the {@link CsvLoadContext}.
     *
     * @param context
     * @return
     * @throws IOException
     */
    public CsvLoaderWorkerWithProfile createLoadWorker(CsvLoadContext context) throws InvalidStateException {
        final Resource resource = context.getResource();
        final String profile = context.getProfile();
        Validate.notNull(resource);
        Validate.notBlank(profile);
        try {
            return CsvLoaderWorkerWithProfile
                    .builder()
                    .context(context)
                    .resource(resource)
                    .profile(profile)
                    .bean(createCSVToBean(resource, profile))
                    .itemAdaptor(createCsvItemAdaptor(profile))
                    .build();
        } catch (IOException e) {
            throw new InvalidStateException(e);
        }
    }

    private CsvToBean<CsvInvoiceItem> createCSVToBean(Resource resource, String profile) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
        CsvToBeanBuilder<CsvInvoiceItem> builder = new CsvToBeanBuilder<>(inputStreamReader);
        builder.withType(CsvInvoiceItem.class);
        builder.withProfile(profile);
        return builder.build();
    }

    private CsvItemAdaptor createCsvItemAdaptor(String profile) {
        if (StringUtils.equals(profile, CsvProfile.PROFILE_GOLDEN_GATE_PRODUCE)) {
            return new AmountInCentCsvItemAdaptor();
        } else if (StringUtils.equals(profile, CsvProfile.PROFILE_HAPPY_FRUITS)) {
            return new DefaultCsvItemAdaptor();
        }
        return new DefaultCsvItemAdaptor();
    }
}
