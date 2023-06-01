package ru.job4j.job4j_order.model;

public class OrderDTO {
    private Order order;
    private Dish dish;

    public Order getOrder() {
        return order;
    }

    public OrderDTO() {
    }

    public OrderDTO(Order order, Dish dish) {
        this.order = order;
        this.dish = dish;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
