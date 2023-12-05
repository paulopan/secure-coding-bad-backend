package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.Order;
import net.croz.owasp.badexample.entity.UserBuyer;
import net.croz.owasp.badexample.service.command.CreateOrderCommand;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer);

    List<Order> findAll();

}