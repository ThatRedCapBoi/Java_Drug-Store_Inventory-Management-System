package adapter;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class CsvProductAdapter implements ProductAdapter {

    @Override
    public List<Product> importFrom(File file) {
        List<Product> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String header = br.readLine();
            if (header == null) return list;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",", -1);
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid CSV row (need 5 columns): " + line);
                }

                Product p = new Product();
                p.setSku(parts[0].trim());
                p.setName(parts[1].trim());
                p.setPrice(new BigDecimal(parts[2].trim()));
                p.setQuantity(Integer.parseInt(parts[3].trim()));
                p.setCategoryId(Long.parseLong(parts[4].trim()));
                list.add(p);
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException("CSV import failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void exportTo(File file, List<Product> products) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

            bw.write("sku,name,price,quantity,category_id");
            bw.newLine();

            for (Product p : products) {
                bw.write(safe(p.getSku()) + "," +
                         safe(p.getName()) + "," +
                         (p.getPrice() == null ? "0.00" : p.getPrice().toPlainString()) + "," +
                         p.getQuantity() + "," +
                         p.getCategoryId());
                bw.newLine();
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV export failed: " + e.getMessage(), e);
        }
    }

    private String safe(String s) {
        if (s == null) return "";
        return s.replace(",", " ");
    }
}
