package ru.job4j.job4j_order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.model.Notification;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.dto.OrderDTO;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.repository.APIDishRepository;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderRepository orderRepository;
    private final APIDishRepository apiDishRepository;

    public OrderServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, OrderRepository orderRepository, APIDishRepository apiDishRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.apiDishRepository = apiDishRepository;
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        Notification notification = new Notification();
        order.setStatus(Status.created);
        var saveOrder = orderRepository.save(order);
        notification.setStatus(order.getStatus().name());
        kafkaTemplate.send("preOrder", saveOrder);
        kafkaTemplate.send("statusDish", notification);
        return saveOrder;
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

    @Override
    public OrderDTO findOrderDTO(int id) {
        Order order = findById(id).get();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(order);
        orderDTO.setDish(apiDishRepository.findById(order.getDishId()));
        return orderDTO;
    }
}
