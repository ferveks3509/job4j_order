package ru.job4j.job4j_order.model;

import java.util.Objects;

public class DishDTO {
    private int id;
    private String name;
    private String description;

    public DishDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishDTO dishDTO = (DishDTO) o;
        return id == dishDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
