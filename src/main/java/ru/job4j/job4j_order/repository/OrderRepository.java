package ru.job4j.job4j_order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_order.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query("from Order")
    List<Order> findByPageRequest(PageRequest pageRequest);
}
