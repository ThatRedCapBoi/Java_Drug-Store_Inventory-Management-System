/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import exception.CategoryException;
import exception.UserException;
import java.util.List;
import java.util.Optional;
import model.Category;
import repository.CategoryRepo;
import validation.CategoryFormatValidator;
import validation.CategoryRequiredValidator;
import validation.Validator;

/**
 *
 * @author Itadori
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo repo;
    private final Validator<Category> validatorChain;

    public CategoryServiceImpl(CategoryRepo repo) {
        this.repo = repo;

        // Chain: Required -> Format
        CategoryRequiredValidator v1 = new CategoryRequiredValidator();
        CategoryFormatValidator v2 = new CategoryFormatValidator();
        v1.linkWith(v2);
        this.validatorChain = v1;
    }

    @Override
    public List<Category> list() {
        return repo.findAll();
    }

    @Override
    public Optional<Category> get(long id) {
        return repo.findById(id);
    }

    @Override
    public Category create(Category c, String role) throws CategoryException {
        try {
            requireManager(role);
            validatorChain.validate(c);

            if (repo.existsByName(c.getName().trim())) {
                throw new CategoryException("Category name already exists.");
            }

            c.setName(c.getName().trim());
            return repo.save(c);
        } catch (UserException ex) {
            System.getLogger(CategoryServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    @Override
    public void update(Category c, String role) throws CategoryException {
        try {
            requireManager(role);
            validatorChain.validate(c);

            if (c.getId() <= 0) {
                throw new CategoryException("Invalid category id.");
            }

            c.setName(c.getName().trim());
            repo.update(c);
        } catch (UserException ex) {
            System.getLogger(CategoryServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void delete(long id, String role) throws CategoryException {
        try {
            requireManager(role);
            if (id <= 0) {
                throw new CategoryException("Invalid category id.");
            }
            repo.delete(id);
        } catch (UserException ex) {
            System.getLogger(CategoryServiceImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void requireManager(String role) throws UserException {
        if (role == null) {
            throw new UserException("Access denied.");
        }
        if (!"INVENTORY_MANAGER".equalsIgnoreCase(role.trim())) {
            throw new UserException("Access denied. Manager only.");
        }
    }
}
