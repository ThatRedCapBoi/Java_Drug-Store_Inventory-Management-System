/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

/**
 *
 * @author Itadori
 */
import java.io.File;

public interface DataExchangeService {

    String exportInventory(File file, String format, String role);

    String importInventory(File file, String format, String role);
}
