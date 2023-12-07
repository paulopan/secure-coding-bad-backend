package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.Order;
import net.croz.owasp.badexample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    // OWASP[84]
    // OWASP[85]
    // OWASP[87]
    // OWASP[88]
    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

}
