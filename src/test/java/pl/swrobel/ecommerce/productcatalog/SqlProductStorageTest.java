package pl.swrobel.ecommerce.productcatalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class SqlProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void helloWorldSql() {
        var sql = """
            select now();
    """;
        var result = jdbcTemplate.queryForObject(sql, String.class);
        assert result.contains("2025");


    }
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

    private SqlProductStorage thereIsStorage() {
        return new SqlProductStorage();

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


