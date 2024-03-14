package dev.roshin.productservicefeb24.services;

import dev.roshin.productservicefeb24.exceptions.ProductNotFoundException;
import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getSingleProduct(long productId) throws ProductNotFoundException;

    List<Product> getProducts(String category);

    Product createProduct(String title,
                          String description,
                          String image,
                          String category,
                          double price);

    Product deleteProduct(Long productId) throws ProductNotFoundException;

    Product updateProduct(Long productId,Product product);

    List<Category> getCategories();

    List<Product> getProducts();


}
