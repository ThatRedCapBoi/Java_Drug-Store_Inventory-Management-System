/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

/**
 *
 * @author Itadori
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Product;

public class SimpleJsonProductParser {

    public static List<Product> parse(String json) {
        String s = json.trim();
        if (s.isEmpty() || s.equals("[]")) {
            return new ArrayList<>();
        }
        Pattern objPattern = Pattern.compile("\\{([^}]*)\\}");
        Matcher m = objPattern.matcher(s);

        List<Product> list = new ArrayList<>();

        while (m.find()) {
            String obj = m.group(1);

            String sku = extractString(obj, "sku");
            String name = extractString(obj, "name");
            BigDecimal price = extractDecimal(obj, "price");
            int quantity = extractInt(obj, "quantity");
            long categoryId = extractLong(obj, "categoryId");

            Product p = new Product();
            p.setSku(sku);
            p.setName(name);
            p.setPrice(price);
            p.setQuantity(quantity);
            p.setCategoryId(categoryId);

            list.add(p);
        }

        return list;
    }

    private static String extractString(String obj, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
        Matcher m = p.matcher(obj);
        if (!m.find()) {
            throw new IllegalArgumentException("Missing field: " + key);
        }
        return unescape(m.group(1));
    }

    private static BigDecimal extractDecimal(String obj, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*([-0-9.]+)");
        Matcher m = p.matcher(obj);
        if (!m.find()) {
            throw new IllegalArgumentException("Missing field: " + key);
        }
        return new BigDecimal(m.group(1));
    }

    private static int extractInt(String obj, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*(-?\\d+)");
        Matcher m = p.matcher(obj);
        if (!m.find()) {
            throw new IllegalArgumentException("Missing field: " + key);
        }
        return Integer.parseInt(m.group(1));
    }

    private static long extractLong(String obj, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*(-?\\d+)");
        Matcher m = p.matcher(obj);
        if (!m.find()) {
            throw new IllegalArgumentException("Missing field: " + key);
        }
        return Long.parseLong(m.group(1));
    }

    private static String unescape(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
