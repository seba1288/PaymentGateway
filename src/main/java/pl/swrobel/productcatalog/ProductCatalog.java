package pl.swrobel.productcatalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {
    private  List<Product> products;

    public ProductCatalog() {
       this.products = new ArrayList<>();
    }

    public List<Product> AllProducts() {
        return Collections.unmodifiableList(products);
    }

    public String createProduct(String name, String description) {
        var uuid = UUID.randomUUID();

        var newProduct = new Product(
                uuid,
                name,
                description
        );
        this.products.add(newProduct);
        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get();

    }

    public void changePrice(String productId, BigDecimal bigDecimal) {
        var product = loadProductById(productId);

        if(BigDecimal.ZERO.compareTo(bigDecimal) <= 0) {
            throw new InvalidPriceException();
        }
    }

    public void changeImage(String productId, String url) {
        var product = loadProductById(productId);
        product.setImage(url);

    }
}
