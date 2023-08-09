package ru.job4j.job4j_order.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_order.model.Dish;

import java.util.Collections;
import java.util.List;

@Component
public class DishRepositoryImpl implements APIDishRepository {
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

    @Override
    public List<Dish> findAll() {
        List<Dish> body = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Dish>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
