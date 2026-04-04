/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import model.Category;

/**
 *
 * @author Itadori
 */
public class CategoryRequiredValidator extends Validator.Base<Category> {

    @Override
    public void validate(Category c) {
        if (c == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (c.getName() == null || c.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name is required.");
        }
        next(c);
    }
}
