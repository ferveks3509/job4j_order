package ru.job4j.job4j_order.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.DishDTO;
import ru.job4j.job4j_order.model.Order;

public class DishRepository {
    @Value("${api-url}")
    private String url;
    private final RestTemplate restTemplate;

    public DishRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Order findById(int id) {
        DishDTO dish = restTemplate.getForEntity(
                String.format("%s/%s",url, id),
                DishDTO.class

        ).getBody();
        Order order = new Order();
        order.setDishId(dish.getId());
        return order;
    }
}
