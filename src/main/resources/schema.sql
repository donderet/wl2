-- Database schema for Pizzeria

-- DROP DATABASE IF EXISTS pizzeria_db; -- Usually databases are created outside the script
-- CREATE DATABASE pizzeria_db; -- Usually databases are created outside the script
-- \c pizzeria_db -- Connect to the database (if running from psql)

-- Create tables
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

CREATE TABLE pizzas (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT,
    pizza_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (pizza_id) REFERENCES pizzas(id)
);

-- Insert initial data for auxiliary entities (customers and pizzas)
INSERT INTO customers (name, contact_info) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com');

INSERT INTO pizzas (name, price) VALUES
('Margherita', 10.99),
('Pepperoni', 12.99),
('Vegetarian', 11.99); 