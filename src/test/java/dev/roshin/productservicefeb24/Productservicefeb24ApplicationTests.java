package dev.roshin.productservicefeb24;

import dev.roshin.productservicefeb24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Productservicefeb24ApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }
//    @Test
//    void testingQueries(){
//
//        productRepository.findAllByTitle("Hello");
//
//    }

}
