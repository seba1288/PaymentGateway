package pl.swrobel.productcatalog;

import java.util.List;

public interface ProductStorage {
    List<Product> allProduct();

    List<Product> allProducts();

    void save(Product newProduct);

    Product loadProductById(String productId);
}
