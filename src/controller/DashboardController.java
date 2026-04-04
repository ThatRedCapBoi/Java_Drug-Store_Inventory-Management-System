/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Itadori
 */
import dto.DashboardStats;
import service.DashboardService;

public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    public DashboardStats generate(int threshold, String role) {
        return service.generate(threshold, role);
    }
}
