package dev.roshin.productservicefeb24.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter

public class CreateProductRequestDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}
