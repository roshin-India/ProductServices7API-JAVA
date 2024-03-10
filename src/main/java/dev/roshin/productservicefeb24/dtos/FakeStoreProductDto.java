package dev.roshin.productservicefeb24.dtos;

import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
    public Product toProduct(){
        Product product=new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImage(image);
        product.setDescription(description);
        product.setPrice(price);

        Category productCategory = new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;

    }

}
