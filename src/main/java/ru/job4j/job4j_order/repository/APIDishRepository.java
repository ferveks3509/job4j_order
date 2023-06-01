package ru.job4j.job4j_order.repository;

import ru.job4j.job4j_order.model.Dish;

public interface APIDishRepository {
    Dish findById(int id);
}
