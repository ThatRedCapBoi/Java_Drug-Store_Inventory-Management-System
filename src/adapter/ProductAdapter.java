/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adapter;

/**
 *
 * @author Itadori
 */
import java.io.File;
import java.util.List;
import model.Product;

public interface ProductAdapter {

    List<Product> importFrom(File file);

    void exportTo(File file, List<Product> products);
}
