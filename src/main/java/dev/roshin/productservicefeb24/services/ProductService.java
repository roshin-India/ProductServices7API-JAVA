package dev.roshin.productservicefeb24.services;

import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(long productId);
    List<Product> getProducts(String category);

    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);

    Product deleteProduct(Long productId);

    Product updateProduct(Long productId,Product product);

    List<Category> getCategories();

    List<Product> getProducts();


}
