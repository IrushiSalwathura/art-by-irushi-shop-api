CREATE TABLE customers
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    customer_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE items
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    total_price DECIMAL(10, 2) NOT NULL,
    quantity INT UNSIGNED DEFAULT 0 NULL,
    order_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (order_id, item_id)
);

ALTER TABLE orders
    ADD CONSTRAINT orders_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE NO ACTION;

CREATE INDEX orders_customers_id_fk ON orders (customer_id);

ALTER TABLE order_items
    ADD CONSTRAINT order_items_orders_id_fk FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE NO ACTION;

ALTER TABLE order_items
    ADD CONSTRAINT order_items_items_id_fk FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE NO ACTION;

CREATE INDEX order_items_orders_id_fk ON order_items (order_id);