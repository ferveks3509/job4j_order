package ru.job4j.job4j_order.service;

import ru.job4j.job4j_order.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Order create(Order order);
    Optional<Order> findById(int id);
    boolean update(int id, Order order);
    boolean deleteById(int id);
}
