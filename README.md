# Getting Started

### Run the App

1. Go to the project folder
1. Run ***"./mvnw spring-boot:run"***
1. Open a browser and visit ***localhost:8080/load***
1. Check the CSV files you would like to load, and click *Submit*
2. Switch to the *Invoice* or *Balance* tab to view the imported invoices and balance report.

### Project Structure
* **InvoiceH2Application** - Application starter.
* **Controller** - Controllers to handle requests with regard to Invoice and Balance.
* **Data**
  * **Entity** - Used for persisting Invoice, Balance and Seller into DB; The Entity is also being directly referenced in the View layer which is not ideal.
  * **Query** - Object to hold query results to the DB.
  * **Repository** -  Handle to the Invoice, Balance and Seller tables.
* **Loader** - Load invoice data from various data sources, CSV is the only supported loader for the time being.
* **Manager** - Handles various business logics.
* **Persistent** - Responsible for the persistence of various data.