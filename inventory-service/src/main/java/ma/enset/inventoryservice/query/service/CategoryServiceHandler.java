package ma.enset.inventoryservice.query.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.events.CategoryCreatedEvent;
import ma.enset.inventoryservice.query.entities.Category;
import ma.enset.inventoryservice.query.repositories.CategoryRepository;
import ma.enset.queries.GetAllCategoriesQuery;
import ma.enset.queries.GetCategoryByIdQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CategoryServiceHandler {
    private CategoryRepository categoryRepository;

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        log.info("*******************************");
        log.info("CategoryCreatedEvent received");
        Category category = new Category();
        category.setCategoryId(event.getId());
        category.setName(event.getName());
        category.setDescription(event.getDescription());
        categoryRepository.save(category);
    }


    @QueryHandler
    public List<Category> on(GetAllCategoriesQuery query)   {
        return categoryRepository.findAll();
    }

    @QueryHandler
    public Category on(GetCategoryByIdQuery query) {
        return categoryRepository.findById(query.getId()).get();
    }
}
