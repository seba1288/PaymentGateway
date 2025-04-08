package pl.swrobel.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ProductStorage productStorage;
//    TECH

    public ProductCatalog(ProductStorage productStorage) {
       this.productStorage = productStorage; //TEch
    }

    public List<Product> AllProducts() {
        return  productStorage.allProduct(); //TECH
    }

    public String createProduct(String name, String description) {
        var uuid = UUID.randomUUID();

        var newProduct = new Product(
                uuid,
                name,
                description
        ); // DOMAIN
        this.productStorage.save(newProduct); // TECH
        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return productStorage.loadProductById(productId);

    }

    public void changePrice(String productId, BigDecimal bigDecimal) {
        var product = productStorage.loadProductById(productId);

        if(BigDecimal.ZERO.compareTo(bigDecimal) <= 0) { // DOMAIN
            throw new InvalidPriceException();
        }
    }

    public void changeImage(String productId, String url) {
        var product = loadProductById(productId);
        product.setImage(url); // DOMAIN

    }
}
