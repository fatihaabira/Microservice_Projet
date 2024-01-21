package ma.emsi.salleservice.controller;

import ma.emsi.salleservice.bean.Category;
import ma.emsi.salleservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("id/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id).orElse(null);
    }

    @PostMapping
    public Category save(@RequestBody Category entity) {
        return categoryService.save(entity);
    }

    @DeleteMapping("id/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
