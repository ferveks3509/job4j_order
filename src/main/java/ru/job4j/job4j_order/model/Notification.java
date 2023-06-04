package ru.job4j.job4j_order.model;

public class Notification {
    private int id;
    private String status;

    public Notification() {
    }

    public Notification(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
