package ma.enset.inventoryservice.query.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.events.ProductCreatedEvent;
import ma.enset.inventoryservice.query.entities.Product;
import ma.enset.inventoryservice.query.repositories.ProductRepository;
import ma.enset.queries.GetAllProductsQuery;
import ma.enset.queries.GetProductByIdQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProductServiceHandler {
    private ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        log.info("*******************************");
        log.info("CustomerCreatedEvent received");
        Product product = new Product();
        product.setProductId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        product.setCategoryId(event.getCategoryId());
        product.setStatus(event.getStatus());
        productRepository.save(product);
    }


    @QueryHandler
    public List<Product> on(GetAllProductsQuery query)   {
        return productRepository.findAll();
    }

    @QueryHandler
    public Product on(GetProductByIdQuery query) {
        return productRepository.findById(query.getId()).get();
    }
}
