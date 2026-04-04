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

import exception.ProductException;
import model.Product;
import service.ProductService;

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public List<Product> listProducts() {
        return service.list();
    }

    public List<Product> search(String q) {
        return service.search(q);
    }

    public Optional<Product> getProduct(long id) {
        return service.get(id);
    }

    public Product create(Product p, String role) throws ProductException {
        return service.create(p, role);
    }

    public void update(Product p, String role) throws ProductException {
        service.update(p, role);
    }

    public void delete(long id, String role) throws ProductException {
        service.delete(id, role);
    }
}
