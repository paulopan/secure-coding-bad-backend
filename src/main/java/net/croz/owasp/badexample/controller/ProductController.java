package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.AuthUser;
import net.croz.owasp.badexample.entity.Order;
import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.entity.ProductComment;
import net.croz.owasp.badexample.entity.UserBuyer;
import net.croz.owasp.badexample.service.AuthService;
import net.croz.owasp.badexample.service.OrderService;
import net.croz.owasp.badexample.service.ProductService;
import net.croz.owasp.badexample.service.StorageService;
import net.croz.owasp.badexample.service.command.CreateOrderCommand;
import net.croz.owasp.badexample.service.command.CreateProductCommand;
import net.croz.owasp.badexample.service.command.CreateProductCommentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService;

    private final OrderService orderService;

    private final AuthService authService;

    @Autowired
    public ProductController(ProductService productService, StorageService storageService, OrderService orderService,
        AuthService authService) {
        this.productService = productService;
        this.storageService = storageService;
        this.orderService = orderService;
        this.authService = authService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(@ModelAttribute CreateProductCommand createProductCommand) {
        return productService.create(createProductCommand);
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/{id}/order")
    public Order placeOrder(
        @PathVariable Long id,
        @RequestBody @Valid CreateOrderCommand createOrderCommand,
        @RequestAttribute("authUser") AuthUser authUser) {
        final UserBuyer userBuyer = (UserBuyer)authService.getUserByType(authUser);
        return orderService.placeOrder(id, createOrderCommand, userBuyer);
    }

    @PostMapping("/{id}/comment")
    public ProductComment createComment(@PathVariable Long id,
        @RequestBody CreateProductCommentCommand createProductCommentCommand,
        @RequestAttribute("authUser") AuthUser authUser) {
        final UserBuyer userBuyer = (UserBuyer)authService.getUserByType(authUser);
        return productService.createComment(id, createProductCommentCommand, userBuyer);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
