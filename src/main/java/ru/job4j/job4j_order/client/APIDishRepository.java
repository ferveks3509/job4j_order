package ru.job4j.job4j_order.client;

import ru.job4j.job4j_order.model.Dish;

import java.util.List;

public interface APIDishRepository {
    Dish findById(int id);
    List<Dish> findAll();
}
