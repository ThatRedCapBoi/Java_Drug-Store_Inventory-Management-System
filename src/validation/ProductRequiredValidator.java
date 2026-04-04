/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

/**
 *
 * @author Itadori
 */
import java.math.BigDecimal;
import model.Product;

public class ProductRequiredValidator {

    public static void validate(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product is required.");
        }

        if (p.getSku() == null || p.getSku().trim().isEmpty()) {
            throw new IllegalArgumentException("SKU is required.");
        }
        if (p.getSku().trim().length() > 50) {
            throw new IllegalArgumentException("SKU max length is 50.");
        }

        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        if (p.getName().trim().length() > 150) {
            throw new IllegalArgumentException("Name max length is 150.");
        }

        BigDecimal price = p.getPrice();
        if (price == null) {
            throw new IllegalArgumentException("Price is required.");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (p.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        if (p.getCategoryId() <= 0) {
            throw new IllegalArgumentException("Category is required.");
        }
    }
}
