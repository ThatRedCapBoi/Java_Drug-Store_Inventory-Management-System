/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Itadori
 */
import exception.ProductException;
import java.util.List;
import java.util.Optional;
import model.Product;
import repository.ProductRepo;
import validation.ProductRequiredValidator;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> list() {
        return repo.findAll();
    }

    @Override
    public List<Product> search(String query) {
        return repo.search(query);
    }

    @Override
    public Optional<Product> get(long id) {
        return repo.findById(id);
    }

    @Override
    public Product create(Product p, String role) throws ProductException {
        ProductRequiredValidator.validate(p);

        if (repo.existsBySku(p.getSku().trim())) {
            throw new ProductException("SKU already exists.");
        }

        p.setSku(p.getSku().trim());
        p.setName(p.getName().trim());
        return repo.save(p);
    }

    @Override
    public void update(Product p, String role) throws ProductException {
        ProductRequiredValidator.validate(p);
        if (p.getId() <= 0) {
            throw new ProductException("Invalid product id.");
        }
        p.setSku(p.getSku().trim());
        p.setName(p.getName().trim());
        repo.update(p);
    }

    @Override
    public void delete(long id, String role) throws ProductException {
        requireManager(role);
        if (id <= 0) {
            throw new ProductException("Invalid product id.");
        }
        repo.delete(id);
    }

    private void requireManager(String role) {
        if (role == null) {
            throw new IllegalArgumentException("Access denied.");
        }
        if (!"INVENTORY_MANAGER".equalsIgnoreCase(role.trim())) {
            throw new IllegalArgumentException("Access denied. Manager only.");
        }
    }
}
