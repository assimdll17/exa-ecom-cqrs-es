package ma.enset.inventoryservice.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    private String categoryId;
    private String name;
    private String description;
}
