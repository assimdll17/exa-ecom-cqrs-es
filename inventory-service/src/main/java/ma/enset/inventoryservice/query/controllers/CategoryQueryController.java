package ma.enset.inventoryservice.query.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.inventoryservice.query.entities.Category;
import ma.enset.queries.GetAllCustomersQuery;
import ma.enset.queries.GetCustomerByIdQuery;
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
public class CategoryQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/allCategories")
    public List<Category> categoryList() {
        List<Category> response = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Category.class)).join();
        return response;
    }

    @GetMapping("/allCategories/{id}")
    public Category getCategory(@PathVariable String id) {
        return queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Category.class)).join();
    }
}
