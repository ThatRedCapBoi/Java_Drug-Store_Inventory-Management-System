/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Itadori
 */
import java.util.List;
import java.util.Optional;

import exception.CategoryException;
import model.Category;
import service.CategoryService;

public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    public List<Category> listCategories() {
        return service.list();
    }

    public Optional<Category> getCategory(long id) {
        return service.get(id);
    }

    public Category createCategory(Category c, String role) throws CategoryException {
        return service.create(c, role);
    }

    public void updateCategory(Category c, String role) throws CategoryException {
        service.update(c, role);
    }

    public void deleteCategory(long id, String role) throws CategoryException {
        service.delete(id, role);
    }
}
