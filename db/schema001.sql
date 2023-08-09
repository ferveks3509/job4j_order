CREATE TABLE IF NOT EXISTS orders
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR,
    dish_id TEXT,
    status  VARCHAR
);