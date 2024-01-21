package ma.emsi.salleservice.service;

import ma.emsi.salleservice.bean.Category;
import ma.emsi.salleservice.dao.CategoryDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category save(Category entity) {
        return categoryDao.save(entity);
    }

    public Optional<Category> findById(Long aLong) {
        return categoryDao.findById(aLong);
    }

    public void deleteById(Long aLong) {
        categoryDao.deleteById(aLong);
    }
}
