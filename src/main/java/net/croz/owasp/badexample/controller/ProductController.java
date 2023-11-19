package net.croz.owasp.badexample.controller;

import net.croz.owasp.badexample.entity.Product;
import net.croz.owasp.badexample.entity.ProductComment;
import net.croz.owasp.badexample.service.ProductService;
import net.croz.owasp.badexample.service.StorageService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final StorageService storageService;

    @Autowired
    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
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

    @PostMapping("/{id}/comment")
    public ProductComment createComment(@PathVariable Long id,
        @RequestBody CreateProductCommentCommand createProductCommentCommand) {
        return productService.createComment(id, createProductCommentCommand);
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
