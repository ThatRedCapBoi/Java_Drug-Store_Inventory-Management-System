/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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

public interface ProductService {

    List<Product> list();

    List<Product> search(String query);

    Optional<Product> get(long id);

    Product create(Product p, String role) throws ProductException;

    void update(Product p, String role) throws ProductException;

    void delete(long id, String role) throws ProductException;
}
