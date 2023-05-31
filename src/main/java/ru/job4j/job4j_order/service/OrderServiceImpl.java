package ru.job4j.job4j_order.service;

import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(int id) {
       return orderRepository.findById(id);
    }

    @Override
    public boolean update(int id, Order order) {
        boolean result = false;
        if (orderRepository.existsById(id)) {
            orderRepository.save(order);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            result = true;
        }
        return result;
    }
}
