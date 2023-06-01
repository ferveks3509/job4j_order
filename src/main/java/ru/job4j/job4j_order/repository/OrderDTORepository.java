package ru.job4j.job4j_order.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.Dish;

@Repository
public class OrderDTORepository implements APIDishRepository {
    @Value("${api-url}")
    private String url;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Dish findById(int id) {
        return restTemplate.getForEntity(
                String.format("%s/%s", url, id),
                Dish.class

        ).getBody();
    }
}
