/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

/**
 *
 * @author Itadori
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class JsonProductAdapter implements ProductAdapter {

    @Override
    public List<Product> importFrom(File file) {
        try {
            String json = readAll(file).trim();
            if (json.isEmpty() || json.equals("[]")) {
                return new ArrayList<>();
            }
            return SimpleJsonProductParser.parse(json);

        } catch (Exception e) {
            throw new RuntimeException("JSON import failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void exportTo(File file, List<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            bw.write("[\n");
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                bw.write("  {");
                bw.write("\"sku\":\"" + esc(p.getSku()) + "\",");
                bw.write("\"name\":\"" + esc(p.getName()) + "\",");
                bw.write("\"price\":" + (p.getPrice() == null ? "0.00" : p.getPrice().toPlainString()) + ",");
                bw.write("\"quantity\":" + p.getQuantity() + ",");
                bw.write("\"categoryId\":" + p.getCategoryId());
                bw.write("}");
                if (i < products.size() - 1) {
                    bw.write(",");
                }
                bw.write("\n");
            }
            bw.write("]\n");

        } catch (Exception e) {
            throw new RuntimeException("JSON export failed: " + e.getMessage(), e);
        }
    }

    private String readAll(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }

    private String esc(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
