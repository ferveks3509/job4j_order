create table orders
(
    id         serial primary key,
    order_name varchar,
    dish_id    integer,
    status     varchar
);