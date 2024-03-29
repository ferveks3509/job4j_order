package ru.job4j.job4j_order.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_order.dto.OrderDTO;
import ru.job4j.job4j_order.model.Order;
import ru.job4j.job4j_order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<Order> findAll() {
        return (List<Order>) orderService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> create(@RequestBody Order order) {
        return new ResponseEntity<>(
                orderService.create(order),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable int id) {
        var order = orderService.findById(id);
        return new ResponseEntity<>(
                order.orElse(new Order()),
                order.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Order order) {
        var update = orderService.update(id, order);
        return new ResponseEntity<>(update ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        var delete = orderService.deleteById(id);
        return new ResponseEntity<>(delete ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> findByIdOrderDTO(@PathVariable int id) {
        var order = orderService.findById(id);
        if (order.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                orderService.findOrderDTO(order.get().getId()),
                HttpStatus.OK);
    }

    @GetMapping("/pages")
    public List<Order> findByPages(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size) {
        return orderService.findByPageRequest(PageRequest.of(page, size));
    }
}
