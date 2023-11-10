package net.croz.owasp.badexample.service.impl;


import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.repository.ProductRepository;
import net.croz.owasp.badexample.service.ProductService;
import net.croz.owasp.badexample.service.StorageService;
import net.croz.owasp.badexample.service.command.CreateProductCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StorageService storageService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StorageService storageService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    @Transactional
    @Override
    public Product create(CreateProductCommand createProductCommand) {
        final String productImage = storageService.store(createProductCommand.getImage());

        final Product product = new Product();
        product.setName(createProductCommand.getName());
        product.setDescription(createProductCommand.getDescription());
        product.setPrice(createProductCommand.getPrice());
        product.setProductImage(productImage);

        return productRepository.save(product);
    }

}
