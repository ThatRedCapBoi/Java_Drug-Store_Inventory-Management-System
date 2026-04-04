/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Itadori
 */
import java.util.List;
import model.Product;

public class DashboardStats {

    private final int totalProducts;
    private final int lowStockCount;
    private final int threshold;
    private final List<Product> lowStockProducts;

    public DashboardStats(int totalProducts, int lowStockCount, int threshold, List<Product> lowStockProducts) {
        this.totalProducts = totalProducts;
        this.lowStockCount = lowStockCount;
        this.threshold = threshold;
        this.lowStockProducts = lowStockProducts;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public int getLowStockCount() {
        return lowStockCount;
    }

    public int getThreshold() {
        return threshold;
    }

    public List<Product> getLowStockProducts() {
        return lowStockProducts;
    }
}
