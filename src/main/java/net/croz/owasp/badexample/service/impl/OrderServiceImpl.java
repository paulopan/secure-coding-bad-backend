package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.entity.Order;
import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.entity.UserBuyer;
import net.croz.owasp.badexample.repository.OrderRepository;
import net.croz.owasp.badexample.service.OrderService;
import net.croz.owasp.badexample.service.ProductService;
import net.croz.owasp.badexample.service.command.CreateOrderCommand;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer) {
        final Product product = productService.findById(id);

        final Order order = new Order();

        order.setCreationDate(LocalDateTime.now());
        order.setProduct(product);
        order.setBuyer(userBuyer);
        order.setQuantity(createOrderCommand.getQuantity());

        return orderRepository.save(order);
    }

}
