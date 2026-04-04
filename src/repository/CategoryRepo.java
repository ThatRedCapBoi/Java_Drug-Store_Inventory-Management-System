/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import java.util.Optional;
import model.Category;

public interface CategoryRepo {

    List<Category> findAll();

    Optional<Category> findById(long id);

    Category save(Category c);

    void update(Category c);

    void delete(long id);

    boolean existsByName(String name);
}
