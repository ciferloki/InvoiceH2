DROP TABLE IF EXISTS INVOICE;

CREATE TABLE SELLER (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE INVOICE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seller_id INT NOT NULL,
    customer_name VARCHAR(256) NOT NULL,
    invoice_id VARCHAR(256) NOT NULL,
    payment_term INT NOT NULL,
    amount_in_cents INT NOT NULL,
    adjustment_amount_in_cents INT,
    paid_amount_in_cents INT,
    date_of_transaction DATE NOT NULL,
    date_of_payment_in_full DATE,
    line_items CHAR VARYING,
    billing_address CHAR VARYING,
    shipping_address CHAR VARYING,
    memo CHAR VARYING DEFAULT NULL,
    FOREIGN KEY (seller_id) REFERENCES SELLER(ID)
);

CREATE TABLE BALANCE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seller_id INT NOT NULL,
    customer_name VARCHAR(256) NOT NULL,
    outstanding_balance_in_cents INT,
    past_due_balance_in_cents INT,
    FOREIGN KEY (seller_id) REFERENCES SELLER(ID)
);