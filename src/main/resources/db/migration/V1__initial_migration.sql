CREATE TABLE customers
(
    id      BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL
);

CREATE TABLE orders
(
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE items
(
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantity INT UNSIGNED DEFAULT 0 NULL,
    price DECIMAL(10,2),
    UNIQUE (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (item_id) REFERENCES items(id)
);

CREATE TABLE payments (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  reference VARCHAR(255) NOT NULL,
  payment_total DECIMAL(10, 2) NOT NULL,
  orderId BIGINT NOT NULL,
  FOREIGN KEY (orderId) REFERENCES orders(id)
);
