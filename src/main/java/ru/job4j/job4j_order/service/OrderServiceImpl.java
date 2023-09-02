package ru.job4j.job4j_order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_order.client.APIDishRepository;
import ru.job4j.job4j_order.dto.OrderDTO;
import ru.job4j.job4j_order.model.Dish;
import ru.job4j.job4j_order.model.Notification;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.model.Status;
import ru.job4j.job4j_order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OrderRepository orderRepository;
    private final APIDishRepository apiDishRepository;

    @Autowired
    public OrderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, OrderRepository orderRepository,
                            APIDishRepository apiDishRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
        this.apiDishRepository = apiDishRepository;
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public OrderDTO create(Order order) {
        Notification notification = new Notification();
        order.setStatus(Status.created);
        var saveOrder = orderRepository.save(order);
        notification.setStatus(order.getStatus().name());
        //kafkaTemplate.send("order", saveOrder);
        kafkaTemplate.send("statusDish", notification.toString());
        return convertToOrderDTO(saveOrder);
    }

    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setName(order.getName());
        String[] dishId = order.getDishId().split(",");
        orderDTO.setDishes(convertToList(dishId));
        return orderDTO;
    }

    private List<Dish> convertToList(String[] dishId) {
        List<Dish> dishes = new ArrayList<>();
        for(String id : dishId) {
            int dishIdInt = Integer.parseInt(id);
            Dish dish = apiDishRepository.findById(dishIdInt);
            if (dish != null) {
                dishes.add(dish);
            }
        }
        return dishes;
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
        Order order = findById(id).orElseThrow();
        return convertToOrderDTO(order);
    }
    @Override
    public List<Order> findByPageRequest(PageRequest pageRequest) {
        //Page<Order> page = orderRepository.findByPageRequest(pageRequest);
        //return page.getContent();
        return orderRepository.findByPageRequest(pageRequest);
    }
}
