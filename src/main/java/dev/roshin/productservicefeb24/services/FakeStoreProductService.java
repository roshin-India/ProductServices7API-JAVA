package dev.roshin.productservicefeb24.services;

import dev.roshin.productservicefeb24.dtos.FakeStoreProductDto;

import dev.roshin.productservicefeb24.models.Category;
import dev.roshin.productservicefeb24.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @Override
    public Product getSingleProduct(long productId) {
        FakeStoreProductDto fakeStoreProduct=restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class);

        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getProducts(String category) {
        List<Product> listProduct = new ArrayList<Product>();
        List<LinkedHashMap<String, String>> listFakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category, List.class);
        for (LinkedHashMap<String, String> linkedHashMap : listFakeStoreProductDto) {
            Product tempProduct = new Product();
            tempProduct.setId((long) Long.valueOf(Integer.parseInt((String.valueOf(linkedHashMap.get("id"))))));
            tempProduct.setDescription(linkedHashMap.get("description"));
            tempProduct.setTitle(linkedHashMap.get("title"));
            tempProduct.setPrice(Double.parseDouble(String.valueOf(linkedHashMap.get("price"))));
            Category tempCategory = new Category();
            tempCategory.setTitle(linkedHashMap.get("category"));
            tempProduct.setCategory(tempCategory);
            tempProduct.setImage(linkedHashMap.get("image"));
            listProduct.add(tempProduct);

        }

        return listProduct;
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String image,
                                 String category,
                                 double price) {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);

        FakeStoreProductDto response=restTemplate.postForObject(
                "https://fakestoreapi.com/products",//url
                fakeStoreProductDto,//request body
                FakeStoreProductDto.class);//data type of response

        //if(response==null) return new Product();
        return response.toProduct();

    }

    @Override
    public Product deleteProduct(Long productId) {
        Product product = getSingleProduct(productId); // Retrieve and possibly throw exception if not found
        restTemplate.delete("https://fakestoreapi.com/products/" + productId); // Delete the product
        return product; // Return the deleted product's details

    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", productId.toString());
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productId.longValue());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(Double.valueOf(product.getPrice()).longValue());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());
        restTemplate.put("https://fakestoreapi.com/products/" + product.getId(),
                fakeStoreProductDto,
                params);
        return product;
    }

    @Override

    public List<Category> getCategories() {
        List<String> fakeStoreCategoryDtoList = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", List.class);
        List<Category> categoryList = new ArrayList<Category>();
        for (String fakeStoreCategoryDto : fakeStoreCategoryDtoList) {
            Category tempCategory = new Category();
            tempCategory.setTitle(fakeStoreCategoryDto);
            categoryList.add(tempCategory);
        }
        return categoryList;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        for (FakeStoreProductDto fakeStoreProduct : fakeStoreProducts) {
            products.add(fakeStoreProduct.toProduct());

        }
        return products;
    }


}