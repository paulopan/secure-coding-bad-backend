package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.entity.Order;
import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.entity.UserBuyer;
import net.croz.owasp.badexample.repository.OrderRepository;
import net.croz.owasp.badexample.service.MessagingService;
import net.croz.owasp.badexample.service.OrderService;
import net.croz.owasp.badexample.service.ProductService;
import net.croz.owasp.badexample.service.command.CreateOrderCommand;
import net.croz.owasp.badexample.service.message.OrderMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final MessagingService messagingService;

    private final static String PRODUCT_ORDER_MESSAGE_DESTINATION = "/product/%d";

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService,
        MessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.messagingService = messagingService;
    }

    @Override
    public Order placeOrder(Long id, CreateOrderCommand createOrderCommand, UserBuyer userBuyer) {
        final Product product = productService.findById(id);

        final Order order = new Order();

        order.setCreationDate(LocalDateTime.now());
        order.setProduct(product);
        order.setBuyer(userBuyer);
        order.setQuantity(createOrderCommand.getQuantity());

        final Order savedOrder = orderRepository.save(order);

        final String topic = String.format(PRODUCT_ORDER_MESSAGE_DESTINATION, product.getSeller().getId());
        final OrderMessage orderMessage = new OrderMessage();
        orderMessage.setProductId(order.getProduct().getId());
        orderMessage.setProductName(order.getProduct().getName());
        orderMessage.setBuyerId(order.getBuyer().getId());
        orderMessage.setQuantity(order.getQuantity());

        messagingService.sendMessage(topic, orderMessage);

        return savedOrder;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
