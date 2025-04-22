package pl.swrobel.ecommerce.productcatalog;

import java.util.List;

public interface ProductStorage {
    List<Product> allProduct();

    void add(Product newProduct);

    Product getProductById(String productId);
}
