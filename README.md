# Getting Started

### Run the App

#### From from the project
1. Go to the project folder
2. Run ***"./mvnw clean package"***
3. Run ***"./mvnw spring-boot:run"***
4. Open a browser and visit ***localhost:8080/load***
5. Check the CSV files you would like to load, and click *Submit*
6. Switch to the *Invoice* or *Balance* tab to view the imported invoices and balance report.
7. You could also visit ***localhost:8080/h2***  and see how the data is persisted.
   * JDBC URL: ***jdbc:h2:mem:testdb***
   * User Name: ***sa***
   * Password: ***(LEAVE_EMPTY)***

#### From from Docker
1. Run "***docker run -dp 8080:8080 ciferloki/wholesailcc:0.3***"
2. Rest of the steps are the same from above.

------

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