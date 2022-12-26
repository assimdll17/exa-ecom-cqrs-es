package ma.enset.inventoryservice.commonapi.commands;

import lombok.Getter;
import ma.enset.inventoryservice.commonapi.enums.ProductStatus;

public class CreateProductCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private double price;
    @Getter private int quantity;
    @Getter private ProductStatus status;
    @Getter private String categoryId;

    public CreateProductCommand(String id, String name, double price, int quantity, ProductStatus status, String categoryId) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.categoryId = categoryId;
    }
}

