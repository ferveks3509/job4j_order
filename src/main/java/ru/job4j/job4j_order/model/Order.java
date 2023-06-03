package ru.job4j.job4j_order.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "order_name")
    private String orderName;
    @JoinColumn(name = "dish_id")
    private int dishId;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order() {
    }

    public Order(String orderName, int dishId) {
        this.orderName = orderName;
        this.dishId = dishId;
    }

    public Order(String orderName, int dishId, Status status) {
        this.orderName = orderName;
        this.dishId = dishId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
