package pl.swrobel.Catalog;

import org.junit.jupiter.api.Test;
import pl.swrobel.productcatalog.Product;
import pl.swrobel.productcatalog.ProductStorage;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapProductStorageTest {

    @Test
    void itSavesAndLoadProduct() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        var loaded  = storage.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDescription(), loaded.getDescription());
    }

    private Product thereIsProduct() {
        return  new Product(UUID.randomUUID(),
                "first it is product",
                "yep product"
        );
    }

    private ProductStorage thereIsStorage() {
        return HashMapProductStorage();

    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        List<Product> all = storage.allProducts();

        assertEquals(1,all.size());
    }
}
