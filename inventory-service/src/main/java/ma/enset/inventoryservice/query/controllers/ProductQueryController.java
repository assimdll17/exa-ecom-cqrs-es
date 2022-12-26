package ma.enset.inventoryservice.query.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.inventoryservice.query.entities.Product;
import ma.enset.queries.GetAllProductsQuery;
import ma.enset.queries.GetProductByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/query/customer")
public class ProductQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allProducts")
    public List<Product> productList() {
        List<Product> response = queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class)).join();
        return response;
    }

    @GetMapping("/allProducts/{id}")
    public Product getProduct(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(Product.class)).join();
    }
}
