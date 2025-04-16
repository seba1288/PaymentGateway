package pl.swrobel.ecommerce.productcatalog;


import org.junit.jupiter.api.Test;
import pl.swrobel.ecommerce.productcatalog.ArrayListProductStorage;
import pl.swrobel.ecommerce.productcatalog.Product;
import pl.swrobel.ecommerce.productcatalog.ProductCatalog;

import javax.imageio.metadata.IIOInvalidTreeException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsTolistAllProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assertTrue(products.isEmpty());

    }

    @Test
    void itAllowsToCreateProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("lego set 8083","nice one");

        List<Product> products = catalog.allProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    void createdProductsAreUniquelyIdentifiable() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId1 = catalog.createProduct("lego set 8083","nice one");
        String productId2 = catalog.createProduct("lego set 6285","nice one 2");

        assertNotEquals(productId1, productId2);
    }

    @Test
    void itLoadsProductById(){
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("lego set 8083","nice one");

        Product loaded = catalog.loadProductById(productId);

        assertNotNull(productId,loaded.getId());
        assertEquals("lego set 8083",loaded.getName());
        assertEquals("nice one",loaded.getDescription());
    }

    @Test
    void allowsToApplyPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("lego set 8083","nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(100,10));

        Product loaded = catalog.loadProductById(productId);
        assertEquals(productId,loaded.getPrice());
    }
    @Test
    void allowsToApplyPriceThatViolateteMinimumRange() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("lego set 8083","nice one");

        assertThrows(IIOInvalidTreeException.class,
                () -> catalog.changePrice(productId, BigDecimal.valueOf(100,10)));

        catalog.changePrice(productId, BigDecimal.valueOf(100,10));

        Product loaded = catalog.loadProductById(productId);
        assertEquals(productId,loaded.getPrice());
    }


    @Test
    void allowsToApplyImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("lego set 8083","nice one");

        catalog.changeImage(productId, "https://picsum.photos/200/300");

        Product loaded = catalog.loadProductById(productId);
        assertEquals(productId,loaded.getImage());
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(
                new ArrayListProductStorage()
        );
    }


}
