package ma.enset.inventoryservice.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.inventoryservice.commonapi.enums.ProductStatus;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreateCategoryRequestDTO {
    private String name;
    private String description;
}
