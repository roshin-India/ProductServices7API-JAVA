package dev.roshin.productservicefeb24.controllers;

import dev.roshin.productservicefeb24.dtos.CreateProductRequestDto;
import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;
import dev.roshin.productservicefeb24.services.ProductService;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;     //dependency injection
    }

    //POST/products
    //request body
    //{
    //  title:
    //  desc:
    //  price:}

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request){
            return productService.createProduct(
                    request.getTitle(),
                    request.getDescription(),
                    request.getImage(),
                    request.getCategory(),
                    request.getPrice()
            );
    }
    // GET/products/1
    // GET/products/201
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") long productId){
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProducts(@PathVariable("category") String category) {
        return productService.getProducts(category);
    }


    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id")Long productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId,
                                 @RequestBody CreateProductRequestDto request){
        Product product = new Product();
        product.setDescription(request.getDescription());
        Category category = new Category();
        category.setTitle(request.getCategory());
        product.setCategory(category);
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        return productService.updateProduct(productId,product);
    }
    @GetMapping("/products/categories")
    public List<Category> getCategories() {
        return productService.getCategories();
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

}
