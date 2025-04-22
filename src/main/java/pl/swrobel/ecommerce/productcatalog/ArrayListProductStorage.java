package pl.swrobel.ecommerce.productcatalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListProductStorage implements ProductStorage {

    private List<Product> products;

    public ArrayListProductStorage() {
        this.products = new ArrayList<>(); //TEch
    }

    @Override
    public List<Product> allProduct() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void add(Product newProduct) {
        this.products.add(newProduct);
    }

    @Override
    public Product getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get(); // TECH

    }
}
