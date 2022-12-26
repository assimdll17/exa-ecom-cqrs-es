package ma.enset.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.inventoryservice.commonapi.enums.ProductStatus;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateProductRequestDTO {
    private String name;
    private double price;
    private int quantity;
    private ProductStatus status;
    private String categoryId;
}
