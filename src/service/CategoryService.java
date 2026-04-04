/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import exception.CategoryException;
import java.util.List;
import java.util.Optional;
import model.Category;

/**
 *
 * @author Itadori
 */
public interface CategoryService {

    List<Category> list();

    Optional<Category> get(long id);

    Category create(Category c, String role) throws CategoryException;

    void update(Category c, String role) throws CategoryException;

    void delete(long id, String role) throws CategoryException;
}
