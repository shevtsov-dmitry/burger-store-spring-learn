-- CREATE MAIN TABLES

CREATE TABLE IF NOT EXISTS burger
(
    id          SERIAL PRIMARY KEY NOT NULL,
    order_id    INT,
    name        varchar(10),
    datecreated DATE
);

CREATE TABLE IF NOT EXISTS "order"
(
    id        SERIAL PRIMARY KEY NOT NULL,
    burger_id INT,
    placedAt  DATE,
    city      varchar(50),
    street    varchar(100),
    apartment varchar(10)
);

CREATE TABLE IF NOT EXISTS ingredients
(
    id         SERIAL PRIMARY KEY NOT NULL,
    burger_id  INT,
    lettuce    boolean,
    bacon      boolean,
    tomato     boolean,
    onion      boolean,
    pickles    boolean,
    cheese     boolean,
    mayonnaise boolean,
    ketchup    boolean
);

-- ADD FOREIGN KEYS

ALTER TABLE "order" ADD FOREIGN KEY (burger_id) REFERENCES burger(id);
ALTER TABLE burger ADD FOREIGN KEY (order_id) REFERENCES "order"(id);
ALTER TABLE ingredients ADD FOREIGN KEY (burger_id) REFERENCES burger(id);