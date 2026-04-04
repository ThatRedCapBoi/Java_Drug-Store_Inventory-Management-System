/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Itadori
 */
import dto.DashboardStats;
import java.util.List;
import model.Product;
import repository.ProductRepo;

public class DashboardServiceImpl implements DashboardService {

    private final ProductRepo productRepo;

    public DashboardServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public DashboardStats generate(int lowStockThreshold, String role) {
        if (lowStockThreshold < 0) {
            throw new IllegalArgumentException("Threshold cannot be negative.");
        }

        int total = productRepo.countAll();
        int lowCount = productRepo.countLowStock(lowStockThreshold);
        List<Product> lowList = productRepo.findLowStock(lowStockThreshold);

        return new DashboardStats(total, lowCount, lowStockThreshold, lowList);
    }
}
