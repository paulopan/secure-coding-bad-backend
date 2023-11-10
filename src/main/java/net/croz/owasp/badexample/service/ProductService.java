package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.service.command.CreateProductCommand;

public interface ProductService {

    Product create(CreateProductCommand createProductCommand);

}
