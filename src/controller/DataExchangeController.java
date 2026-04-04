/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Itadori
 */
import java.io.File;
import service.DataExchangeService;

public class DataExchangeController {

    private final DataExchangeService service;

    public DataExchangeController(DataExchangeService service) {
        this.service = service;
    }

    public String exportInventory(File file, String format, String role) {
        return service.exportInventory(file, format, role);
    }

    public String importInventory(File file, String format, String role) {
        return service.importInventory(file, format, role);
    }
}
