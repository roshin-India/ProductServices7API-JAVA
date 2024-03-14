package dev.roshin.productservicefeb24.repositories;

import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);

    @Override
    List<Product> findAll();
    Product findByIdIs(long id);
//    List<Product>findAllByTitle(String title);
    List<Product>findByCategory(Category category);
    List<Product>findByCategory_Title(String title);
    void delete(Product product);



}
