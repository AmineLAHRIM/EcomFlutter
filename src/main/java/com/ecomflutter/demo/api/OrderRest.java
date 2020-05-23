package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Order;
import com.ecomflutter.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/order")
public class OrderRest {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public List<Order> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        return this.orderService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Order order) {
        return this.orderService.save(order);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.orderService.deleteById(id);
    }
}
