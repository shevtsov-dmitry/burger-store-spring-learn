CREATE TABLE IF NOT EXISTS "order"(
    id  SERIAL PRIMARY KEY NOT NULL,
    placedAt DATE,
    city varchar(50),
    street varchar(100),
    apartment varchar(10)
);

CREATE TABLE IF NOT EXISTS burger(
    id SERIAL PRIMARY KEY NOT NULL,
    datecreated DATE,
    name varchar(10)
);

CREATE TABLE IF NOT EXISTS ingredients(
    id SERIAL PRIMARY KEY NOT NULL,
    lettuce boolean,
    bacon boolean,
    tomato boolean,
    onion boolean,
    pickles boolean,
    cheese boolean,
    mayonnaise boolean,
    ketchup boolean
);

CREATE TABLE IF NOT EXISTS burger_ingredients(
    burger_id INT REFERENCES burger(id),
    ingredient_id INT REFERENCES ingredients(id),
    PRIMARY KEY (burger_id, ingredient_id)

)