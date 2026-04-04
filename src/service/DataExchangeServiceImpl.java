package service;

import adapter.CsvProductAdapter;
import adapter.ProductAdapter;
import java.io.File;
import java.util.List;
import model.Product;
import repository.ProductRepo;
import validation.ProductRequiredValidator;

public class DataExchangeServiceImpl implements DataExchangeService {

    private final ProductRepo productRepo;

    public DataExchangeServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public String exportInventory(File file, String format, String role) {
        requireManager(role);

        ProductAdapter adapter = adapterFor(format);
        List<Product> products = productRepo.findAll();
        adapter.exportTo(file, products);

        return "Exported " + products.size() + " product(s) to:\n" + file.getAbsolutePath();
    }

    @Override
    public String importInventory(File file, String format, String role) {
        requireManager(role);

        ProductAdapter adapter = adapterFor(format);
        List<Product> imported = adapter.importFrom(file);

        int created = 0;
        int skipped = 0;

        for (Product p : imported) {
            ProductRequiredValidator.validate(p);
            if (productRepo.existsBySku(p.getSku().trim())) {
                skipped++;
                continue;
            }
            p.setSku(p.getSku().trim());
            p.setName(p.getName().trim());
            productRepo.save(p);
            created++;
        }
        return "Import completed.\n"
                + "Rows read: " + imported.size() + "\n"
                + "Created: " + created + "\n"
                + "Skipped (duplicate SKU): " + skipped;
    }

    private ProductAdapter adapterFor(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Format is required.");
        }

        String f = format.trim().toUpperCase();
        switch (f) {
            case "CSV":
                return new CsvProductAdapter();
            default:
                throw new IllegalArgumentException("Unsupported format (v1): " + format + ". Use CSV.");
        }
    }

    private void requireManager(String role) {
        if (role == null || !"INVENTORY_MANAGER".equalsIgnoreCase(role.trim())) {
            throw new IllegalArgumentException("Access denied. Manager only.");
        }
    }
}
