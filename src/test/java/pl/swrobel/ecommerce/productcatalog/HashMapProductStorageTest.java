package pl.swrobel.ecommerce.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashMapProductStorageTest {


    public static final String EXAMPLE_PRODUCT = "example product";
    @Test
    void itStoresAndLoadProduct() {
        var product = thereIsExampleProduct();
        var productStorage  = thereIsStorage();

        productStorage.add(product);

        List<Product> products = productStorage.allProduct();

       assertThat(products)
               .hasSize(1)
               .extracting(Product::getName)
               .containsExactly(EXAMPLE_PRODUCT);
    }

    @Test
    void itStoresAndLoadById() {
        var  product = thereIsExampleProduct();
        var productStorage  = thereIsStorage();

        productStorage.add(product);
        var loaded = productStorage.getProductById(product.getId());

        assertThat(loaded.getId()).isEqualTo(product.getId());

    }

    private HashMapProductStorage thereIsStorage() {
        return  new HashMapProductStorage();

    }

    private Product thereIsExampleProduct() {
        var product =   new Product(UUID.randomUUID(),
                EXAMPLE_PRODUCT,
                "yep product"
        );
        product.changePrice(BigDecimal.valueOf(10.10));

        return product;
    }
}
