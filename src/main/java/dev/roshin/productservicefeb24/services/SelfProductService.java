package dev.roshin.productservicefeb24.services;

import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;
import dev.roshin.productservicefeb24.repositories.CategoryRepository;
import dev.roshin.productservicefeb24.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getProducts(String category) {
        //two DB calls
        Category newCategory = categoryRepository.findByTitle(category);
        return productRepository.findByCategory(newCategory);
        //Single DB call
       // return productRepository.findByCategory_Title(category);
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(category);

        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
//            categoryFromDatabase = categoryRepository.save(newCategory);
            categoryFromDatabase = newCategory;
//            category1 = new Category();
//            category1.setTitle(category);
        }

        // if the category was found from DB -> category1 will be having an ID
        // else: category1 won't have any ID
        product.setCategory(categoryFromDatabase);

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product=getSingleProduct(productId);
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Product product1 = getSingleProduct(productId);
            if (product.getTitle() != null) {
                product1.setTitle(product.getTitle());
            }
            if (product.getImage() != null) {
                product1.setImage(product.getImage());
            }
            if (product.getDescription() != null) {
                product1.setDescription(product.getDescription());
            }
            if (Double.valueOf(product.getPrice()) != 0.0) {
                product1.setPrice(product.getPrice());
            }
            if (product.getCategory() != null) {
                Category newCategory = new Category();
                newCategory.setTitle(product.getCategory().getTitle());
                product1.setCategory(newCategory);
            }
            productRepository.save(product1);
            return product1;

    }

    @Override
    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }
}
