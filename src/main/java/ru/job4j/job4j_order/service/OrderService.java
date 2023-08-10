package ru.job4j.job4j_order.service;

import org.springframework.data.domain.PageRequest;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Iterable<Order> findAll();
    OrderDTO create(Order order);
    Optional<Order> findById(int id);
    boolean update(int id, Order order);
    boolean deleteById(int id);
    OrderDTO findOrderDTO(int id);
    List<Order> findByPageRequest(PageRequest pageRequest);
}
