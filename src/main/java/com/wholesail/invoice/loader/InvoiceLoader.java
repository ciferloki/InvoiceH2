package com.wholesail.invoice.loader;

import java.util.List;

public interface InvoiceLoader {
    /**
     * Returns a list of names of all the resources (Files, S3 Object and etc.)
     * that can be loaded into the DB.
     * @return
     */
    List<String> listAvailableResources();

    /**
     * Load a single resource identified by "name".
     * @param name
     */
    void loadResourceByName(final String name) throws Exception;

    /**
     * Load a single resource identified by information provided in {@link LoadContext}.
     * @param context
     */
    void loadResource(final LoadContext context) throws Exception;

    /**
     * Load all resources available in the data source.
     * @throws Exception
     */
    void loadAllResources() throws Exception;
}
