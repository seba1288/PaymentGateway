package pl.swrobel.ecommerce.productcatalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapProductStorage  implements ProductStorage {

    HashMap<String, Product> productsHashMap;

    public HashMapProductStorage() {
        this.productsHashMap = new HashMap<>();
    }

    @Override
    public List<Product> allProduct() {
        return productsHashMap.values().stream().toList();
    }


    @Override
    public void add(Product newProduct) {
        productsHashMap.put(newProduct.getId(), newProduct);
    }

    @Override
    public Product getProductById(String productId) {
        return productsHashMap.get(productId);
    }
}
