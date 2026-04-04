/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

/**
 *
 * @author Itadori
 */
import dto.DashboardStats;

public interface DashboardService {

    DashboardStats generate(int lowStockThreshold, String role);
}
