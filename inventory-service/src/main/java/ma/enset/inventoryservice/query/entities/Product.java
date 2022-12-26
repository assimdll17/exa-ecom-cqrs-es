package ma.enset.inventoryservice.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.enums.ProductStatus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    private String productId;
    private String name;
    private double price;
    private int quantity;
    private ProductStatus status;
    private String categoryId;
}
