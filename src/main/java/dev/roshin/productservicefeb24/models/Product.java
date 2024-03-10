package dev.roshin.productservicefeb24.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private Category category;


}
