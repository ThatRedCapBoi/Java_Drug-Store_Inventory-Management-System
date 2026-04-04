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
public class CategoryFormatValidator extends Validator.Base<Category> {

    @Override
    public void validate(Category c) {
        String name = c.getName().trim();
        if (name.length() < 2) {
            throw new IllegalArgumentException("Category name is too short.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Category name is too long (max 50).");
        }
        next(c);
    }
}
