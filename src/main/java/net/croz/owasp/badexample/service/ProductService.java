package net.croz.owasp.badexample.service;

import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.entity.ProductComment;
import net.croz.owasp.badexample.service.command.CreateProductCommand;
import net.croz.owasp.badexample.service.command.CreateProductCommentCommand;

public interface ProductService {

    Product create(CreateProductCommand createProductCommand);

    Product findById(Long id);

    ProductComment createComment(Long id, CreateProductCommentCommand createProductCommentCommand);

}
