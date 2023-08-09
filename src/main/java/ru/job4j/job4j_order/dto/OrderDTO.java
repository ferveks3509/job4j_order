package ru.job4j.job4j_order.dto;

import ru.job4j.job4j_order.model.Dish;

import java.util.List;

public class OrderDTO {
    private int id;
    private String name;
    private List<Dish> dishes;

    public OrderDTO() {
    }

    public OrderDTO(int id, String name, List<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
